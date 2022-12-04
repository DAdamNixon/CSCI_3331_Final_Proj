package main.java;

import java.util.Date;

// Inherits from Merchandise
// Grocery items are classified as all non-meat food products
public class GroceryItem extends Merchandise {

    // Fields
    public Date expiration;
    public boolean wic;
    public boolean foodStamps;

    // Default Constructor
    public GroceryItem() {
    }

    // Parameterized Constructor
    public GroceryItem(String item, float price, int itemNum, int inStock, String path) {
        setItemName(item);
        setPrice(price);
        setItemNumber(itemNum);
        setInStock(inStock);
        setTaxable(false);
        setFilePath(path);
    }

    @Override
    public String inventoryString() {
        String string = String.format("%s,%.2f,%d,%d,%s\n",
            getItemName(),
            getPrice(),
            getItemNumber(),
            getInStock(),
            getpath());
        return string;
    }
}
