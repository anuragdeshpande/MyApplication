package productiveapps.com.foodplanner;

/**
 * Created by anura on 7/21/2017.
 */

public class Food {
    private String name;
    private int count;
    private int thumbnail;
    private String foodID;

    public Food(){

    }

    public Food(String name, int count, int thumbnail, String ID){
        this.name = name;
        this.count = count;
        this.thumbnail = thumbnail;
        this.foodID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }
}
