package blackjack.fxui;

import blackjack.model.BlackJackMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GamePageController{

	private BlackJackMain blackJackMain;
	
	@FXML private Button startButton, bet20Button, bet100Button, bet200Button,
				 		 hitButton, standButton, resetButton;
	@FXML private TextField balanceField, betField;
	@FXML private Text dealerTotal, playerTotal, endText;
	@FXML private ImageView firstCard, secondCard, thirdCard, 
							dealerFirstCard, dealerSecondCard, dealerThirdCard;

	public void setBlackJackMain(BlackJackMain blackJackMain) {
		this.blackJackMain = blackJackMain;
	}

	@FXML
	public void initialize() {
		disableAll();
		activateAvailableButtons();
	}

	@FXML
	public void bet20buttonOnAction() {
		double bettingAmount = 20; 
		blackJackMain.bet(bettingAmount);
		balanceField.setText("Bank: " + blackJackMain.showBalance() + " ,-");
		betField.setText("Bet: " + bettingAmount + " ,-");
	}

	@FXML
	public void bet100buttonOnAction() {
		double bettingAmount = 100; 
		blackJackMain.bet(bettingAmount);
		balanceField.setText("Bank: $" + blackJackMain.showBalance());
		betField.setText("Bet: " + bettingAmount + " ,-");
	}

	@FXML
	public void bet200buttonOnAction() {
		double bettingAmount = 200; 
		blackJackMain.bet(bettingAmount);
		balanceField.setText("Saldo: $" + blackJackMain.showBalance());
		betField.setText("Sats: " + bettingAmount + " ,-");
	}

	
	@FXML
	public void hitButtonOnAction() {
		blackJackMain.drawCard();
		
		if (firstCard.getImage() == null) {
			firstCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
		} 
		else if (secondCard.getImage() == null) {
			secondCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
		}
		else {
			thirdCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
		}
		
		int playerHandValue = blackJackMain.getPlayer().getHandValue();
		playerTotal.setText("Your Hand: " + playerHandValue);

		if (blackJackMain.hasBlackJack()) {
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
		
		if (blackJackMain.dealerWins()) {
			endText.setText("HUSET VANT");
			activateAvailableButtons();
		} 
		else if (blackJackMain.isTie()) {
			endText.setText("UAVGJORT");
		}
		else {
			endText.setText("DU VANT");
		}
	}

	public void dealForDealer() {
		
		while (blackJackMain.drawCardDealer() != null); {
		
			if (dealerFirstCard.getImage() == null) {
				dealerFirstCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
			} 
			else if (dealerSecondCard.getImage() == null) {
				dealerSecondCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
			}
			else {
				dealerThirdCard.setImage(blackJackMain.getCardImage(blackJackMain.getLastCardInHandDealer()));
			}
			
			int playerHandValue = blackJackMain.getPlayer().getHandValue();
			playerTotal.setText("Your Hand: " + playerHandValue);
	
			if (blackJackMain.hasBlackJack()) {
				blackjack();
			}
			else {
				bust();
			}
		}

	}
		
	public void blackjack() {
		dealForDealer();
		if (blackJackMain.hasBlackJack() && blackJackMain.hasBlackJackDealer()) {
			endText.setText("UAVGJORT");
		}
		else if (blackJackMain.isBustDealer()) {
			endText.setText("BLACKJACK");
		}
	}
	
	public void bust() {
		endText.setText("HUSET VANT");
	}
			
	public void resetGame() {
		endText.setText(null);
		double balance = blackJackMain.getPlayer().getBalance();
		
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
		resetButton.setDisable(true);
	}
	
	@FXML
	public void activateAll() {
		bet20Button.setDisable(false);
		bet100Button.setDisable(false);
		bet200Button.setDisable(false);
		startButton.setDisable(false);
		hitButton.setDisable(false);
		standButton.setDisable(false);
		resetButton.setDisable(false);
	}
	
	@FXML
	public void activateAvailableButtons() {
		
		double balance = Double.parseDouble(blackJackMain.showBalance());
	
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
			resetButton.setDisable(false);
		}
		// if (hitButton.isDisable() && standButton.isDisable()) {
		// 	startButton.setDisable(false);
		// }
	}
}