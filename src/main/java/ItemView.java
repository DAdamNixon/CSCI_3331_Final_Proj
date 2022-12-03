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

    // List of items related to the current search query
    private Collection<Merchandise> items;
    MainPage mainPage;

    public ItemView(MainPage main) {
        this.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        this.mainPage = main;
        this.setAlignment(Pos.CENTER);
        this.items = main.getInventory().values();
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
            getChildren().add(InventoryController.card(merchandise, mainPage.getCart()));
        }
    }

    // Adds items to view based on search terms
    public void search(String searchTerms) {
        if (searchTerms.equals("")) {
            makeCards();
        } else {
            getChildren().clear();
            for (Merchandise item : this.items) {
                if (parse(searchTerms, item.toString())) {
                    getChildren().add(InventoryController.card(item, mainPage.getCart()));
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
