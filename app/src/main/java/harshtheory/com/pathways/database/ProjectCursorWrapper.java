package harshtheory.com.pathways.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.database.PathwaysDBSchema.ProjectsTable;

public class ProjectCursorWrapper extends CursorWrapper {

    public static final String TAG = "ProjectCursorWrapper";

    public ProjectCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Project getProject()
    {
        int id = getInt(getColumnIndex(ProjectsTable.Cols.ID));
        String name = getString(getColumnIndex(ProjectsTable.Cols.NAME));
        String identifier = getString(getColumnIndex(ProjectsTable.Cols.IDENTIFIER));

        return new Project(id, name, identifier);
    }
}
