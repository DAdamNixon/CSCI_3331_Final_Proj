import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class CardBuilder implements Builder<Region> {

    Merchandise model;

    public CardBuilder(Merchandise model) {
        this.model = model;
    }

    @Override
    public Region build() {
        VBox card = new VBox();
        card.setPadding(new Insets(3));
        card.setMinHeight(180);
        card.setMinWidth(180);
        card.getChildren().addAll(
                new Label(model.getItemName()),
                new Label(String.format("%.2f", model.getPrice())));
        return card;
    }

    
}
