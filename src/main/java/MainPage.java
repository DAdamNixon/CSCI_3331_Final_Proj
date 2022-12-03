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
    private InventoryController invCont;
    private TextField search;
    private Label lblSearch;
    private HBox searchBox;
    private Button btnSearch;
    private ItemView view;
    private Cart cart;
    private User currentUser;

    public MainPage() {
        this.setBackground(new Background(new BackgroundFill(Color.LAVENDER,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.invCont = new InventoryController();
        this.cart = new Cart(this, invCont);
        this.searchBox = new HBox();
        this.search = new TextField();
        this.search.setPromptText("Keywords...");
        this.lblSearch = new Label("Search: ");
        ImageView image = new ImageView(Resources.imagePath("searchGlass.png"));
        image.setPreserveRatio(true);
        image.setFitHeight(11);
        image.setFitWidth(11);
        this.btnSearch = new Button("", image);
        view = new ItemView(this);
        btnSearch.setOnMouseClicked(e -> {
            view.search(this.search.getText());
        });
        setLayout();
    }

    // Returns the search bar text
    public String getSearchText() {
        return search.getText();
    }

    // Returns the inventory controller
    public InventoryController getInventory() {
        return this.invCont;
    }

    // Setter for the user
    public void setUser(User user) {
        this.currentUser = user;
    }

    // Returns the current user
    public User getUser() {
        return this.currentUser;
    }

    // Returns the current cart
    public Cart getCart() {
        return this.cart;
    }

    // Helps the constructor setup the layout
    private void setLayout() {
        ScrollPane scroll = new ScrollPane(view);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> bounds, Bounds oldBounds, Bounds newBounds) {
                view.setPrefWidth(newBounds.getWidth());
            }
        });

        this.setCenter(scroll);
        this.searchBox.getChildren().addAll(lblSearch, search, btnSearch);
        this.searchBox.setAlignment(Pos.CENTER_RIGHT);
        this.setTop(searchBox);
        this.searchBox.setAlignment(Pos.CENTER);
        this.searchBox.setPadding(new Insets(15, 12, 15, 12));
        this.searchBox.setSpacing(10);
        this.setRight(cart.getView());
    }
}
