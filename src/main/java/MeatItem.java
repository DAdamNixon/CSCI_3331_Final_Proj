package main.java;

// Inherits from Merchandise
// Meat items are classified as all meat-based products
public class MeatItem extends GroceryItem {

    // Fields
    public float pricePerPound;
    public float weight;

    // Parameterized Constructor
    public MeatItem(String item, float price, float weight, int itemNum, String path) {
        super(item, price * weight, itemNum, path);
        this.weight = weight;
        this.pricePerPound = price;
    }
}
