package harshtheory.com.pathways.database;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import harshtheory.com.pathways.models.Level;
import harshtheory.com.pathways.models.Path;

public class PathwaysDBManager {
    private static final String TAG = "PathwaysDBManager";

    private Context context;
    private SQLiteDatabase pathwaysDatabase;

    public PathwaysDBManager(Context context)
    {
        this.context = context;
        pathwaysDatabase = PathwaysDBHelper.getInstance(context).getReadableDatabase();
    }

    private PathsCursorWrapper queryPath(String whereClause, String[] whereArgs)
    {
        Cursor cursor = pathwaysDatabase.query(PathwaysDBSchema.PathsTable.NAME, null,
                whereClause, whereArgs, null, null, null);
        return new PathsCursorWrapper(cursor);
    }

   public List<Path> getAllPaths()
    {
        PathsCursorWrapper allPathsCursorWrapper = queryPath(null, null);
        List<Path> allPaths = new ArrayList<>(allPathsCursorWrapper.getCount());

        try
        {
            allPathsCursorWrapper.moveToFirst();
            while(!allPathsCursorWrapper.isAfterLast())
            {
                allPaths.add(allPathsCursorWrapper.getPath());
                allPathsCursorWrapper.moveToNext();
            }
        }catch (Exception e)
        {
            Log.e(TAG,  e.getLocalizedMessage());
        }
        finally {
            allPathsCursorWrapper.close();
        }

        return allPaths;
    }

    public LevelCursorWrapper queryLevel(String whereClause, String[] whereArgs)
    {
        Cursor cursor = pathwaysDatabase.query(PathwaysDBSchema.LevelsTable.NAME, null,
                whereClause, whereArgs, null, null, null);

        return new LevelCursorWrapper(cursor);
    }

    public List<Level> getSelectedLevels(int[] levelsList)
    {
        String whereClause = null;
        String[] whereArgs = null;
        if(levelsList != null) {
            whereClause = PathwaysDBSchema.LevelsTable.Cols.ID + "=?";
            whereArgs = new String[]{Arrays.toString(levelsList)};
        }

        LevelCursorWrapper selectedLvlCurWrapper = queryLevel(whereClause, whereArgs);

        List<Level> selectedLevels = new ArrayList<>(selectedLvlCurWrapper.getCount());
         selectedLvlCurWrapper.moveToFirst();

         try
         {
             while (!selectedLvlCurWrapper.isAfterLast())
             {
                 selectedLevels.add(selectedLvlCurWrapper.getLevel());
                 selectedLvlCurWrapper.moveToNext();
             }
         }finally {
             selectedLvlCurWrapper.close();
         }

         return selectedLevels;
    }


}
