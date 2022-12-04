package main.java;

import java.util.Collection;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

// ItemView is a GUI interface for displaying the items that are purchaseable from the store
public class ItemView extends FlowPane {

    // Collection of the items that are purchaseable from the store
    private Collection<Merchandise> items;
    MainPage mainPage;

    // Cart
    Cart cart;

    // Constructor
    // Sets up the UI and builds a card for each item in the store
    public ItemView(Cart cart, Collection<Merchandise> merch) {
        this.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        this.items = merch;
        this.cart = cart;
        makeCards();
    }

    // Clears all card in the view, then makes a new set
    public void makeCards() {
        getChildren().clear();
        for (Merchandise merchandise : this.items) {
            getChildren().add(InventoryController.card(merchandise, this.cart));
        }
    }

    // Calls makeCards() on an empty search bar, or makes cards for items containing
    // the search terms
    public void search(String searchTerms) {
        if (searchTerms.equals("")) {
            makeCards();
        } else {
            getChildren().clear();
            for (Merchandise item : this.items) {
                if (parse(searchTerms, item.toString())) {
                    getChildren().add(InventoryController.card(item, this.cart));
                }
            }
        }
    }

    // Helper method for the search function
    private boolean parse(String text, String item) {
        String query = text.toLowerCase();
        String itemInfo = item.toLowerCase();
        String[] buffer = query.split(" ");
        Boolean flag = false;

        for (String string : buffer) {
            if (itemInfo.contains(string)) {
                flag = true;
            }
        }

        return flag;
    }
}
