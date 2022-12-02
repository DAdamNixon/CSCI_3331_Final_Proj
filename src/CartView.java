import java.util.LinkedList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CartView extends VBox {
	ScrollPane scroll;
	FlowPane container;

	public CartView(){
		
		this.container = new FlowPane();
		this.scroll = new ScrollPane(this.container);
		this.scroll.setPrefHeight(500);
		this.scroll.setFitToWidth(true);
		this.setPrefWidth(200);
		getChildren().add(scroll);
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
	}
}
