package harshtheory.com.pathways.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import harshtheory.com.pathways.models.Level;
import harshtheory.com.pathways.database.PathwaysDBSchema.LevelsTable;

public class LevelCursorWrapper extends CursorWrapper {

    public static final String TAG = "LevelCursorWrapper";

    public LevelCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Level getLevel()
    {
        int id = getInt(getColumnIndex(LevelsTable.Cols.ID));
        String identifier = getString(getColumnIndex(LevelsTable.Cols.IDENTIFIER));

        return new Level(id, identifier);
    }
}
