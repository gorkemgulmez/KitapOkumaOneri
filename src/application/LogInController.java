package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController{
	
	@FXML private static TextField username;
	@FXML private static PasswordField password;
	@FXML private static TextField ID;
	@FXML private static TextField adress;
	@FXML private static Button logInButton;
	@FXML private static Button correctionButton;
	
	
	public void logInAction() {
		System.out.println("log in action method");
		if( username == null || password == null) {
			return;
		}

		//if(true) //TODO fix true condition
			Main.sceneRedirect_firstLogIn();
		//else 
			//Main.sceneRedirect_mainWindow();
	}
	
	
	public void correctionAction() {
		Main.sceneRedirect_register();
	}
	
}
