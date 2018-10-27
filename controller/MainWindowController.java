package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

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

	@FXML private TableView<BookModel> bookTablePopular;
	@FXML private TableColumn<BookModel, String> isbnCP;
	@FXML private TableColumn<BookModel, String> nameCP;
	@FXML private TableColumn<BookModel, String> authorCP;
	@FXML private TableColumn<BookModel, String> dateCP;
	@FXML private TableColumn<BookModel, String> publisherCP;
	
	@FXML private TableView<BookModel> bookTableBest;
	@FXML private TableColumn<BookModel, String> isbnCB;
	@FXML private TableColumn<BookModel, String> nameCB;
	@FXML private TableColumn<BookModel, String> authorCB;
	@FXML private TableColumn<BookModel, String> dateCB;
	@FXML private TableColumn<BookModel, String> publisherCB;
	
	@FXML private ImageView imgView;
	@FXML private Text bookNameT;
	@FXML private ChoiceBox<Integer> rateBox;
	@FXML private ChoiceBox<String> adminBox;
	@FXML private Button readButton;
	
	private ObservableList<BookModel> books = FXCollections.observableArrayList();
	private ObservableList<BookModel> booksPopular = FXCollections.observableArrayList();
	private ObservableList<BookModel> booksBest = FXCollections.observableArrayList();
	
	private ObservableList<Integer> rateList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	private ObservableList<String> adminList = FXCollections.observableArrayList("Ekle", "Sil", "Duzenle");
	
	private BookModel lastSelectedmodel;
	
	public void initialize() {
		///Book Table Init
		isbnC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		//dateC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publish_date"));
		publisherC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		isbnCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		//dateCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publish_date"));
		publisherCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		isbnCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		//dateCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publish_date"));
		publisherCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		Database.getBookData(books);
		bookTable.setItems(books);
		
		Database.getBookData(booksPopular);
		bookTablePopular.setItems(booksPopular);
		
		Database.getBookData(booksBest);
		bookTableBest.setItems(booksBest);
		
		///ChoiceBox
		rateBox.setItems(rateList);
		adminBox.setItems(adminList);
		
		///Table Listeners
		bookTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookModel>() {
			@Override
			public void changed(ObservableValue<? extends BookModel> arg0, BookModel arg1, BookModel arg2) {
				setSelectedItemsProp(bookTable);
			}
			
		});
		bookTablePopular.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookModel>() {

			@Override
			public void changed(ObservableValue<? extends BookModel> arg0, BookModel arg1, BookModel arg2) {
				setSelectedItemsProp(bookTablePopular);
			}
			
		});
		bookTableBest.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookModel>() {

			@Override
			public void changed(ObservableValue<? extends BookModel> arg0, BookModel arg1, BookModel arg2) {
				setSelectedItemsProp(bookTableBest);
			}
			
		});
	}
	
	public void setSelectedItemsProp(TableView<BookModel> t) {
		BookModel model = t.getSelectionModel().getSelectedItem();
		lastSelectedmodel = model;
		
		bookNameT.setText(model.getName());
		Image img = new Image(model.getImageLink());
        imgView.setImage(img);
        readButton.setDisable(false);
	}
	
	public void readPDF() {
		if(Desktop.isDesktopSupported()) {
			try {
				Random rand = new Random();
				File pdf = new File("file:..\\..\\resource\\pdf" + Integer.toString(rand.nextInt(5)+1) + ".pdf");
				Desktop.getDesktop().open(pdf);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void vote() {
		int rate = rateBox.getSelectionModel().getSelectedIndex()+1;
		//Database.VoteBook(userId, bookId, rate);
	}
	
	public void handleAdminButton() {
		int choice = adminBox.getSelectionModel().getSelectedIndex();
		if(choice == 0) {
			//ekle
		}
		else if(choice == 1) {
			Database.deleteBook(lastSelectedmodel.getIsbn());
		}
		else {
			//d�zelt
		}
	}
	
}
