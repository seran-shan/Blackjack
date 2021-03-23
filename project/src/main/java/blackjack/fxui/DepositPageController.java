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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DepositPageController{
	
	private BlackJackMain blackJackMain;
	
	@FXML private TextField depositAmountTextField;
	@FXML private Text errorMessageLabel;
	@FXML private Button confirmButton;
	
	
	public void setBlackJackMain(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	}
	
	@FXML
	public void confirmButtonOnAction(ActionEvent event) throws IOException {
		double depositAmount = Double.parseDouble(depositAmountTextField.getText());

		if (depositAmount > 0) {
			blackJackMain.getPlayer().deposit(depositAmount);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
			Parent root = (Parent) loader.load();

			MenuPageController menuPageController = loader.getController();
			menuPageController.setBlackJackMain(blackJackMain);

			Scene menuScene = new Scene(root);
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(menuScene);
			window.show();
		} 
		else if (depositAmount < 0) {
			errorMessageLabel.setText("Beløpet kan ikke være mindre enn null");
		}
		else {
			errorMessageLabel.setText("Feil!");
		}
		// Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		// Scene menuScene = new Scene(menuParent);
		
		// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		// window.setScene(menuScene);
		// window.show();
	}
}