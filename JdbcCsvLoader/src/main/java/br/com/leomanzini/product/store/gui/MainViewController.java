package br.com.leomanzini.product.store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("About Page Action");
	}
	
	@FXML
	public void onMenuItemExitAction() {
		System.out.println("Exit Action");
		
		System.exit(0);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	}
}
