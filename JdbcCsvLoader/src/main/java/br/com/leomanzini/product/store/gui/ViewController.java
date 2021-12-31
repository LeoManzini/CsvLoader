package br.com.leomanzini.product.store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.leomanzini.product.store.gui.utils.Alerts;
import br.com.leomanzini.product.store.gui.utils.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ViewController implements Initializable {

	@FXML
	private TextField firstNumber;
	
	@FXML
	private TextField secondNumber;
	
	@FXML
	private Label result;
	
	@FXML
	private Button sumButton;

	@FXML
	public void onSumButtonAction() {
		try {
			double numberOne = Double.parseDouble(firstNumber.getText());
			double numberTwo = Double.parseDouble(secondNumber.getText());
			
			Double sum = numberOne + numberTwo;
			
			result.setText(String.format("%.2f", sum));
		} catch (NumberFormatException e) {
			Alerts.showAlert("ERROR", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Constraints.setTextFieldDouble(firstNumber);
		Constraints.setTextFieldMaxLength(firstNumber, 12);
		
		Constraints.setTextFieldDouble(secondNumber);
		Constraints.setTextFieldMaxLength(secondNumber, 12);
	}
}
