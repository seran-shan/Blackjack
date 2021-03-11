package blackjack.fxui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPageController {
	
	@FXML private Button gameButton, depositButton, withdrawButton, exitButton;
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void gameButtonOnAction(ActionEvent event) throws IOException {
		Parent menuParent = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
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
		Parent menuParent = FXMLLoader.load(getClass().getResource("DepositPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
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
		Parent menuParent = FXMLLoader.load(getClass().getResource("WithdrawPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
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