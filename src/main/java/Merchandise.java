package main.java;
public abstract class Merchandise {

    protected int itemNumber;

    protected float price;

    protected int inStock;

    protected String brandName;

    protected String itemName;

    protected boolean taxable;

    public final String getItemName(){
        return this.itemName;
    }

    public final double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.getItemName() + "," + this.getPrice();
    }
}
