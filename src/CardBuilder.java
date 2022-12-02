import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Builder;

public class CardBuilder implements Builder<Region> {

    Merchandise model;

    public CardBuilder(Merchandise model) {
        this.model = model;
    }

    @Override
    public Region build() {
        VBox card = new VBox();
        card.setPadding(new Insets(15));
        card.setMinHeight(180);
        card.setMinWidth(180);
        card.getChildren().addAll(
                new Label(model.getItemName()),
                new Label(String.format("%.2f", model.getPrice())),
				new ImageView("flour.png"));
        card.setBackground(new Background(new BackgroundFill(Color.WHITE , new CornerRadii(10), new Insets(10))));
        return card;
    }

    
}
