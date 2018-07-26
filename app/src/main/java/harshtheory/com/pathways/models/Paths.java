package harshtheory.com.pathways.models;

public class Paths
{
    public static final String TAG = "Paths";

    private int id;
    private String title;
    private String imageName;
    private String description;
    private String imageContDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
        setImageContDesc(imageName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageContDesc() {
        return imageContDesc;
    }

    private void setImageContDesc(String imageContDesc) {
        this.imageContDesc = imageContDesc + " Pathways Logo";
    }
}
