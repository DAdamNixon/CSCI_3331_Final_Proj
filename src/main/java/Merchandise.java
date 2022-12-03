package main.java;

// Abtract class Merchandise which Automotive, Grocery, and Meat Items inherit from 
public abstract class Merchandise {

    // Shared Fields
    protected int itemNumber;
    protected float price;
    protected int inStock;
    protected String brandName;
    protected String itemName;
    protected boolean taxable;

    // Filepath for card image
    protected String filePath;

    // Returns the name of the item
    public final String getItemName() {
        return this.itemName;
    }

    // Returns the price of the item
    public final double getPrice() {
        return this.price;
    }

    // Returns the image filepath
    public final String getpath() {
        return this.filePath;
    }

    // Returns whether the item is taxable or not
    public boolean taxable() {
        return this.taxable;
    }

    // Returns a string representation of important attributes
    @Override
    public String toString() {
        return this.getItemName() + "," + this.getPrice();
    }
}
