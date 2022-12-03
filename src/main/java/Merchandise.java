package main.java;

public abstract class Merchandise {

    protected int itemNumber;

    protected float price;

    protected int inStock;

    protected String brandName;

    protected String itemName;

    protected boolean taxable;

    protected String filePath;

    public final String getItemName() {
        return this.itemName;
    }

    public final double getPrice() {
        return this.price;
    }

    public final String getpath() {
        return this.filePath;
    }

    public boolean taxable() {
        return this.taxable;
    }
    @Override
    public String toString() {
        return this.getItemName() + "," + this.getPrice();
    }
}
