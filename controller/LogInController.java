package controller;

import static controller.YazLab1.window;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LogInController {
    
	@FXML private TextField username;
	@FXML private PasswordField password;
	
	public void logInAction() {
		if( username.getText().equals("") || password.getText().equals("") ) {
			new ErrorMessage("Lütfen bilgileri eksiksiz doldurun");
			return;
		}
		if( !Database.isUserExist(username.getText(), password.getText())) {
			new ErrorMessage("Hatali Kullanici Adi ve ya Sifre");
			return;
		} 
		
		int userId = Database.getUserId(username.getText());
		
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainWindow.fxml"));
			loader.load();
			root = loader.getRoot();
			
			MainWindowController controller = loader.<MainWindowController>getController();
			controller.setUserID(userId);
			
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("Kitap Okuma");
		} catch (IOException e) {
			System.out.println("Could not load MainWindow.fxml");
			e.printStackTrace();
		}
			
	}
	
	public void registerRedirect() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Register.fxml"));
			loader.load();
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("KayÄ±t Ol");
		} catch (IOException e) {
			System.out.println("Could not load Register.fxml");
			e.printStackTrace();
		}
			
	}
	
}
