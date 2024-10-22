package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// Cart
// Component Classes: CartView, Panel
// Cart is responsible for maintaining a list of Merchandise objects, and displaying them appropriately in the CartView
public class Cart {

	MainPage main;
	InventoryController invCont;

	private CartView view;
	private HashMap<Integer, Integer> cart;

	private boolean flag;

	public Cart(MainPage mainPage, InventoryController iController) {
		this.main = mainPage;
		this.invCont = iController;
		this.cart = new HashMap<>();
		this.view = new CartView(this);
		this.flag = false;
	}

	public CartView getView() {
		return this.view;
	}

	public void addItem(int itemNum) {
		Merchandise item = invCont.get(itemNum);
		if (cart.get(itemNum) != null) {
			if (cart.get(itemNum) < item.getInStock()) {
				cart.put(itemNum, cart.get(itemNum) + 1);
			}
		} else {
			cart.put(itemNum, 1);
		}
		view.makeCards();
	}

	// Removes the reference to an item fron the cart list, then remakes the cards
	public void removeItem(int itemNum) {
		if (cart.get(itemNum) == 1) {
			this.cart.remove(itemNum);
		} else {
			cart.put(itemNum, cart.get(itemNum) - 1);
		}
		view.makeCards();
	}

	// Returns the subtotal to be dislpayed in the Panel
	public float getSubtotal() {
		float subtotal = 0f;
		for (int itemNum : cart.keySet()) {
			subtotal += invCont.get(itemNum).getPrice() * cart.get(itemNum);
		}
		return subtotal;
	}

	// Returns the list of items currently stored in the Cart
	public HashMap<Integer, Integer> getCart() {
		return this.cart;
	}

	// Returns the flag that determines if a card should be a cart card
	public boolean getFlag() {
		return this.flag;
	}

	// Toggles the flag that determines if a card should be a cart card
	public void setFlag() {
		this.flag = !flag;
	}

	// Removes every item from the cart, then remakes(removes) the cards
	public void clear() {
		this.cart.clear();
		view.makeCards();
	}

	public HashMap<Integer, Merchandise> getInventory() {
		return invCont.getInv();
	}

	public int getNumInCart(int itemNumber) {
		return cart.get(itemNumber);
	}

	// Writes the order being made into the orders.csv file, then clears the Cart
	public void purchase() {
		writeOrder();
		invCont.updateInventory(this.cart);
		clear();
	}

