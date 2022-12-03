package main.java;

public class AutomotiveItem extends Merchandise {

    public AutomotiveItem(String item, float price, int itemNum, String path) {
        this.itemName = item;
        this.price = price;
        this.itemNumber = itemNum;
        this.filePath = path;
    }
}
