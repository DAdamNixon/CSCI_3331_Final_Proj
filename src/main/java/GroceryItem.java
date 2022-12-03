package main.java;
import java.util.Date;

public class GroceryItem extends Merchandise {
    
    public Date expiration;
    
    public boolean wic;

    public boolean foodStamps;

    public GroceryItem(){}
    
    public GroceryItem(String item, float price, int itemNum) {
        this.itemName = item;
        this.price = price;
		this.itemNumber = itemNum;
    }
}
