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
			//e.printStackTrace();
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
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO bx_users ( username , password , location , age, book_number )" + "VALUES( ? , ? , ? , ?, ?)");
			stmt.setString(1,user.getUsername());
			stmt.setString(2,user.getPassword());
			stmt.setString(3,user.getAdress());
			stmt.setString(4,user.getAge());
			stmt.setInt(5, 0);
			stmt.executeUpdate();
	
			//kullanýcýnýn idisini dondur
			System.out.println(getUserId(user.getUsername()));
			return getUserId(user.getUsername());
			
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
	
	public static int createBook(BookRegisterModel book) {
		//insert et 
                try{
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO bx_books (isbn , book_title , book_author , year_of_publication , publisher , image_url_s , image_url_m , image_url_l)" + " VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1,book.getIsbn());
			stmt.setString(2,book.getName());
			stmt.setString(3,book.getAuthor());
			stmt.setString(4,book.getPublish_date());
                        stmt.setString(5,book.getPublisher());
                        stmt.setString(6,book.getImageLink_s());
                        stmt.setString(7,book.getImageLink_m());
                        stmt.setString(8,book.getImage_l());
			stmt.executeUpdate();
	
			//kullanï¿½cï¿½nï¿½n idsini
		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}
                
	
	public static void deleteBook(String isbn) {
            String Sql= "DELETE From bx_books WHERE isbn = ? ";
            try{
                 PreparedStatement stmt = connection.prepareStatement(Sql);
                 stmt.setString(1,isbn);
                 stmt.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
    	//isbn kayï¿½tlï¿½ kitabï¿½ sil
    }
	
	public static void deleteUser(int id) {
            String Sql= "DELETE From bx_users WHERE user_id = ? ";
            try{
                 PreparedStatement stmt = connection.prepareStatement(Sql);
                 stmt.setInt(1,id);
                 stmt.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
		//id si verilen kullanï¿½cï¿½yï¿½ sil
	}

	public static void VoteBook(int userID, String isbn, int rate) {
		// TODO Auto-generated method stub
                try{
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO bx_book_ratings (user_id,isbn,book_rating)" + " VALUES ( ? , ? , ? )");
			stmt.setInt(1,userID);
			stmt.setString(2,isbn);
			stmt.setInt(3,rate);
			stmt.executeUpdate();
			//kullanï¿½cï¿½nï¿½n idsini
              		//oylamayï¿½ kaydet
		}
		catch(SQLException e) {
			e.printStackTrace();
                }
        }
        public static void UpdateVoteBook(int rate){
             //AÅŸaÄŸÄ±daki kod satiri baÅŸka bir fonksiyona atilacak 
                try{
                    PreparedStatement stmt2 = connection.prepareStatement("UPDATE bx_book_ratings SET book_rating = ? ");
                    stmt2.setInt(1, rate);
                    stmt2.executeUpdate();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
		//ama kullanï¿½cï¿½ bu kitabï¿½ oylamï¿½ï¿½ï¿½a ï¿½nceki veriyi sil
        }

	public static int getUserId(String username) {
            try{		
                PreparedStatement stmt = connection.prepareStatement("SELECT user_id FROM bx_users WHERE username= ? ");
                stmt.setString(1,username);
                ResultSet user = stmt.executeQuery();
                return (user.getInt("user_id"));
            }
            catch(SQLException e){
                e.printStackTrace();
            }
		return -1;
	}
}
