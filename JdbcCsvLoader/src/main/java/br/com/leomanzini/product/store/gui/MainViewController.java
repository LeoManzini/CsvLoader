package br.com.leomanzini.product.store.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.leomanzini.product.store.App;
import br.com.leomanzini.product.store.gui.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemHomePage;

	@FXML
	private MenuItem menuItemStore;

	@FXML
	private MenuItem menuItemProduct;

	@FXML
	private MenuItem menuItemInventory;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private MenuItem menuItemExit;

	@FXML
	public void onMenuItemHomePageAction() {
		System.out.println("Home Page Action");
	}

	@FXML
	public void onMenuItemStoreAction() {
		System.out.println("Store Page Action");
	}

	@FXML
	public void onMenuItemProductAction() {
		System.out.println("Products Page Action");
	}

	@FXML
	public void onMenuItemInventoryAction() {
		System.out.println("Inventory Page Action");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/br/com/leomanzini/product/store/gui/AboutView.fxml");
	}

	@FXML
	public void onMenuItemExitAction() {
		System.out.println("Exit Action");
		System.exit(0);
	}

	private synchronized void loadView(String viewAbsoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(viewAbsoluteName));
			VBox newVbox = loader.load();

			Scene mainScene = App.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error while loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
