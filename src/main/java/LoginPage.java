package main.java;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// LoginPage is a GUI used to allow a user to sign into the application
public class LoginPage extends BorderPane {

	// Fields
	private Store mainStore;
	private TextField username;
	private TextField password;
	private Button btnLogin;
	private Button btnQuit;

	// Constructor
	LoginPage(Store main) {
		this.mainStore = main;
		this.username = new TextField();
		this.password = new PasswordField();
		this.btnLogin = new Button("Login");
		this.btnQuit = new Button("Quit");
		this.setBackground(new Background(
				new BackgroundImage(new Image(Resources.imagePath("login.png")), null, null, null, null)));
		btnLogin.setOnAction(e -> {
			this.mainStore.loginAttempt(username.getText(), password.getText());
		});

		btnQuit.setOnAction(e -> {
			System.exit(0);
		});
		setLoginCard();

	}

	// Sets the GUI layout and design
	private void setLoginCard() {
		VBox container = new VBox();
		Region spacer = new Region();
		spacer.setPrefHeight(150);
		GridPane gp = new GridPane();
		container.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(260, 100, 100, 100))));
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(btnLogin, btnQuit);
		gp.add(new Label("Login"), 1, 0);
		gp.add(new Label("Username: "), 0, 1);
		gp.add(username, 1, 1);
		gp.add(new Label("Password: "), 0, 2);
		gp.add(password, 1, 2);
		gp.add(buttonBox, 1, 3);
		container.setAlignment(Pos.CENTER);
		gp.setAlignment(Pos.CENTER);
		container.getChildren().addAll(spacer, gp);
		setCenter(container);
	}
}
