package main.java;

public class MeatItem extends GroceryItem {
    
    public float pricePerPound;

    public float weight;

    public MeatItem(String item, float price, float weight, int itemNum, String path) {
        super(item, price * weight, itemNum, path);
        this.weight = weight;
        this.pricePerPound = price;
    }
}
