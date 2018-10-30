package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import model.BookModel;
import model.BookRegisterModel;
import model.UserRegisterModel;

public class Database {

	private static Connection connection;
	// Database Methods
	public static void setConnection() {
		String url, username, password;
		url = "jdbc:mysql://localhost:3306/kitap?useUnicode=yes&characterEncoding=UTF-8";
		username = "admin";
		password = "333ordEMmgH8aJud";

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error while accessing database");
			// e.printStackTrace();
			return;
		}
		System.out.println("Success");
	}

	public static Connection getConnection() {
		return connection;
	}

	// User Methods
	public static int createUser(UserRegisterModel user) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO bx_users ( username , password , location , age, book_number )"
							+ "VALUES( ? , ? , ? , ?, ?)");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getAdress());
			stmt.setString(4, user.getAge());
			stmt.setInt(5, 0);
			stmt.executeUpdate();

			// kullan�c�n�n idisini dondur
			System.out.println(getUserId(user.getUsername()));
			return getUserId(user.getUsername());

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}

	/*public static void getUsers() {

		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM bx_users  ORDER BY `bx_users`.`user_id` ASC ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String register = rs.getString("username");
				String password = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	public static boolean isUserExist(String username, String password) {
		String sqlOrder = "Select user_id FROM bx_users WHERE username = ? and password = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sqlOrder);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteUser(int id) {
		String Sql = "DELETE From bx_users WHERE user_id = ? ";
		try {
			PreparedStatement stmt = connection.prepareStatement(Sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// id si verilen kullan�c�y� sil
	}
	
	public static int getUserId(String username) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT user_id FROM bx_users WHERE username= ? ");
			stmt.setString(1, username);
			ResultSet user = stmt.executeQuery();
			while (user.next())
				return (user.getInt("user_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Books Methods
	public static int getNumberofBooks() {
		String sql_order = "SELECT COUNT(*) FROM bx_books";
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql_order);
			while(rs.next())
				return rs.getInt(1);
		} catch(SQLException e) {
			System.out.println("Cannot get nums of books");
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void getBooks(ObservableList<BookModel> books) {
		String sql_order = "SELECT * FROM `bx_books`";
		books.clear();
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql_order);
			while (rs.next()) {
				books.add(new BookModel(rs.getString("isbn"), rs.getString("book_title"), rs.getString("book_author"),
						rs.getString("year_of_publication"), rs.getString("publisher"), rs.getString("image_url_l")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Cannot execute sql order at load products method");
			// e.printStackTrace();

		}
	}

	public static void getBooks(ObservableList<BookModel> books, int limit) {
		String sql_order = "SELECT * FROM bx_books LIMIT " + limit;
		books.clear();
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql_order);
			while (rs.next()) {
				books.add(new BookModel(rs.getString("isbn"), rs.getString("book_title"), rs.getString("book_author"),
						rs.getString("year_of_publication"), rs.getString("publisher"), rs.getString("image_url_l")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Cannot execute sql order at load products method");
			// e.printStackTrace();

		}
	}
	
	public static void getBooks(ObservableList<BookModel> books, int pageIndex, int limit) {
		int end = (pageIndex+1) * limit;
		int start = end - limit + 1;
		//sql_order i degistir
		String sql_order = "SELECT * FROM `bx_books` LIMIT "+ start +","+ limit +" "; // startla end arasındaki getir
                System.out.println(sql_order);
		books.clear();
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(sql_order);                      
			while (rs.next()) {          
				books.add(new BookModel(rs.getString("isbn"), rs.getString("book_title"), rs.getString("book_author"),
						rs.getString("year_of_publication"), rs.getString("publisher"), rs.getString("image_url_l")));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Cannot execute sql order at load products method");
			// e.printStackTrace();
		}
	}
	
	public static void getPopularBooks(ObservableList<BookModel> populerbooks) {
		String isbn[] = new String[1000];
		populerbooks.clear();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT isbn FROM `bx_book_ratings` GROUP BY isbn ORDER BY COUNT(isbn) DESC LIMIT 1000");
			ResultSet rs = stmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				isbn[index] = rs.getString("isbn");
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(isbn.length);
		try {
			for(int i =0; i<isbn.length; i++) {
				PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM bx_books WHERE isbn = ?");
				stmt2.setString(1, isbn[i]);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					populerbooks.add(new BookModel(rs2.getString("isbn"), rs2.getString("book_title"),
					rs2.getString("book_author"), rs2.getString("year_of_publication"),
					rs2.getString("publisher"), rs2.getString("image_url_l")));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getBestBooks(ObservableList<BookModel> books) {
		String isbn[] = new String[10];
		books.clear();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT isbn FROM `bx_book_ratings` GROUP BY isbn ORDER BY AVG(book_rating) DESC LIMIT 10");
			ResultSet rs = stmt.executeQuery();
			for (int i = 0; i < isbn.length; i++) {
				while (rs.next()) {
					isbn[i] = rs.getString("isbn");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < isbn.length; i++) {
				PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM bx_books WHERE isbn = ?");
				stmt2.setString(1, isbn[i]);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					books.add(new BookModel(rs2.getString("isbn"), rs2.getString("book_title"),
							rs2.getString("book_author"), rs2.getString("year_of_publication"),
							rs2.getString("publisher"), rs2.getString("image_url_l")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int createBook(BookRegisterModel book) {
		// insert et
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO bx_books (isbn , book_title , book_author , year_of_publication , publisher , image_url_s , image_url_m , image_url_l)"
							+ " VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getName());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getPublish_date());
			stmt.setString(5, book.getPublisher());
			stmt.setString(6, book.getImageLink_s());
			stmt.setString(7, book.getImageLink_m());
			stmt.setString(8, book.getImage_l());
			stmt.executeUpdate();
			return 1;
			// kullan�c�n�n idsini
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}
	
	

	public static int userVotedBook(int userId) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("Select book_number FROM bx_users WHERE user_id='" + userId + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int book_number = rs.getInt("book_number");
				return book_number;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	

	public static void deleteBook(String isbn) {
		String Sql = "DELETE From bx_books WHERE isbn = ? ";
		try {
			PreparedStatement stmt = connection.prepareStatement(Sql);
			stmt.setString(1, isbn);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// isbn kay�tl� kitab� sil
	}


	public static void VoteBook(String userID, String isbn, String rate) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO bx_book_ratings (user_id, isbn, book_rating)" + " VALUES ( ? , ? , ? )");
			stmt.setString(1, userID);
			stmt.setString(2, isbn);
			stmt.setString(3, rate);
			
			PreparedStatement select = connection.prepareStatement("SELECT book_number FROM bx_users WHERE (user_id)=?" );
			select.setInt(1, Integer.parseInt(userID));
			ResultSet rs = select.executeQuery();
			int bookNumber = 0 ;
			while(rs.next()) {
				bookNumber = rs.getInt("book_number");
			}
			PreparedStatement upd = connection.prepareStatement("Update bx_users SET book_number=? WHERE user_id=?");
			upd.setInt(1, bookNumber+1);
			upd.setInt(2, Integer.parseInt(userID));
			stmt.executeUpdate();
			upd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateVoteBook(int rate) {
		// Aşağıdaki kod satiri başka bir fonksiyona atilacak
		try {
			PreparedStatement stmt2 = connection.prepareStatement("UPDATE bx_book_ratings SET book_rating = ? ");
			stmt2.setInt(1, rate);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ama kullan�c� bu kitab� oylam���a �nceki veriyi sil
	}

}
