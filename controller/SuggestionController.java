package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.BookModel;

public class SuggestionController {

	@FXML private ImageView imgview1;
	@FXML private ImageView imgview2;
	@FXML private ImageView imgview3;
	@FXML private Label text1;
	@FXML private Label text2;
	@FXML private Label text3;
	
	public static BookModel[] books = new BookModel[3]; 
	
	public void initialize() {
		text1.setText(books[0].getName());
		text2.setText(books[1].getName());
		text3.setText(books[2].getName());
		
		new Thread(new Runnable() {
			public void run() {
				Image img = new Image(books[0].getImageLink());
				if (img.getHeight() == 1.0) img = new Image("file:..\\..\\resource\\no-image.png"); 
				imgview1.setImage(img);
				
				Image img2 = new Image(books[1].getImageLink());
				if (img2.getHeight() == 1.0) img2 = new Image("file:..\\..\\resource\\no-image.png"); 
				imgview2.setImage(img2);
				
				Image img3 = new Image(books[2].getImageLink());
				if (img3.getHeight() == 1.0) img3 = new Image("file:..\\..\\resource\\no-image.png"); 
				imgview3.setImage(img3);
			}
		}).start();
		
	}
	
}
