import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class ItemView extends FlowPane {

    // List of items related to the current search query
    private Collection<Merchandise> items;
    SearchController searching;
    MainPage mainPage;

    public ItemView(MainPage main) {
        mainPage = main;
        main.setCenter(this);
        this.setAlignment(Pos.CENTER);
        this.items = main.getInventory().values();
        searching = new SearchController();
		makeCards();
    }

    // How are we going to display items from search?
    public void addItem(Merchandise newItem) {
        this.items.add(newItem);
    }

    public void removeItem(Merchandise item) {
        this.items.remove(item);
    }

    private void makeCards() {
        getChildren().clear();
        for (Merchandise merchandise : this.items) {
            getChildren().add(new CardBuilder(merchandise).build());
        }
    }

    public void search(String searchTerms) {
        if (searchTerms.equals("")) {
            makeCards();
        }
        else {
            getChildren().clear();
            for (Merchandise item : this.items) {
                if (searching.parse(searchTerms, item.toString())) {
                    getChildren().add(new CardBuilder(item).build());
                }
            }
        }
    }
}
