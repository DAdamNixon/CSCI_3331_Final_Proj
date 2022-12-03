package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Store is the main class of the application.

public class Store extends Application {

	// Fields
	Stage stage;
	Scene main;
	MainPage mainPage;
	Scene cart;
	Scene login;
	HashMap<String, String> users;

	// Application start() method. Called once at execution
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.users = new HashMap<>();
		loadUserBase();

		this.stage = primaryStage;
		this.login = new Scene(new LoginPage(this), 500, 500);
		this.mainPage = new MainPage();
		this.main = new Scene(mainPage, 780, 700);
		primaryStage.setScene(login);
		primaryStage.show();
	}

	// Reads the user.csv file in and builds the users HashMap for login purposes
	private void loadUserBase() {
		try (Scanner input = new Scanner(new File(Resources.dataPath("Users.csv")))) {
			String[] line;
			while (input.hasNextLine()) {
				line = input.nextLine().split(",");
				users.put(line[0], line[1]);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// Main method
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	// Attempts a login with the credentials in the login box
	public void loginAttempt(String username, String password) {
		User user;
		if (password.equals(users.get(username)) || username.equals("")) {
			user = new Customer(username);
			mainPage.setUser(user);
			stage.setScene(main);
			stage.show();
		}
	}
}
