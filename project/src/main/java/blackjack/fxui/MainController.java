package blackjack.fxui;

import java.io.IOException;
import java.time.LocalDate;

import blackjack.model.BlackJackMain;
import blackjack.model.UserValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
// 	@FXML private StartPageController startPageController;// = new StartPageController();
// 	@FXML private DepositPageController depositPageController;// = new DepositPageController();
// 	@FXML private WithdrawPageController withdrawPageController;// = new WithdrawPageController();
// 	@FXML private GamePageController gamePageController;// = new GamePageController();
// 	@FXML private Button loginRegButton;
	

//	private BlackJackMain blackJackMain;

//	public void initialize() {
// 		blackJackMain = new BlackJackMain("Sander", "Shawn", "sora21", "Pss3!", "sera@live.no", LocalDate.of(2001, 8, 26), "Mann", 200);
		
// 		startPageController.initialize(blackJackMain);
// 		depositPageController.initialize(blackJackMain);
// 		withdrawPageController.initialize(blackJackMain);
// 		gamePageController.initialize(blackJackMain);
//	}
	
	@FXML
	public void loginRegButtonOnAction(ActionEvent event) throws IOException {
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
}