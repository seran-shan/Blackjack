package blackjack.fxui;

import java.io.IOException;

import blackjack.model.BlackJack;
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

public class LogRegPageController {
	
	private BlackJack blackJack;

	public void setBlackJack(BlackJack blackJack) {
		this.blackJack = blackJack;
	}

	@FXML private Button loginButton, regButton;
	@FXML private TextField usernameLoginField, passwordLoginField, firstnameRegField, 
							lastNameRegField, usernameRegField, emailRegField, 
							passwordRegField, balanceRegField;
	@FXML private DatePicker birthdayRegDatePicker;
	@FXML private ChoiceBox<String> genderChoiceBox;
	@FXML private Label errorMessageLabel;

	public void initialize() {
		ObservableList<String> valigGender = FXCollections.observableArrayList("Mann", "Dame", "Udefinert");
		if (genderChoiceBox != null) {
			genderChoiceBox.getItems().add("Mann");
			genderChoiceBox.getItems().add("Dame");
			genderChoiceBox.getItems().add("Udefinert");
			genderChoiceBox.setValue("Mann");
		}
	} 
	
	/**
	 * Skal bytte scene til menyen
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void loginButtonOnAction(ActionEvent event) throws IOException {

		String username = usernameLoginField.getText();
		String password = passwordLoginField.getText();
		
		try {
			blackJack = new BlackJack(username,
					  				  password);
			if (blackJack != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
				Parent root = (Parent) loader.load();
		
				MenuPageController menuPageController = loader.getController();
				menuPageController.setBlackJack(blackJack);
				menuPageController.getWelcomeLabel().setText("Velkommen tilbake " + blackJack.getPlayer().getFirstname() + " " +
																					blackJack.getPlayer().getLastname());
		
				Scene menuScene = new Scene(root);
				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
				window.setScene(menuScene);
				window.show();
			}
		} catch (Exception e) {
			errorMessageLabel.setText(e.getMessage());
		}	
	}
	
	@FXML
	public void regButtonOnAction(ActionEvent event) throws IOException {
		try {
			blackJack = new BlackJack(firstnameRegField.getText(), 
									  lastNameRegField.getText(), 
									  usernameRegField.getText(),
									  passwordRegField.getText(),
									  emailRegField.getText(),
									  birthdayRegDatePicker.getValue(),
									  genderChoiceBox.getValue(),
									  Double.parseDouble(balanceRegField.getText()));
			if (blackJack != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
				Parent root = (Parent) loader.load();
		
				MenuPageController menuPageController = loader.getController();
				menuPageController.setBlackJack(blackJack);
		
				Scene menuScene = new Scene(root);
				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
				window.setScene(menuScene);
				window.show();
			}
		} catch (Exception e) {
			errorMessageLabel.setText(e.getMessage());;
		}
	}
}
