package main.java;

// Inherits from Merchandise, requires no extra attributes
// Automotive items are classified as all automobile related products
public class AutomotiveItem extends Merchandise {

    // Parameterized Constructor
    public AutomotiveItem(String item, float price, int itemNum, int inStock, String path) {
        setItemName(item);
        setPrice(price);
        setItemNumber(itemNum);
        setInStock(inStock);
        setTaxable(true);
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
