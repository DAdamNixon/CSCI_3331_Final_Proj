public class MeatItem extends GroceryItem {
    public float pricePerPound;

    public float weight;

    public MeatItem(String item, float price, float weight, int itemNum) {
        this.itemName = item;
        this.pricePerPound = price;
        this.weight = weight;
        this.price = price * weight;
		this.itemNumber = itemNum;
    }
}
