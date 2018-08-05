package harshtheory.com.pathways.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import harshtheory.com.pathways.database.PathwaysDBSchema.PathsTable;

import harshtheory.com.pathways.models.Path;

public class PathsCursorWrapper extends CursorWrapper {

    public static final String TAG = "PathsCursorWrapper";

    public PathsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Path getPath() {
        Path path = new Path();

        path.setId(getInt(getColumnIndex(PathsTable.Cols.ID)));
        path.setName(getString(getColumnIndex(PathsTable.Cols.NAME)));
        path.setDescription(getString(getColumnIndex(PathsTable.Cols.DESC)));
        path.setLogo(getString(getColumnIndex(PathsTable.Cols.LOGO)));
        path.setLevelOne(getString(getColumnIndex(PathsTable.Cols.LVL_ONE)));
        path.setLevelTwo(getString(getColumnIndex(PathsTable.Cols.LVL_TWO)));
        path.setLevelThree(getString(getColumnIndex(PathsTable.Cols.LVL_THREE)));
        path.setLevelThreeElect(getString(getColumnIndex(PathsTable.Cols.LVL_THREE_ELEC)));
        path.setLevelFour(getString(getColumnIndex(PathsTable.Cols.LVL_FOUR)));
        path.setLevelFourElect(getString(getColumnIndex(PathsTable.Cols.LVL_FOUR_ELEC)));
        path.setLevelFive(getString(getColumnIndex(PathsTable.Cols.LVL_FIVE)));
        path.setLevelFiveElect(getString(getColumnIndex(PathsTable.Cols.LVL_FIVE_ELEC)));

        return path;
    }
}
