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

public class MainController {
	
	@FXML private Button loginRegButton;
	
	@FXML
	public void loginRegButtonOnAction(ActionEvent event) throws IOException {
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}