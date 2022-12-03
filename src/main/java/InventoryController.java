package main.java;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryController {

	private static String filename = "inventory.csv";

	public HashMap<Integer, Merchandise> inventory;

	public InventoryController() {
		this.inventory = new HashMap<Integer, Merchandise>();
		loadInventory();
	}

	private void loadInventory() {
		File inventoryFile = new File(Resources.dataPath(filename));
		try (Scanner input = new Scanner(inventoryFile)) {
			int merchandiseType = 0;
			while (input.hasNextLine()) {

				String[] line = input.nextLine().split(",");
				if (line.length == 1) {
					merchandiseType = Integer.valueOf(line[0]);
				} else {
					String name = line[0];
					float price = Float.parseFloat(line[1]);
					int itemNum = Integer.valueOf(line[2]);
					switch (merchandiseType) {
						case 1:
							inventory.put(itemNum, new GroceryItem(name, price, itemNum, line[3]));
							break;
						case 2:
							inventory.put(itemNum, new AutomotiveItem(name, price, itemNum, line[3]));
							break;
						case 3:
							inventory.put(itemNum,
									new MeatItem(name, price, Float.parseFloat(line[3]), itemNum, line[4]));
							break;
						default:
							break;
					}
				}
			}

		} catch (Exception e) {
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
