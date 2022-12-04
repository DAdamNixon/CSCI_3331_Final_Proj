package main.java;

// Inherits from Merchandise
// Meat items are classified as all meat-based products
public class MeatItem extends GroceryItem {

    // Fields
    private float pricePerPound;
    private float weight;

    // Parameterized Constructor
    public MeatItem(String item, float price, float weight, int itemNum, int inStock, String path) {
        super(item, price * weight, itemNum, inStock, path);
        this.weight = weight;
        this.pricePerPound = price;
    }

    @Override
    public String inventoryString() {
        String string = String.format("%s,%.2f,%d,%d,%.2f,%s\n",
            getItemName(),
            getPricePerPound(),
            getItemNumber(),
            getInStock(),
            getWeight(),
            getpath());
        return string;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getPricePerPound() {
        return this.pricePerPound;
    }
}
