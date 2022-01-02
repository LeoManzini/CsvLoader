package br.com.leomanzini.product.store;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/leomanzini/product/store/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			Scene mainScene = new Scene(scrollPane);
			// Add stylesheet later, when the app is running
			//mainScene.getStylesheets().add(getClass().getResource("/src/main/resources/MainStylesheet.css").toExternalForm());
			stage.setScene(mainScene);
			stage.setTitle("Store Inventory Management");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
