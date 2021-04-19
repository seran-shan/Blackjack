package blackjack.fxui;

import java.io.IOException;

import blackjack.model.BlackJack;
import blackjack.model.FileSupport;
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
	
	private BlackJack blackJack;
	private FileSupport fileSupport = new FileSupport();
	
	@FXML private TextField depositAmountTextField;
	@FXML private Text errorMessageLabel;
	@FXML private Button backButton, confirmButton;
	
	
	public void setBlackJack(BlackJack blackJack) {
		this.blackJack = blackJack;
	}
	
	@FXML
	public void backButtonOnAction(ActionEvent event) throws IOException {
		blackJack.resetGame();
		fileSupport.saveNewBalance(blackJack.getPlayer().toString(),
								   blackJack.getPlayer().getUsername());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
		Parent root = (Parent) loader.load();

		MenuPageController menuPageController = loader.getController();
		menuPageController.setBlackJack(blackJack);

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
	
	@FXML
	public void confirmButtonOnAction(ActionEvent event) throws IOException {
		double depositAmount = Double.parseDouble(depositAmountTextField.getText());

		if (depositAmount > 0) {
			blackJack.getPlayer().deposit(depositAmount);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
			Parent root = (Parent) loader.load();

			MenuPageController menuPageController = loader.getController();
			menuPageController.setBlackJack(blackJack);

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
	}
}