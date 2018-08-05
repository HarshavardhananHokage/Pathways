package harshtheory.com.pathways.models;

import harshtheory.com.pathways.util.GeneralUtils;

public class Path
{
    public static final String TAG = "Path";

    private int id;
    private String name;
    private String description;
    private String logo;
    private String imageContDesc;
    private int[] levelOne;
    private int[] levelTwo;
    private int levelThree;
    private int levelFour;
    private int[] levelFive;
    private int[] levelThreeElect;
    private int[] levelFourElect;
    private int[] levelFiveElect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
        setImageContDesc(logo);
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

    public int[] getLevelOne() {
        return levelOne;
    }

    public void setLevelOne(String levelOne) {
        this.levelOne = GeneralUtils.getIntArrayFromString(levelOne);
    }

    public int[] getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(String levelTwo) {
        this.levelTwo = GeneralUtils.getIntArrayFromString(levelTwo);
    }

    public int getLevelThree() {
        return levelThree;
    }

    public void setLevelThree(String levelThree) {
        this.levelThree = Integer.parseInt(levelThree);
    }

    public int getLevelFour() {
        return levelFour;
    }

    public void setLevelFour(String levelFour) {
        this.levelFour = Integer.parseInt(levelFour);
    }

    public int[] getLevelFive() {
        return levelFive;
    }

    public void setLevelFive(String levelFive) {
        this.levelFive = GeneralUtils.getIntArrayFromString(levelFive);
    }

    public int[] getLevelThreeElect() {
        return levelThreeElect;
    }

    public void setLevelThreeElect(String levelThreeElect) {
        this.levelThreeElect = GeneralUtils.getIntArrayFromString(levelThreeElect);
    }

    public int[] getLevelFourElect() {
        return levelFourElect;
    }

    public void setLevelFourElect(String levelFourElect) {
        this.levelFourElect = GeneralUtils.getIntArrayFromString(levelFourElect);
    }

    public int[] getLevelFiveElect() {
        return levelFiveElect;
    }

    public void setLevelFiveElect(String levelFiveElect) {
        this.levelFiveElect = GeneralUtils.getIntArrayFromString(levelFiveElect);
    }
}
