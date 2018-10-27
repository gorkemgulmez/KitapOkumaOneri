package controller;

import static controller.YazLab1.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController implements Initializable {

	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private TextField address;
	@FXML private TextField age;
	
	public void registerAction() {
		if( username.getText().equals("") || password.getText().equals("") 
				|| address.getText().equals("") || age.getText().equals("") ) {
			new ErrorMessage("Bütün Alanlarýn Doldurulmasý Gerekmektedir");
			return;
		}
		//if() //Database.isUserExist(string username)
			//new ErrorMessage("Kullanýcý adý alýnmýþ");
			//return;
		
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RateBook.fxml"));
			loader.load();
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("Kayýt Ol");
		} catch (IOException e) {
			System.out.println("Could not load RateBook.fxml");
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
			window.setTitle("Kayýt Ol");
		} catch (IOException e) {
			System.out.println("Could not load LogIn.fxml");
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
