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
    private Label lblPad;

    public MainPage() {
        this.lblPad = new Label("   ");
        this.search = new TextField();
        this.search.setPromptText("Keywords...");
        this.lblSearch = new Label("Search: ");
        this.searchBox = new HBox();
        ImageView image = new ImageView(new Image("searchGlass.png"));
        image.setPreserveRatio(true);
        image.setFitHeight(11);
        image.setFitWidth(11);
        this.btnSearch = new Button("", image);
        setLayout();
    }

    public String getSearchText() {
        return search.getText();
    }

    private void setLayout() {
        this.searchBox.getChildren().addAll(lblSearch, search, lblPad, btnSearch);
        this.setTop(searchBox);
        this.searchBox.setAlignment(Pos.CENTER);
    }
}
