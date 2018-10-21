package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {

	@FXML private static TextField username;
	@FXML private static PasswordField password;
	@FXML private static Text ID = new Text("ID: ");
	@FXML private static Text adress = new Text("Adres: ");
	
	@FXML
	protected static void registerConfirm() throws IOException {
		if( username == null || password == null) {
			System.out.println("Hata");
			Main.sceneRedirect_logIn();
			return;
		}
		//else
		Main.sceneRedirect_firstLogIn();
	}
	
}
