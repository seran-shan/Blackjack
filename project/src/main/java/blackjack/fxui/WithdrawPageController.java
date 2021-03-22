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

public class WithdrawPageController {
	
	private BlackJackMain blackJackMain;
	
	@FXML private TextField withdrawAmountTextField;
	@FXML private Button confirmButton;
	
	public void setBlackJackMain(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	}
	
	@FXML
	public void confirmButtonOnAction(ActionEvent event) throws IOException {
		double withdrawAmount = Double.parseDouble(withdrawAmountTextField.getText());
		blackJackMain.getPlayer().withdraw(withdrawAmount);
		
		// Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		// Scene menuScene = new Scene(menuParent);
		
		// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		// window.setScene(menuScene);
		// window.show();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
		Parent root = (Parent) loader.load();

		MenuPageController menuPageController = loader.getController();
		menuPageController.setBlackJackMain(blackJackMain);

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}
