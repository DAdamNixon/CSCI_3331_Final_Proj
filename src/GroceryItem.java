import java.util.Date;

public class GroceryItem extends Merchandise {
    
    public Date expiration;
    
    public boolean wic;

    public boolean foodStamps;

    public GroceryItem(){}
    
    public GroceryItem(String item, float price) {
        this.itemName = item;
        this.price = price;
    }
}
