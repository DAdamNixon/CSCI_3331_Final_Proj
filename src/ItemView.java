import java.util.LinkedList;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class ItemView extends FlowPane {

    // List of items related to the current search query
    private LinkedList<Merchandise> items;


    public ItemView(MainPage main) {
        main.setCenter(this);
        this.setAlignment(Pos.CENTER);
        this.items = new LinkedList<>();
    }

    // How are we going to display items from search?
    public void addItem(Merchandise newItem) {
        this.items.add(newItem);
    }

    public void removeItem(Merchandise item) {
        this.items.remove(item);
    }

    public void makeCards(){
        for (Merchandise merchandise : items) {
            getChildren().add(new CardBuilder(merchandise).build());
        }
    }
}