	// Writes the order being made into the orders.csv file
	private void writeOrder() {
		File orders = new File(Resources.dataPath("orders.csv"));
		try (FileWriter fw = new FileWriter(orders, true)) {
			fw.append(String.format("%s\n", main.getUser().username));
			for (int itemNumber : cart.keySet()) {
				fw.append(String.format("%d,%d\n", itemNumber, cart.get(itemNumber)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}// END Cart

// CartView is the GUI interface that displays the cart.
class CartView extends VBox {

	ScrollPane scroll;
	FlowPane container;
	Panel panel;
	Cart cart;

	// Constructor
	// Sets up the interface
	public CartView(Cart cart) {
		this.cart = cart;
		this.container = new FlowPane();
		this.scroll = new ScrollPane(this.container);
		this.container
				.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.scroll.setPrefHeight(500);
		this.scroll.setFitToWidth(true);
		this.scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		this.panel = new Panel(cart);
		updateLabels();

		this.setPrefWidth(195);
		getChildren().addAll(this.scroll, this.panel);

	}

	// Gets the subtotal from the Cart and updates all of the cart pricing labels
	// appropriately
	public void updateLabels() {
		float taxableSubtotal = 0f;
		float untaxableSubtotal = 0f;
		HashMap<Integer, Merchandise> inventory = this.cart.getInventory();
		HashMap<Integer, Integer> items = this.cart.getCart();
		for (int itemNum : items.keySet()) {
			Merchandise merch = inventory.get(itemNum);
			double total = merch.getPrice() * items.get(itemNum);
			if (merch.taxable()) {
				taxableSubtotal += total;
			} else {
				untaxableSubtotal += total;
			}
		}

		float subtotal = taxableSubtotal + untaxableSubtotal;

		this.panel.getSubtotalValue().setText(String.format("%.2f", subtotal));
		updateTotalLabel(updateTaxLabel(taxableSubtotal) + subtotal);

	}

	// Updates the tax amount Label
	private float updateTaxLabel(float subtotal) {
		float tax = subtotal * 0.0825f;
		this.getTax().setText(String.format("%.2f", tax));
		return tax;
	}

	// Updates the total amount Label
	private void updateTotalLabel(float total) {
		this.getTotal().setText(String.format("$ %.2f", total));
	}

	// Returns the subtotal value from the Panel
	public Label getSubtotal() {
		return this.panel.getSubtotalValue();
	}

	// Returns the tax value from the Panel
	public Label getTax() {
		return this.panel.getTaxValue();
	}

	// Returns the total value from the Panel
	public Label getTotal() {
		return this.panel.getTotalValue();
	}

	// Generates cards for each item in the cart and displays them in the CartView
	public void makeCards() {
		Set<Integer> merchandise = this.cart.getCart().keySet();
		HashMap<Integer, Merchandise> inventory = this.cart.getInventory();
		this.container.getChildren().clear();
		for (int merch : merchandise) {
			this.cart.setFlag();
			this.add(InventoryController.card(inventory.get(merch), this.cart));
		}
		updateLabels();
	}

	private void add(Region card) {
		this.container.getChildren().add(card);
	}
}// END CartView

// Panel
// Panel is a GUI construct that displays information related to the
// calculations for the total price of the cart, and contains buttons to clear
// the cart or make the purchase
class Panel extends VBox {

	Label subtotalValue;
	Label taxValue;
	Label totalValue;

	public Panel(Cart cart) {

		HBox subtotalBox = new HBox();
		Label subtotal = new Label("Subtotal:");
		this.subtotalValue = new Label("0.00");
		subtotalBox.getChildren().addAll(subtotal, spacer(), subtotalValue);

		HBox taxBox = new HBox();
		Label tax = new Label("Tax (8.25%):");
		this.taxValue = new Label("0.00");
		taxBox.getChildren().addAll(tax, spacer(), taxValue);

		HBox spacerBox = new HBox();
		spacerBox.getChildren().add(spacer());
		spacerBox.setPrefHeight(40);

		HBox totalBox = new HBox();
		Label total = new Label("Total:");
		this.totalValue = new Label("$ 0.00");
		totalBox.getChildren().addAll(total, spacer(), totalValue);

		HBox spacerBox2 = new HBox();
		spacerBox2.getChildren().add(spacer());
		spacerBox2.setPrefHeight(80);

		HBox buttonBox = new HBox();

		Alert a = new Alert(AlertType.NONE);

		Button clearButton = new Button("Clear Cart");
		clearButton.setPrefHeight(40);
		clearButton.setOnAction(e -> {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Are you sure you want to clear the cart?");
			Optional<ButtonType> result = a.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				cart.clear();
			}
		});
		Button purchaseButton = new Button("Make Purchase");
		purchaseButton.setOnAction(e -> {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Are you sure you want to purchase the cart?");
			Optional<ButtonType> result = a.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				cart.purchase();
			}
		});
		purchaseButton.setPrefHeight(40);

		buttonBox.getChildren().addAll(clearButton, spacer(), purchaseButton);

		this.getChildren().addAll(subtotalBox, taxBox, spacerBox, totalBox, spacerBox2, buttonBox);
	}

	public Label getSubtotalValue() {
		return this.subtotalValue;
	}

	public Label getTaxValue() {
		return this.taxValue;
	}

	public Label getTotalValue() {
		return this.totalValue;
	}

	private Region spacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}
}
