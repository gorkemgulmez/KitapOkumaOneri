package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {

	@FXML
	private static TextField username;
	@FXML
	private static PasswordField password;
	@FXML
	private static TextField ID, adress;
	
	@FXML
	public static void logInAction() {
		if( username == null || password == null) {
			return;
		}
		try {
			if(true) //TODO fix true condition
				Main.sceneRedirect_firstLogIn();
			else 
				Main.sceneRedirect_mainWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
