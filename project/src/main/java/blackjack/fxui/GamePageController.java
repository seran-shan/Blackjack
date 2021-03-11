package blackjack.fxui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GamePageController extends StartPageController {

	
	@FXML private Button startButton, bet20Button, bet100Button, bet200Button,
				 		 hitButton, standButton, resetButton;
	@FXML private TextField balanceField, betField;
	@FXML private Text dealerTotal, playerTotal, endText;
	@FXML private ImageView firstCard, secondCard, thirdCard, 
							dealerFirstCard, dealerSecondCard, dealerThirdCard;
	

	@FXML
	public void bet20buttonOnAction() {
		double bettingAmount = 20; 
		getBlackJackMain().bet(bettingAmount);
		balanceField.setText("Bank: " + getBlackJackMain().showBalance() + " ,-");
		betField.setText("Bet: " + bettingAmount + " ,-");
	}

	@FXML
	public void bet100buttonOnAction() {
		double bettingAmount = 100; 
		getBlackJackMain().bet(bettingAmount);
		balanceField.setText("Bank: $" + getBlackJackMain().showBalance());
		betField.setText("Bet: " + bettingAmount + " ,-");
	}

	@FXML
	public void bet200buttonOnAction() {
		double bettingAmount = 200; 
		getBlackJackMain().bet(bettingAmount);
		balanceField.setText("Saldo: $" + getBlackJackMain().showBalance());
		betField.setText("Sats: " + bettingAmount + " ,-");
	}

	
	@FXML
	public void hitButtonOnAction() {
		getBlackJackMain().drawCard();
		
		if (firstCard.getImage() == null) {
			firstCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
		} 
		else if (secondCard.getImage() == null) {
			secondCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
		}
		else {
			thirdCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
		}
		
		int playerHandValue = getBlackJackMain().getPlayer().getHandValue();
		playerTotal.setText("Your Hand: " + playerHandValue);

		if (getBlackJackMain().hasBlackJack()) {
			blackjack();
		}
		else {
			bust();
		}

	}
	
	@FXML
	public void startButtonOnAction() {
		resetGame();
	}
	
	
	@FXML
	public void standButtonOnAction() {
		dealForDealer();
		
		if (getBlackJackMain().dealerWins()) {
			endText.setText("HUSET VANT");
			activateAvailableButtons();
		} 
		else if (getBlackJackMain().isTie()) {
			endText.setText("UAVGJORT");
		}
		else {
			endText.setText("DU VANT");
		}
	}

	public void dealForDealer() {
		
		while (getBlackJackMain().drawCardDealer() != null); {
		
			if (dealerFirstCard.getImage() == null) {
				dealerFirstCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
			} 
			else if (dealerSecondCard.getImage() == null) {
				dealerSecondCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
			}
			else {
				dealerThirdCard.setImage(getBlackJackMain().getCardImage(getBlackJackMain().getLastCardInHandDealer()));
			}
			
			int playerHandValue = getBlackJackMain().getPlayer().getHandValue();
			playerTotal.setText("Your Hand: " + playerHandValue);
	
			if (getBlackJackMain().hasBlackJack()) {
				blackjack();
			}
			else {
				bust();
			}
		}

	}
		
	public void blackjack() {
		dealForDealer();
		if (getBlackJackMain().hasBlackJack() && getBlackJackMain().hasBlackJackDealer()) {
			endText.setText("UAVGJORT");
		}
		else if (getBlackJackMain().isBustDealer()) {
			endText.setText("BLACKJACK");
		}
	}
	
	public void bust() {
		endText.setText("HUSET VANT");
	}
			
	public void resetGame() {
		endText.setText(null);
		double balance = getBlackJackMain().getPlayer().getBalance();
		
		if (balance == 0) {
			disableAll();
			endText.setText("Your are out of money");
		} else {
			activateAvailableButtons();
			betField.setText("Bet: " + balance);
		}
	}
	
	@FXML
	public void disableAll() {
		bet20Button.setDisable(true);
		bet100Button.setDisable(true);
		bet200Button.setDisable(true);
		startButton.setDisable(true);
		hitButton.setDisable(true);
		standButton.setDisable(true);
	}
	
	@FXML
	public void activateAll() {
		bet20Button.setDisable(false);
		bet100Button.setDisable(false);
		bet200Button.setDisable(false);
		startButton.setDisable(false);
		hitButton.setDisable(false);
		standButton.setDisable(false);
	}
	
	@FXML
	public void activateAvailableButtons() {
		
		double balance = Double.parseDouble(getBlackJackMain().showBalance());
	
		if (balance < 200 && !bet200Button.isDisable()) {
			bet200Button.setDisable(true);
		} 
		if (balance < 100 && !bet100Button.isDisable()) {
			bet100Button.setDisable(true);
		} 
		if (balance < 50 && !bet20Button.isDisable()) {
			bet20Button.setDisable(true);
		}
		if (!startButton.isDisable()) {
			hitButton.setDisable(true);
			standButton.setDisable(true);
		}
		if (hitButton.isDisable() && standButton.isDisable()) {
			startButton.setDisable(false);
		}
	}
}