package main.java;

// Inherits from Merchandise, requires no extra attributes
// Automotive items are classified as all automobile related products
public class AutomotiveItem extends Merchandise {

    // Parameterized Constructor
    public AutomotiveItem(String item, float price, int itemNum, String path) {
        this.itemName = item;
        this.price = price;
        this.itemNumber = itemNum;
        this.filePath = path;
        this.taxable = true;
    }
}
