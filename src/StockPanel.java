import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class StockPanel extends HBox {
	

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
		if (inStock != 0) {
			addToCart.disableProperty().set(true);
		}
		
		getChildren().addAll(stockLabel, spacer, addToCart);
	}
}
