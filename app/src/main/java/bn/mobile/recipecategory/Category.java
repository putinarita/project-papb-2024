package bn.mobile.recipecategory;

public class Category {
    private final String name;
    private final int imageResourceId;

    public Category(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return this.name;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }
}
