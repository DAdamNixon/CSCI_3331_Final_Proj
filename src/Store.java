import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Store extends Application {
	Stage stage;
	Scene main;
	Scene cart;
	Scene login;
    InventoryController inventory;
	HashMap<String,String> users;
	User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
		this.users = new HashMap<>();
		loadUserBase();
		this.stage = primaryStage;
		this.login = new Scene(new LoginPage(this), 500, 500);
		this.main = new Scene(new BorderPane(), 800, 800);
        primaryStage.setScene(login);
		primaryStage.show();
    }

    private void loadUserBase() {
		try (Scanner input = new Scanner(new File("./src/Users.csv"))) {
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
		if (password.equals(users.get(username))) {
			currentUser = new Customer(username);
			stage.setScene(main);
			stage.show();
		}
	}
}
