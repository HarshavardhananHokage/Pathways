package harshtheory.com.pathways.models;

public class Level {

    public static final String TAG = "Level";

    private int id;
    private String title;
    private String description;
    private String purpose;
    private String overview;
    private String includes;

    public Level(int id, String identifier)
    {
        this.id = id;
        this.title = identifier + "_name";
        this.description = identifier + "_desc";
        this.purpose = identifier + "_purpose";
        this.overview = identifier + "_overview";
        this.includes = identifier + "_includes";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getOverview() {
        return overview;
    }

    public String getIncludes() {
        return includes;
    }
}
