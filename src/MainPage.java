import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainPage extends BorderPane {

    private TextField search;
    private Label lblSearch;
    private HBox searchBox;
    private Button btnSearch;
    private ItemView view = new ItemView(this);

    public MainPage() {
        this.searchBox = new HBox();
        this.search = new TextField();
        this.search.setPromptText("Keywords...");
        this.lblSearch = new Label("Search: ");
        ImageView image = new ImageView(new Image("searchGlass.png"));
        image.setPreserveRatio(true);
        image.setFitHeight(11);
        image.setFitWidth(11);
        this.btnSearch = new Button("", image);
        btnSearch.setOnMouseClicked(e -> {
            view.search();
        });
        setLayout();
        view.addItem(new GroceryItem("Flour", 2.96f));
        view.makeCards();
    }

    public String getSearchText() {
        return search.getText();
    }

    private void setLayout() {
        this.setCenter(view);
        this.searchBox.getChildren().addAll(lblSearch, search, btnSearch);
        this.searchBox.setAlignment(Pos.CENTER_RIGHT);
        this.setTop(searchBox);
        this.searchBox.setAlignment(Pos.CENTER);
        this.searchBox.setPadding(new Insets(15, 12, 15, 12));
        this.searchBox.setSpacing(10);

    }
}
