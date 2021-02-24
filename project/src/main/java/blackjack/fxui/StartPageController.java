package blackjack.fxui;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

import com.sun.tools.javac.launcher.Main;

import blackjack.model.BlackJackMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class StartPageController {
	
	private BlackJackMain blackJackMain;
	ObservableList<String> valigGender = FXCollections.observableArrayList("Mann", "Dame", "Udefinert");

	// Kontakt informasjon

	@FXML private TextField firstName, lastName, username, email;
	@FXML private DatePicker birthday;
	@FXML private ChoiceBox gender;
	
	@FXML
	private void goMenu() {
		blackJackMain.
	}
	
	@FXML
	private void initilize() {
		gender.setValue("mmm");
	}

	
}
