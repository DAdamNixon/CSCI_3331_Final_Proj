package main.java;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Builder;

// Inventory Controller
// Component Classes: CardBuilder, StockPanel, IconView

// InventoryController is responsible for storing the current inventory of the store, and creating graphical depictions of the Inventory items through CardBuilder
public class InventoryController {

	private static String filename = "inventory.csv";

	// Hashmap to store and control inventory 
	public HashMap<Integer, Merchandise> inventory;

	// Constructor
	// initializes Hashmap and loads inventory file
	public InventoryController() {
		this.inventory = new HashMap<Integer, Merchandise>();
		loadInventory();
	}

	// reads in the inventory.csv file and creates merchandise objects based on the data
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
					int inStock = Integer.valueOf(line[3]);
					switch (merchandiseType) {
						case 1:
							inventory.put(itemNum, new GroceryItem(name, price, itemNum, inStock, line[4]));
							break;
						case 2:
							inventory.put(itemNum, new AutomotiveItem(name, price, itemNum, inStock, line[4]));
							break;
						case 3:
							inventory.put(itemNum,
									new MeatItem(name, price, Float.parseFloat(line[4]), itemNum, inStock, line[5]));
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

	// Static method to build a card based on the merchandise parameter
	public static Region card(Merchandise model, Cart cart) {
		return new CardBuilder(model, cart).build();
	}

	// Returns the Merchandise item with the given item number
	public Merchandise get(int itemNum) {
		return this.inventory.get(itemNum);
	}

	// Returns a Collection containing all of the items in the store
	protected final Collection<Merchandise> values() {
		return inventory.values();
	}

}// END InventoryController

// CardBuilder builds the GUI element that displays an item in the store.  The cards are used by ItemView and CartView
class CardBuilder implements Builder<Region> {

    Merchandise model;
    Cart cart;

	// Constructor
	// Sets variables necessary to build()
    public CardBuilder(Merchandise model, Cart cart) {
        this.model = model;
        this.cart = cart;
    }

	// Interface method.  Builds a GUI construct based on the Merchandise of the CardBuilder
    @Override
    public Region build() {
        VBox card = new VBox();
        card.setPadding(new Insets(15));
        card.setMinHeight(180);
        card.setMinWidth(180);
        card.getChildren().addAll(
                new Label(model.getItemName()),
                new Label(String.format("$ %.2f", model.getPrice())),
                new IconView(Resources.imagePath(model.getpath())),
                new StockPanel(model.inStock, model.itemNumber, this.cart));
        card.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(10))));
        return card;
    }

}// END CardBuilder

// StockPanel is a component used by CardBuilder to display the number of items in stock and the button to add an item to the cart
class StockPanel extends HBox {
	
	// Constructor
	// Builds Labels and controls for the Cart
	StockPanel(int inStock, int itemNumber, Cart cart) {
		Label stockLabel = new Label("In Stock: " + inStock);
		Button addToCart = new Button();
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		addToCart.setAlignment(Pos.BOTTOM_RIGHT);
		if (cart.getFlag()) {
			addToCart.setText("Remove");
			addToCart.setOnAction(e -> {
				cart.removeItem(itemNumber);
			});
			cart.setFlag();
		}
		else {
			addToCart.setText("Add to cart");
			addToCart.setOnAction(e -> {
				cart.addItem(itemNumber);
			});
		}
		if (inStock == 0) {
			addToCart.disableProperty().set(true);
		}
		
		getChildren().addAll(stockLabel, spacer, addToCart);
	}
}// END StockPanel

// IconView is a component used by CardBuilder to display the image associated with an item for sale
class IconView extends HBox {
	
	IconView(String filename) {
		ImageView img = new ImageView(filename);
		img.setFitHeight(100);
		img.setFitWidth(100);
		this.getChildren().add(img);
		this.setAlignment(Pos.CENTER);
	}
}// END IconView