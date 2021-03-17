package blackjack.fxui;

import java.io.IOException;

import blackjack.model.BlackJackMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositPageController{
	
	private BlackJackMain blackJackMain;
	
	@FXML private TextField depositAmountTextField;
	@FXML private Button confirmButton;
	
	
	public void initialize(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	}
	
	@FXML
	public void confirmButtonOnAction(ActionEvent event) throws IOException {
		double withdrawAmount = Double.parseDouble(depositAmountTextField.getText());
		blackJackMain.getPlayer().withdraw(withdrawAmount);
		
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}