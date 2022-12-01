import java.util.Collection;
import java.util.HashMap;

public class InventoryController {
    
    public HashMap<Integer, Merchandise> inventory;

    public InventoryController(String fileName) {
		this.inventory = new HashMap<Integer, Merchandise>();
		loadInventory(fileName);
    }

	private void loadInventory(String fileName) {
		//TODO Write inventory file
		//TODO load inventory file
        inventory.put(1, new GroceryItem("Flour", 2.99f));
        inventory.put(2, new GroceryItem("Cornmeal", 2.99f));
	}

	public Collection<Merchandise> values() {
		return inventory.values();
	}

    
    
}
