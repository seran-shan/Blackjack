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
import javafx.stage.Stage;

public class MenuPageController {

	private BlackJackMain blackJackMain;

	public void setBlackJackMain(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	}
	
	@FXML private Button gameButton, depositButton, withdrawButton, exitButton;

	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void gameButtonOnAction(ActionEvent event) throws IOException {
		// Parent menuParent = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
		// Scene menuScene = new Scene(menuParent);
		
		// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		// window.setScene(menuScene);
		// window.show();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
		Parent root = (Parent) loader.load();

		GamePageController gamePageController = loader.getController();
		gamePageController.setBlackJackMain(blackJackMain);

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
		// Parent menuParent = FXMLLoader.load(getClass().getResource("DepositPage.fxml"));
		// Scene menuScene = new Scene(menuParent);
		
		// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		// window.setScene(menuScene);
		// window.show();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositPage.fxml"));
		Parent root = (Parent) loader.load();

		DepositPageController depositPageController = loader.getController();
		depositPageController.setBlackJackMain(blackJackMain);

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
		// Parent menuParent = FXMLLoader.load(getClass().getResource("WithdrawPage.fxml"));
		// Scene menuScene = new Scene(menuParent);
		
		// Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		// window.setScene(menuScene);
		// window.show();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("WithdrawPage.fxml"));
		Parent root = (Parent) loader.load();

		WithdrawPageController withdrawPageController = loader.getController();
		withdrawPageController.setBlackJackMain(blackJackMain);

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
		Stage window = (Stage) exitButton.getScene().getWindow();
		window.close();
	}
}