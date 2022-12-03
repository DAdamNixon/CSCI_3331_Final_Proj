package main.java;
import java.util.LinkedList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CartView extends VBox {

	ScrollPane scroll;
	FlowPane container;
	Panel panel;

	public CartView(Cart cart){
		
		this.container = new FlowPane();
		this.scroll = new ScrollPane(this.container);
		this.scroll.setPrefHeight(500);
		this.scroll.setFitToWidth(true);
		this.scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		this.panel = new Panel(cart);
		updateLabels(0);

		this.setPrefWidth(195);
		getChildren().addAll(this.scroll, this.panel);

	}

	public void updateLabels(float subtotal) {
		this.panel.getSubtotalValue().setText(String.format("%.2f", subtotal));
		updateTotalLabel(updateTaxLabel(subtotal));
		

	}

	private float updateTaxLabel(float subtotal) {
		float tax = subtotal * 0.0825f;
		this.getTax().setText(String.format("%.2f", tax));
		return subtotal + tax;
	}
	
	private void updateTotalLabel(float total) {
		this.getTotal().setText(String.format("$ %.2f", total));
	}

	public Label getSubtotal() {
		return this.panel.getSubtotalValue();
	}

	public Label getTax() {
		return this.panel.getTaxValue();
	}

	public Label getTotal() {
		return this.panel.getTotalValue();
	}

	public void add(Region card) {
		this.container.getChildren().add(card);
	}

	public void makeCards(Cart cart) {
		LinkedList<Merchandise> merchandise = cart.getCart();
		this.container.getChildren().clear();
		for (Merchandise merch : merchandise) {
			cart.setFlag();
			this.add(new CardBuilder(merch, cart).build());
		}
		updateLabels(cart.getSubtotal());
	}
}

class Panel extends VBox {

	Label subtotalValue;
	Label taxValue;
	Label totalValue;

	public Panel(Cart cart) {
	
		HBox subtotalBox = new HBox();
		Label subtotal = new Label("Subtotal:");
		this.subtotalValue = new Label("12.99");
		subtotalBox.getChildren().addAll(subtotal, spacer() ,subtotalValue);

		HBox taxBox = new HBox();
		Label tax = new Label("Tax (8.25%):");
		this.taxValue = new Label("12.99");
		taxBox.getChildren().addAll(tax, spacer(),taxValue);

		HBox spacerBox = new HBox();
		spacerBox.getChildren().add(spacer());
		spacerBox.setPrefHeight(40);

		HBox totalBox = new HBox();
		Label total = new Label("Total:");
		this.totalValue = new Label("25.98");
		totalBox.getChildren().addAll(total, spacer(),totalValue);

		HBox buttonBox = new HBox();
		Button clearButton = new Button("Clear Cart");
		clearButton.setPrefHeight(40);

		HBox spacerBox2 = new HBox();
		spacerBox2.getChildren().add(spacer());
		spacerBox2.setPrefHeight(80);



		Button purchaseButton = new Button("Make Purchase");
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
