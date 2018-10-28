package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javafx.collections.ObservableList;
import model.BookModel;
import model.BookRegisterModel;
import model.UserRegisterModel;

public class Database {

	private static Connection connection;

	public static void setConnection() {
		String url, username, password;
		url = "jdbc:mysql://localhost:3306/kitap?useUnicode=yes&characterEncoding=UTF-8";
		username = "admin";
		password = "333ordEMmgH8aJud";

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error while accessing database");
			e.printStackTrace();
			return;
		}
		System.out.println("Success");
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void getBooks(ObservableList<BookModel> books) {
		String sql_order = "SELECT * FROM `bx_books`";
		books.clear();
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql_order);
			while (rs.next()) {
				books.add(new BookModel(rs.getString("isbn"), rs.getString("book_title"),
						rs.getString("book_author"), rs.getString("year_of_publication"),
						rs.getString("publisher"), rs.getString("image_url_l") ));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Cannot execute sql order at load products method");
			// e.printStackTrace();

		}
	}
	
	public static void getPopularBooks(ObservableList<BookModel> populerbooks){
		String isbn[]=new String[10];
		populerbooks.clear();
		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT isbn FROM `bx_book_ratings` GROUP BY isbn ORDER BY COUNT(isbn) DESC LIMIT 10");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				for (int i = 0; i < isbn.length; i++) {
					isbn[i]=rs.getString("isbn");
				}                      
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try{
			PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM `bx_books` WHERE 'isbn'");
			ResultSet rs2 = stmt2.executeQuery();
			while(rs2.next()){
				for (int i = 0; i < isbn.length; i++) {
					if(isbn[i]==rs2.getString("isbn")){
						populerbooks.add(new BookModel(rs2.getString("isbn"), rs2.getString("book_title"),
								rs2.getString("book_author"), rs2.getString("year_of_publication"),
								rs2.getString("publisher"), rs2.getString("image_url_s") ));
					}

				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void getBestBooks(ObservableList<BookModel> books){
		String isbn[]=new String[10];
		books.clear();
		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT isbn FROM `bx_book_ratings` GROUP BY isbn ORDER BY AVG(book_rating) DESC LIMIT 10");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				for (int i = 0; i < isbn.length; i++) {
					isbn[i]=rs.getString("isbn");
				}                      
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try{
			PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM `bx_books` WHERE 'isbn'");
			ResultSet rs2 = stmt2.executeQuery();
			while(rs2.next()){
				for (int i = 0; i < isbn.length; i++) {
					if(isbn[i]==rs2.getString("isbn")){
						books.add(new BookModel(rs2.getString("isbn"), rs2.getString("book_title"),
								rs2.getString("book_author"), rs2.getString("year_of_publication"),
								rs2.getString("publisher"), rs2.getString("image_url_s") ));
					}

				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static int createUser(UserRegisterModel user){

		try{
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO bx_users VALUES ( username + password + location + age )");
			stmt.setString(1,user.getUsername());
			stmt.setString(2,user.getPassword());
			stmt.setString(3,user.getAdress());
			stmt.setString(4,user.getAge());
			stmt.executeUpdate();
			//PreparedStatement user = connection.prepareStatement("SELECT user_id FROM bx_users WHERE username='" + user.getUsername() + "'");
			//kullan�c�n�n idsini
		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}

	public static void getUsers(){


		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bx_users  ORDER BY `bx_users`.`user_id` ASC ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String register = rs.getString("username");
				String password = rs.getString("password");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	public static boolean isUserExist(String username, String password){

		try{
			PreparedStatement stmt = connection.prepareStatement("Select username FROM bx_users ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String id=rs.getString("username");
				String pw=rs.getString("password");

				if(username==id && pw==password){
					return true;
				}
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int userVotedBook(int userId){
		try{
			PreparedStatement stmt = connection.prepareStatement("Select book_number FROM bx_users WHERE user_id='" + userId + "'");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int book_number=rs.getInt("book_number");
				return book_number;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void createBook(BookRegisterModel book) {
		//insert et 
	}
	
	public static void deleteBook(String isbn) {
    	//isbn kay�tl� kitab� sil
    }
	
	public static void deleteUser(int id) {
		//id si verilen kullan�c�y� sil
	}

	public static void VoteBook(int userID, String isbn, int rate) {
		// TODO Auto-generated method stub
		//oylamay� kaydet
		//ama kullan�c� bu kitab� oylam���a �nceki veriyi sil
	}

	public static int getUserId(String username) {
		// kullan�c� ad�ndan userId yi getir
		return 0;
	}
	
	

}
