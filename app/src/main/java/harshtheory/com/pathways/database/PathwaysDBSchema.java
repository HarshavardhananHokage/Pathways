package harshtheory.com.pathways.database;

public class PathwaysDBSchema {
    public static final String TAG = "PathwaysDBSchema";

    public static final class PathsTable
    {
        public static final String NAME = "PATHS";

        public static final class Cols
        {
            public static final String ID = "_id";
            public static final String NAME = "name";
            public static final String DESC = "description";
            public static final String LOGO = "logo";
            public static final String LVL_ONE = "lvl_one";
            public static final String LVL_TWO = "lvl_two";
            public static final String LVL_THREE = "lvl_three";
            public static final String LVL_THREE_ELEC = "lvl_three_elec";
            public static final String LVL_FOUR = "lvl_four";
            public static final String LVL_FOUR_ELEC = "lvl_four_elec";
            public static final String LVL_FIVE = "lvl_five";
            public static final String LVL_FIVE_ELEC = "lvl_five_elec";
        }
    }

    public static final class LevelsTable
    {
        public static final String NAME = "LEVELS";

        public static final class Cols
        {
            public static final String ID = "_id";
            public static final String IDENTIFIER = "identifier";
        }
    }
}
