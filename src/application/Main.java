package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Main extends Application {
	
	private static Stage window;
	
	@FXML
	private Button login;
	// private Button validate;
	
	@FXML
	protected static void sceneRedirect_mainWindow() throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("MainWindow.fxml"));
		Scene scene = new Scene(root, 500, 600);
		window.setScene(scene);
		window.setTitle("Kitap");
	}
	
	@FXML
	protected static void sceneRedirect_register() throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("Register.fxml"));
		Scene scene = new Scene(root, 500, 600);
		window.setScene(scene);
		window.setTitle("Kayýt Ol");
	}
	
	@FXML
	protected static void sceneRedirect_logIn() throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("LogIn.fxml"));
		Scene scene = new Scene(root, 500, 600);
		window.setScene(scene);
		window.setTitle("Giriþ");
	}

	@FXML
	protected static void sceneRedirect_firstLogIn() throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("FirstLogIn.fxml"));
		Scene scene = new Scene(root, 500, 600);
		window.setScene(scene);
		window.setTitle("Oylama");
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root, 749, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.setTitle("Giriþ");
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
