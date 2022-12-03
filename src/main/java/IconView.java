package main.java;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class IconView extends HBox {
	
	IconView(String filename) {
		ImageView img = new ImageView(filename);
		img.setFitHeight(100);
		img.setFitWidth(100);
		this.getChildren().add(img);
		this.setAlignment(Pos.CENTER);
	}
}
