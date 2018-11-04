package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YazLab1 extends Application {
    
	protected static Stage window;
	
    @Override
    public void start(Stage stage){
        Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			window = stage;
		} catch (IOException e) {
			System.out.println("Could not load: LogIn.fxml ");
			e.printStackTrace();
		}
        
        stage.setTitle("Giris");
        stage.show();
    }

   
    public static void main(String[] args) {
    	Database.setConnection();
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				UBCF.getMapData();
			}
		}).start();
		
    	launch(args);
    }
    
}
