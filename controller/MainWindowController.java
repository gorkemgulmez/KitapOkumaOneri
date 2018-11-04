package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.BookModel;

public class MainWindowController {

	@FXML private Pagination pagination;
	@FXML private Tab bestTab;
	@FXML private Tab popTab;
	
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
	@FXML private Button adminButton;
	@FXML private Button voteButton;
	@FXML private Button suggestButton;
	@FXML private Text remainingText;
	
	private ObservableList<BookModel> books = FXCollections.observableArrayList();
	private ObservableList<BookModel> booksPopular = FXCollections.observableArrayList();
	private ObservableList<BookModel> booksBest = FXCollections.observableArrayList();
	private ObservableList<Integer> rateList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	private ObservableList<String> adminList = FXCollections.observableArrayList("Ekle", "Sil", "Kullanici Ekle", "Kullanici Sil");
	
	private BookModel lastSelectedModel;
	private int userID;
	private int numberOfBooks = Database.getNumberofBooks();
	private int rowsPerPage = 17, from=0, to=0;
	private boolean popularDatabaseFlag = false;
	private boolean bestDatabaseFlag = false;
	
	public void setUserID(int id) {
		userID = id;
		System.out.println("(Main)userid: " + userID);
	}
	
	private Node createPage(int pageIndex) {
		from = pageIndex * rowsPerPage;
		to = Math.min(from + rowsPerPage, books.size());
		System.out.println("to: " + to);
		Database.getBooks(books, pageIndex, rowsPerPage);
		bookTable.setItems(books);
		return bookTable;
	}
	
	@FXML public void initialize() {
		UBCF.setUser_id(userID);
		
		pagination.setPageCount((numberOfBooks/rowsPerPage) + 1);
		pagination.setPageFactory(this::createPage);
		
		///Book Table Init
		isbnC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		dateC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("date"));
		publisherC.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		isbnCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		dateCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("date"));
		publisherCP.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		isbnCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("isbn"));
		nameCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("name"));
		authorCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
		dateCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("date"));
		publisherCB.setCellValueFactory(new PropertyValueFactory<BookModel, String>("publisher"));
		
		///ChoiceBox
		rateBox.setItems(rateList);
		adminBox.setItems(adminList);		

		adminBox.setVisible(false);
		adminButton.setVisible(false);
		
		Platform.runLater(() -> {
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
			
			bestTab.setOnSelectionChanged(new EventHandler<Event>() {
				
				@Override
				public void handle(Event arg0) {
					if(booksBest.isEmpty() && bestDatabaseFlag == false) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								bestDatabaseFlag = true;
								System.out.println("Best thread started");
								Database.getBestBooks(booksBest);
								bookTableBest.setItems(booksBest);
								System.out.println("Best thread end");
							}
						}).start();
					}
				}
			});
			
			popTab.setOnSelectionChanged(new EventHandler<Event>() {
				
				@Override
				public void handle(Event arg0) {
					if(booksPopular.isEmpty() && popularDatabaseFlag == false) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								popularDatabaseFlag = true;
								System.out.println("Popular thread started");
								Database.getPopularBooks(booksPopular);
								bookTablePopular.setItems(booksPopular);
								System.out.println("Popular thread ended");
							}
						}).start();
					}
				}
			});
			
			bookTable.getSelectionModel().selectFirst();
		});
	}
	
	public void setSelectedItemsProp(TableView<BookModel> t) {
		BookModel model = t.getSelectionModel().getSelectedItem();
		lastSelectedModel = model;
		
		try {
			bookNameT.setText(model.getName());
			Thread t1 = new Thread(setImage(model));
			t1.start();
	        
			readButton.setDisable(false);
	        voteButton.setDisable(false);
		}catch (Exception e) {
			System.out.println("null");
		}
		

        int remainingBook = Database.userVotedBook(userID);
		if(remainingBook < 10 && userID!=1) {
			
			remainingText.setText("Kalan Kitap: " + (10-remainingBook));
			suggestButton.setVisible(false);
		}
		
		if(userID == 1) {
			adminBox.setVisible(true);
			adminButton.setVisible(true);
			remainingText.setVisible(false);
		}
	}
	
	public Runnable setImage(BookModel model) {
		return new Runnable() {
			public void run() {
				Image img = new Image(model.getImageLink());
				if (img.getHeight() == 1.0) img = new Image("file:..\\..\\resource\\no-image.png"); 
				imgView.setImage(img);
			}
		};
		
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
		Database.VoteBook(userID, lastSelectedModel.getIsbn(), rate);
		int remainingBook = Database.userVotedBook(userID);
		if(remainingBook < 10) 
			remainingText.setText("Kalan Kitap: " + (10-remainingBook));
		else {
			remainingText.setVisible(false);
			suggestButton.setVisible(true);
		}
	}
	
	public void handleAdminButton() {
		int choice = adminBox.getSelectionModel().getSelectedIndex();

		if(choice == 0) {
			Parent root;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddBook.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Stage win = new Stage();
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			win.setScene(scene);
			win.setTitle("Kitap Ekle");
			win.show();
		}
		else if(choice == 1) {
			Database.deleteBook(lastSelectedModel.getIsbn());
			books.remove(books.indexOf(lastSelectedModel));
		}
		else if(choice == 2) {
			Parent root;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddUser.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Stage win = new Stage();
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			win.setScene(scene);
			win.setTitle("Kullanici Ekle");
			win.show();
		}
		else {
			Parent root;
			Stage window = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DeleteUser.fxml"));
			try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			root = loader.getRoot();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setTitle("Kullanici Sil");
			window.show();
		}
	}
	
	public void getSuggestion() {
		UBCF.setUser_id(userID);
		BookModel[] books = UBCF.rating();
		
		Parent root;
		Stage window = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SuggestionWindow.fxml"));
		
		SuggestionController.books = books;
		
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		root = loader.getRoot();
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setTitle("Oneriler");
		window.show();
	}
	
}
