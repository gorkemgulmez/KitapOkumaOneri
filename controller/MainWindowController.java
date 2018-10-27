package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BookModel;

public class MainWindowController {

	@FXML private TableView<BookModel> bookTable;
	@FXML private TableColumn<BookModel, String> isbnC;
	@FXML private TableColumn<BookModel, String> nameC;
	@FXML private TableColumn<BookModel, String> authorC;
	@FXML private TableColumn<BookModel, String> dateC;
	@FXML private TableColumn<BookModel, String> publisherC;
	@FXML private ImageView imgView;
	@FXML private Text bookNameT;
	@FXML private ChoiceBox<Integer> rateBox;
	@FXML private Button readButton;
	
	private ObservableList<BookModel> books = FXCollections.observableArrayList();
	private ObservableList<Integer> rateList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	
	public void initialize() {
		//Book Table Init
		isbnC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		//dateC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publish_date"));
		publisherC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		Database.getBookData(books);
		bookTable.setItems(books);
		
		//ChoiceBox
		rateBox.setItems(rateList);
		
		//Table Listeners
		bookTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookModel>() {

			@Override
			public void changed(ObservableValue<? extends BookModel> arg0, BookModel arg1, BookModel arg2) {
				bookNameT.setText(bookTable.getSelectionModel().getSelectedItem().getName());
				String link = bookTable.getSelectionModel().getSelectedItem().getImageLink();
				Image img = new Image(link);
		        imgView.setImage(img);
		        
		        readButton.setDisable(false);
			}
			
		});
		
	}
	
	public void readPDF() {
		if(Desktop.isDesktopSupported()) {
			try {
				File pdf = new File("file:..\\..\\pdf1.pdf");
				Desktop.getDesktop().open(pdf);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void vote() {
		
	}
	
}
