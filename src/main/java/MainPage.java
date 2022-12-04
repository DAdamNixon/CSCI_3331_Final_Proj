package main.java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


// MainPage is a GUI used in conglomeration with ItemView and Cart to display the list
// of available items, the cart, and the checkout window. This interface also contains the Search Bar
public class MainPage extends BorderPane {

    // Fields
    private TextField search;
    private User currentUser;

    // Constructor
    // Builds the components and sets them into the MainPage
    public MainPage() {
        setLayout();
    }

    // Returns the text a user entered into the search bar
    public String getSearchText() {
        return search.getText();
    }

    // Sets the current user. Called during loginAttempt()
    public void setUser(User user) {
        this.currentUser = user;
    }

    // Returns the current user
    public User getUser() {
        return this.currentUser;
    }

    // Builds the logical components and GUI components of the store
    private void setLayout() {
        this.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        InventoryController invCont = new InventoryController();
        buildSearch(buildItemView(buildCart(invCont), invCont));
    }

    // Builds and returns the cart to pass as a reference
    private Cart buildCart(InventoryController iController) {
        Cart cart = new Cart(this, iController);
        this.setRight(cart.getView());
        return cart;
    }

    // Builds and returns the ItemView to pass as a reference
    private ItemView buildItemView(Cart cart, InventoryController iController) {
        ItemView view = new ItemView(cart, iController.values());
        iController.setItemView(view);
        ScrollPane scroll = new ScrollPane(view);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> bounds, Bounds oldBounds, Bounds newBounds) {
                view.setPrefWidth(newBounds.getWidth());
            }
        });

        this.setCenter(scroll);

        return view;
    }

    // Builds the search bar
    private void buildSearch(ItemView view) {
        HBox searchBox = new HBox();
        this.search = new TextField();
        this.search.setPromptText("Keywords...");
        Label lblSearch = new Label("Search: ");
        ImageView image = new ImageView(Resources.imagePath("searchGlass.png"));
        image.setPreserveRatio(true);
        image.setFitHeight(11);
        image.setFitWidth(11);
        Button btnSearch = new Button("", image);
        btnSearch.setOnAction(e -> {
            view.search(this.search.getText());
        });
        searchBox.getChildren().addAll(lblSearch, search, btnSearch);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        this.setTop(searchBox);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(15, 12, 15, 12));
        searchBox.setSpacing(10);
    }
}
