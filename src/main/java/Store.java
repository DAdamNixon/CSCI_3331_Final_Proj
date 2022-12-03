package main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Store extends Application {
	Stage stage;
	Scene main;
	MainPage mainPage;
	Scene cart;
	Scene login;
	InventoryController inventory;
	HashMap<String, String> users;

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

	public static void main(String[] args) throws Exception {
		launch(args);
	}

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
