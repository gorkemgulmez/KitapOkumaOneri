package controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorMessage {

	public ErrorMessage(String message) {
		Stage window = new Stage();
		AnchorPane root = new AnchorPane();
		Scene scene = new Scene(root);
		
		Label text = new Label(message);
		text.setPadding(new Insets(30, 25, 20, 25));
		root.getChildren().add(text);
		
		window.setScene(scene);
		window.setTitle("Hata");
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();
	}
}
