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

public class LogInController implements Initializable {
    
	@FXML private TextField username;
	@FXML private PasswordField password;
	
	public void logInAction() {
		if( username.getText().equals("") || password.getText().equals("") ) {
			new ErrorMessage("Lütfen bilgileri eksiksiz doldurun");
			return;
		}
		//if( !Database.isUserExist(username.getText, password.getText)) {
		//new ErrorMessage("HatalÄ± KullanÄ±cÄ± AdÄ± ve ya Å�ifre");
		//return;
		//} 
		//if() //TODO Database.isUserVoted(username.getText()) {
			//if() //normal user
				Parent root;
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("MainWindow.fxml"));
					loader.load();
					
					root = loader.getRoot();
					Scene scene = new Scene(root);
					
					window.setScene(scene);
					window.setTitle("Kitap Okuma");
				} catch (IOException e) {
					System.out.println("Could not load MainWindow.fxml");
					e.printStackTrace();
				}
			//else {}
				/*Parent root;
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("AdminEdit.fxml"));
					loader.load();
					
					root = loader.getRoot();
					Scene scene = new Scene(root, 500, 600);
					
					window.setScene(scene);
					window.setTitle("KayÄ±t Ol");
				} catch (IOException e) {
					System.out.println("Could not load AdminEdit.fxml");
					e.printStackTrace();
				}
	
		//}
			
		//else {
				Parent root;
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("RateBook.fxml"));
					loader.load();
					
					root = loader.getRoot();
					Scene scene = new Scene(root, 500, 600);
					
					window.setScene(scene);
					window.setTitle("KayÄ±t Ol");
				} catch (IOException e) {
					System.out.println("Could not load RateBook.fxml");
					e.printStackTrace();
				}*/
		//}
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
