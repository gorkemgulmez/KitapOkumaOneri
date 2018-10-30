package controller;

import static controller.YazLab1.window;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserRegisterModel;

public class RegisterController {

	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private TextField address;
	@FXML private TextField age;
	
	public void registerAction() {
		if( username.getText().equals("") || password.getText().equals("") 
				|| address.getText().equals("") || age.getText().equals("") ) {
			new ErrorMessage("Butun Alanlarin Doldurulmasi Gerekmektedir");
			return;
		}
		
		UserRegisterModel user = new UserRegisterModel(username.getText(), password.getText(), address.getText(), age.getText());
		int userId = Database.createUser(user);
		if(userId == -1) {
			new ErrorMessage("Kayit olurken hata olustu");
			return;
		}
		
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainWindow.fxml"));
			loader.load();
			
			MainWindowController controller = loader.getController();
			controller.setUserID(userId);
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("Kayit Ol");
		} catch (IOException e) {
			System.out.println("Could not load MainWindow.fxml");
			e.printStackTrace();
		}
		
	}
	
	public void backRedirect() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("LogIn.fxml"));
			loader.load();
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("Kayit Ol");
		} catch (IOException e) {
			System.out.println("Could not load LogIn.fxml");
			e.printStackTrace();
		}

	}

}
