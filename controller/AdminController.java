package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BookRegisterModel;
import model.UserRegisterModel;

public class AdminController {

	@FXML private TextField isbn; 
	@FXML private TextField kitap_adi;
	@FXML private TextField yazar;
	@FXML private TextField yayinlanma_tarihi;
	@FXML private TextField yayinlayan;
	@FXML private TextField resim_k;
	@FXML private TextField resim_o;
	@FXML private TextField resim_b;
	
	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private TextField address;
	@FXML private TextField age;
	
	@FXML private TextField id;
	
	public void addBook() {
		BookRegisterModel model = new BookRegisterModel
				(isbn.getText(), kitap_adi.getText(), yazar.getText(), yayinlanma_tarihi.getText(), yayinlayan.getText(), resim_k.getText(), resim_o.getText(), resim_b.getText());
		if(Database.createBook(model) == 1) {
			new ErrorMessage("Kitap Kaydedildi");
			return;
		}
		new ErrorMessage("Kaydedilirken hata olustu");
		
	}
	
	public void registerAction() {
		if( username.getText().equals("") || password.getText().equals("") 
				|| address.getText().equals("") || age.getText().equals("") ) {
			new ErrorMessage("Butun Alanlarin Doldurulmasi Gerekmektedir");
			return;
		}
		
		UserRegisterModel user = new UserRegisterModel(username.getText(), password.getText(), address.getText(), age.getText());
		int userId = Database.createUser(user);
		if(userId == -1) {
			new ErrorMessage("Kayit olurken hata olustu");
			return;
		}
		new ErrorMessage("Kullanici olusturuldu");
	}
	
	public void delete() {
		Database.deleteUser(Integer.parseInt(id.getText()));
		new ErrorMessage("Islem Tamamlandi");
	}
	
}
