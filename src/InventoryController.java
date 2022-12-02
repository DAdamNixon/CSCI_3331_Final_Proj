import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryController {
    
    public HashMap<Integer, Merchandise> inventory;

    public InventoryController(String fileName) {
		this.inventory = new HashMap<Integer, Merchandise>();
		loadInventory(fileName);
    }

	private void loadInventory(String fileName) {
        File inventoryFile = new File(fileName);
		try (Scanner input = new Scanner(inventoryFile)) {
			int merchandiseType = 0;
			while(input.hasNextLine()) {
				
				String[] line = input.nextLine().split(",");
				if (line.length == 1){
					merchandiseType = Integer.valueOf(line[0]);
				}
				else{
					int itemNum = Integer.valueOf(line[2]);
					float price = Float.parseFloat(line[1]);
					switch(merchandiseType){
						case 1:
							inventory.put(itemNum, new GroceryItem(line[0], price, itemNum));
							break;
						case 2:
							inventory.put(itemNum, new AutomotiveItem(line[0], price, itemNum));
							break;
						case 3:
							inventory.put(itemNum, new MeatItem(line[0], price, Float.parseFloat(line[3]), itemNum));
							break;
						default:
							break;
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Merchandise get(int itemNum) {
		return this.inventory.get(itemNum);
	}
	protected final Collection<Merchandise> values() {
		return inventory.values();
	}

}
