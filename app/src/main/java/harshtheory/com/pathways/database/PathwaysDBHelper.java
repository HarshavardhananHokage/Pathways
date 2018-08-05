package harshtheory.com.pathways.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PathwaysDBHelper extends SQLiteAssetHelper {

    public static final String TAG = "PathwaysDBHelper";

    private static final String DB_NAME = "pathways.db";
    private static final int DB_VERSION = 1;

    private static PathwaysDBHelper pathwaysDBHelper;

    public static synchronized PathwaysDBHelper getInstance(Context context)
    {
        if(pathwaysDBHelper == null)
        {
            pathwaysDBHelper = new PathwaysDBHelper(context.getApplicationContext());
        }
        return pathwaysDBHelper;
    }

    private PathwaysDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }



}
