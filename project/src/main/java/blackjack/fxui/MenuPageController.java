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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuPageController {

	private BlackJack blackJack;
	private FileSupport fileSupport = new FileSupport();

	public void setBlackJack(BlackJack blackJack) {
		this.blackJack = blackJack;
	}
	
	@FXML private Button gameButton, depositButton, withdrawButton, exitButton;
	@FXML private Label welcomeLabel;

	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void gameButtonOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
		Parent root = (Parent) loader.load();

		GamePageController gamePageController = loader.getController();
		gamePageController.setBlackJack(blackJack);
		gamePageController.initialize();

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void depositButtonOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositPage.fxml"));
		Parent root = (Parent) loader.load();

		DepositPageController depositPageController = loader.getController();
		depositPageController.setBlackJack(blackJack);

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void withdrawButtonOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WithdrawPage.fxml"));
		Parent root = (Parent) loader.load();

		WithdrawPageController withdrawPageController = loader.getController();
		withdrawPageController.setBlackJack(blackJack);

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void exitButtonOnAction(ActionEvent event) throws IOException {
		fileSupport.saveNewBalance(blackJack.getPlayer().toString(), 
								   blackJack.getPlayer().getUsername());
		Stage window = (Stage) exitButton.getScene().getWindow();
		window.close();
	}
	
	public Label getWelcomeLabel() {
		return welcomeLabel;
	}
}