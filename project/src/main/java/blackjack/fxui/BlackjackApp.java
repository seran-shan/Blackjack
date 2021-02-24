package blackjack.fxui;

import java.io.IOException;

import blackjack.model.BlackJackMain;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BlackjackApp extends Application {

	@Override
    public void start(Stage primatyStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        
        Scene scene = new Scene(parent);
        
        scene.setFill(Color.TRANSPARENT);
        
        primatyStage.setTitle("Startside Blackjack");
        primatyStage.setScene(scene);
        primatyStage.initStyle(StageStyle.TRANSPARENT);
        primatyStage.show();
    }
	
    
    @FXML
	private static void showMenuPage() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BlackJackMain.class.getResource("MenuPage.fxml"));
		Button createAccountButton = loader.load();
		Button loginButton = loader.load();
 	}
    
    @FXML
	private static void showDepositPage() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BlackJackMain.class.getResource("DepositPage.fxml"));
		Button depositButton = loader.load();
 	}
    
    @FXML
	private static void showWithdrawPage() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BlackJackMain.class.getResource("WithdrawPage.fxml"));
		Button withdrawButton = loader.load();
 	}
    
    @FXML
	private static void showGamePage() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BlackJackMain.class.getResource("GamePage.fxml"));
		Button gameButton = loader.load();
 	}

    public static void main(String[] args) {
        launch(BlackjackApp.class, args);
    }
}
