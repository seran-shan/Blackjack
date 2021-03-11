package blackjack.fxui;

import java.io.IOException;

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

public class StartPageController {
	
	private BlackJackMain blackJackMain;
	
	ObservableList<String> valigGender = FXCollections.observableArrayList("Mann", "Dame", "Udefinert");

	@FXML private Button loginButton, regButton;
	@FXML private TextField usernameLoginField, passwordLoginField, firstnameRegField, 
							lastNameRegField, usernameRegField, emailRegField, 
							passwordRegField, balanceRegField;
	@FXML private DatePicker birthdayRegDatePicker;
	@FXML private ChoiceBox<String> genderChoiceBox;
	@FXML private Label errorMessageLabel;
	
	public void initialize(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	
		if ( genderChoiceBox != null) {
		genderChoiceBox.getItems().add("Mann");
		genderChoiceBox.getItems().add("Dame");
		genderChoiceBox.getItems().add("Udefinert");
		genderChoiceBox.setValue("Mann");
		}}
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void loginButtonOnAction(ActionEvent event) throws IOException {
		Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		Scene menuScene = new Scene(menuParent);
		
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}
	
	@FXML
	public void regButtonOnAction(ActionEvent event) throws IOException {
		
		UserValidation userValidation = new UserValidation();
		
		if (!userValidation.validateFirstName(firstnameRegField.getText())) {
		      errorMessageLabel.setText("Fornavn må bestå av bokstaver");
		    } 
			else if (!userValidation.validateLastName(lastNameRegField.getText())) {
				errorMessageLabel.setText("Etternavn må bestå av bokstaver");
		    } 
		    else if (!userValidation.validateUsername(usernameRegField.getText())) {
		    	errorMessageLabel.setText("Brukernavnet kan kun består av bokstaver og tall");
			} 
		    else if (!userValidation.validatePassword(passwordRegField.getText())) {
		    	errorMessageLabel.setText("Passordet må bestå av minst et tall, en liten bokstav og en stor bokstav");
		    } 
			else if (!userValidation.validateMail(emailRegField.getText())) {
				errorMessageLabel.setText("Eposten er ugyldig");
		    } 
			else if (!userValidation.validateBalance(balanceRegField.getText())) {
				errorMessageLabel.setText("Balansen kan kun bestå av tall");
			} 
		    else if (!userValidation.validateBirthday(birthdayRegDatePicker.getValue())) {
		    	errorMessageLabel.setText("Du må være 18 år for å bruke appen");
		    }
		    else if (birthdayRegDatePicker.isDisabled()) {
				errorMessageLabel.setText("Legg inn bursdagen din");
			}
			else {
				errorMessageLabel.setText("Oppretter konto");
				
				blackJackMain = new BlackJackMain(firstnameRegField.getText(), 
												  lastNameRegField.getText(), 
												  usernameRegField.getText(),
												  passwordRegField.getText(),
												  emailRegField.getText(),
												  birthdayRegDatePicker.getValue(),
												  genderChoiceBox.getValue(),
												  Double.parseDouble(balanceRegField.getText()));
				
				Parent menuParent = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
				Scene menuScene = new Scene(menuParent);
				
				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
				window.setScene(menuScene);
				window.show();
				
			}
	}

	public BlackJackMain getBlackJackMain() {
		return blackJackMain;
	}
}
