package main.java;
import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class ItemView extends FlowPane {

    // List of items related to the current search query
    private Collection<Merchandise> items;
    SearchController searching;
    MainPage mainPage;

    public ItemView(MainPage main) {
        this.mainPage = main;
        this.setAlignment(Pos.CENTER);
        this.items = main.getInventory().values();
        searching = new SearchController();
		makeCards();
    }

    public void addItem(Merchandise newItem) {
        this.items.add(newItem);
    }

    public void removeItem(Merchandise item) {
        this.items.remove(item);
    }

    private void makeCards() {
        getChildren().clear();
        for (Merchandise merchandise : this.items) {
            getChildren().add(new CardBuilder(merchandise, mainPage.getCart()).build());
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
                    getChildren().add(new CardBuilder(item, mainPage.getCart()).build());
                }
            }
        }
    }
}