
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javafx.collections.ObservableList;

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
    
        public static void Kitap_Secim() {
		
		try{
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `bx_books` WHERE user=admin AND password=333ordEMmgH8aJud");
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Users(){
			
		try{
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM 'bx_users' WHERE user=admin AND password=333ordEMmgH8aJud");
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void populer_book(){
					
		try{
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `bx_book_ratings` WHERE user=admin AND password=333ordEMmgH8aJud BY `bx_book_ratings`.`book_rating` DESC LIMIT 10");
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Register(){
	
		System.out.println("Konumunuzu giriniz:");
		Scanner sc= new Scanner(System.in);
		String location=sc.nextLine();
		System.out.println("Yaşınızı  giriniz:");
		String age=sc.nextLine();
	/*	PreparedStatement stmt = connection.prepareStatement("İNSERT İNTO `bx_users` WHERE user=admin AND password=333ordEMmgH8aJud  VALUES ( location + " " + age ) ");
		
		try{
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void Bring_Register(){
		
		
		try{
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `bx_users`  WHERE user=admin AND password=333ordEMmgH8aJud ORDER BY `bx_users`.`user_id` ASC ");
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void Bring_Books(){
		
		
		try{
                    PreparedStatement stmt = connection.prepareStatement("SELECT book_title FROM `bx_books`  WHERE user=admin AND password=333ordEMmgH8aJud ORDER BY `isbn` ASC ");
			stmt.setString(1," admin");
			stmt.setString(2, "333ordEMmgH8aJud");
			ResultSet rs = stmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}
}
