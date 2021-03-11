package blackjack.fxui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositPageController extends StartPageController {
	
	@FXML private TextField depositAmountTextField;
	@FXML private Button confirmButton;
	
	@FXML
	public void confirmButtonOnAction(ActionEvent event) throws IOException {
		double withdrawAmount = Double.parseDouble(depositAmountTextField.getText());
		getBlackJackMain().getPlayer().withdraw(withdrawAmount);
		
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}