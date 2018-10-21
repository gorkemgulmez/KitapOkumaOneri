package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private static Stage window;
	
	protected static void sceneRedirect_mainWindow() {
		Parent root;
		try {
			root = FXMLLoader.load(Main.class.getResource("MainWindow.fxml"));
			Scene scene = new Scene(root, 500, 600);
			window.setScene(scene);
			window.setTitle("Kitap");
		} catch (IOException e) {
			System.out.println("Could not found MainWindow.fxml");
			e.printStackTrace();
		}
	}
	
	protected static void sceneRedirect_register() {
		Parent root;
		try {
			root = FXMLLoader.load(Main.class.getResource("Register.fxml"));
			Scene scene = new Scene(root, 500, 600);
			window.setScene(scene);
			window.setTitle("Kayýt Ol");
		} catch (IOException e) {
			System.out.println("Could not found Register.fxml");
			e.printStackTrace();
		}
	}
	
	protected static void sceneRedirect_logIn() {
		Parent root;
		try {
			root = FXMLLoader.load(Main.class.getResource("LogIn.fxml"));
			Scene scene = new Scene(root, 500, 600);
			window.setScene(scene);
			window.setTitle("Giriþ");
		} catch (IOException e) {
			System.out.println("Could not found LogIn.fxml");
			e.printStackTrace();
		}
		
	}

	public static void sceneRedirect_firstLogIn() {
		Parent root;
		try {
			root = FXMLLoader.load(Main.class.getResource("FirstLogIn.fxml"));
			Scene scene = new Scene(root, 500, 600);
			window.setScene(scene);
			window.setTitle("Oylama");
		} catch (IOException e) {
			System.out.println("Could not found FirstLogIn.fxml");
			e.printStackTrace();
		}
		
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
