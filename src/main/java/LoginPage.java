package main.java;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginPage extends BorderPane {

	private Store mainStore;
	private TextField username;
	private TextField password;
	private Button btnLogin;
	private Button btnQuit;

	LoginPage(Store main) {

		this.mainStore = main;
		this.username = new TextField();
		this.password = new TextField();
		this.btnLogin = new Button("Login");
		this.btnQuit = new Button("Quit");
		GridPane gp = new GridPane();
		IconView icon = new IconView(Resources.makePath("flour.png"));
		this.setTop(icon);

		btnLogin.setOnAction(e -> {
			this.mainStore.loginAttempt(username.getText(), password.getText());
		});

		btnQuit.setOnAction(e -> {
			System.exit(0);
		});

		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(btnLogin, btnQuit);
		gp.add(new Label("Login"), 1, 0);
		gp.add(new Label("Username: "), 0, 1);
		gp.add(username, 1, 1);
		gp.add(new Label("Password: "), 0, 2);
		gp.add(password, 1, 2);
		gp.add(buttonBox, 1, 3);
		gp.setAlignment(Pos.CENTER);
		setCenter(gp);
	}
}
