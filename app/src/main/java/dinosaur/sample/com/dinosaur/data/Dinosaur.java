package dinosaur.sample.com.dinosaur.data;

public class Dinosaur {

    private int id;
    private String name;
    private int drawableID;

    public Dinosaur(int id, String name, int drawableID) {
        this.id = id;
        this.name = name;
        this.drawableID = drawableID;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
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

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }
}
