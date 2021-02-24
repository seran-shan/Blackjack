package blackjack.fxui;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class StartPageController {

	ObservableList<String> valigGender = FXCollections.observableArrayList("Mann", "Dame", "Udefinert");

	// Kontakt informasjon

	@FXML
	private TextField firstName, lastName, username, email;

	@FXML
	private DatePicker birthday;

	@FXML
	private ChoiceBox gender;
	
	@FXML
	private void initilize() {
		gender.setValue("mmm");
	}

}
