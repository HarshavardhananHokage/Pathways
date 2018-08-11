package harshtheory.com.pathways.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.util.GeneralUtils;

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

   public ArrayList<Path> getAllPaths()
    {
        PathsCursorWrapper allPathsCursorWrapper = queryPath(null, null);
        ArrayList<Path> allPaths = new ArrayList<>(allPathsCursorWrapper.getCount());

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

    public Path getIndividualPath(int id)
    {
        String whereClause = PathwaysDBSchema.PathsTable.Cols.ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        PathsCursorWrapper pathsCursorWrapper = queryPath(whereClause, whereArgs);

        if(pathsCursorWrapper.getCount() > 0)
        {
            pathsCursorWrapper.moveToFirst();
            return pathsCursorWrapper.getPath();
        }
        return null;
    }

    public ProjectCursorWrapper queryProject(String whereClause, String[] whereArgs, String orderByClause)
    {
        Cursor cursor = pathwaysDatabase.query(PathwaysDBSchema.ProjectsTable.NAME, null,
                whereClause, whereArgs, null, null, orderByClause);

        return new ProjectCursorWrapper(cursor);
    }

    public ArrayList<Project> getSelectedProjects(int[] projectIdList)
    {
        String whereClause = null;
        String[] whereArgs = null;
        String orderByClause = null;
        if(projectIdList != null) {
            whereClause = PathwaysDBSchema.ProjectsTable.Cols.ID + " IN(" + GeneralUtils.generateQueryPlaceHolders(projectIdList.length) + ")";
            whereArgs = GeneralUtils.convertIntArrToStrArr(projectIdList);
            orderByClause = GeneralUtils.orderByIds(projectIdList, PathwaysDBSchema.ProjectsTable.Cols.ID);
        }

        ProjectCursorWrapper selectedLvlCurWrapper = queryProject(whereClause, whereArgs, orderByClause);

        ArrayList<Project> selectedProjects = new ArrayList<>(selectedLvlCurWrapper.getCount());
         selectedLvlCurWrapper.moveToFirst();

         try
         {
             while (!selectedLvlCurWrapper.isAfterLast())
             {
                 selectedProjects.add(selectedLvlCurWrapper.getProject());
                 selectedLvlCurWrapper.moveToNext();
             }
         }finally {
             selectedLvlCurWrapper.close();
         }

         return selectedProjects;
    }


}
