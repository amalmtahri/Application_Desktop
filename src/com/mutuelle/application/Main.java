package com.mutuelle.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			   Pane root = (Pane)FXMLLoader.load(getClass().getResource("views/welcomePage.fxml"));
	            Scene scene = new Scene(root,735,400);
	            primaryStage.initStyle(StageStyle.UNDECORATED);
	            scene.getStylesheets().add(getClass().getResource("views/application.css").toExternalForm());
	            primaryStage.setScene(scene);
	            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
