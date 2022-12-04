package main.java;

// Abtract class Merchandise which Automotive, Grocery, and Meat Items inherit from 
public abstract class Merchandise {

    // Shared Fields
    private int itemNumber;
    private float price;
    private int inStock;
    //private String brandName; unused data field
    private String itemName;
    private boolean taxable;

    // Filepath for card image
    private String filePath;

    // Returns the item number of the item
    public final int getItemNumber() {
        return this.itemNumber;
    }

    // Sets itemNumber
    public void setItemNumber(int itemNum) {
        this.itemNumber = itemNum;
    }

    // Returns the number of items in stock
    public final int getInStock() {
        return this.inStock;
    }

    // Sets inStock
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    // Returns the name of the item
    public final String getItemName() {
        return this.itemName;
    }

    // Sets itemName
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Returns the price of the item
    public final float getPrice() {
        return this.price;
    }

    // Sets price
    public void setPrice(float price) {
        this.price = price;
    }

    // Returns the image filepath
    public final String getpath() {
        return this.filePath;
    }

    //Sets filepath
    public void setFilePath(String path) {
        this.filePath = path;
    }

    // Returns whether the item is taxable or not
    public final boolean taxable() {
        return this.taxable;
    }

    // Sets taxable
    public void setTaxable(boolean flag) {
        this.taxable = flag;
    }
    
    // Formats the item's properties as a String for storagr purposes
    public abstract String inventoryString();

    // Returns a string representation of important attributes
    @Override
    public String toString() {
        return this.getItemName() + "," + this.getPrice();
    }
}
