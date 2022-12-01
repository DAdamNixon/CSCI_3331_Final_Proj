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
					switch(merchandiseType){
						case 1:
							inventory.put(Integer.valueOf(line[2]), new GroceryItem(line[0], Float.parseFloat(line[1])));
							break;
						case 2:
							inventory.put(Integer.valueOf(line[2]), new AutomotiveItem(line[0], Float.parseFloat(line[1])));
							break;
						case 3:
							inventory.put(Integer.valueOf(line[3]), new MeatItem(line[0], Float.parseFloat(line[1]), Float.parseFloat(line[2])));
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

	public Collection<Merchandise> values() {
		return inventory.values();
	}

}
