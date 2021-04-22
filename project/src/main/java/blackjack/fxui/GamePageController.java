package blackjack.fxui;

import java.io.IOException;

import blackjack.model.BlackJack;
import blackjack.model.FileSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GamePageController{

	private BlackJack blackJack = new BlackJack();
	private FileSupport fileSupport = new FileSupport();
	
	@FXML private Button startButton, bet20Button, bet100Button, bet200Button,
				 		 hitButton, standButton, resetButton, newGameButton, backButton;
	@FXML private TextField balanceField, betField;
	@FXML private Text dealerText, playerText, endText;
	@FXML private ImageView firstCard, secondCard, thirdCard, fourthCard, 
							dealerFirstCard, dealerSecondCard, dealerThirdCard, dealerFourthCard;

	public void setBlackJack(BlackJack blackJack) {
		this.blackJack = blackJack;
	}

	@FXML
	public void initialize() {
		disableAll();
		backButton.setDisable(false);
		
		if (blackJack.getPlayer().getBalance() >= 200) {
			bet200Button.setDisable(false);
		}
		if (blackJack.getPlayer().getBalance() >= 100) {
			bet100Button.setDisable(false);
		}
		if (blackJack.getPlayer().getBalance() >= 20) {
			bet20Button.setDisable(false);
		}
		balanceField.setText("Bank: " + blackJack.showBalance() + " ,-");
		betField.setText("Bet: " + blackJack.showBettingAmount() + " ,-");
	}
	
	@FXML
	public void backButtonOnAction(ActionEvent event) throws IOException {
		blackJack.resetGame();
		fileSupport.saveNewBalance(blackJack.getPlayer().toString(),
				   				   blackJack.getPlayer().getUsername());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
		Parent root = (Parent) loader.load();

		MenuPageController menuPageController = loader.getController();
		menuPageController.setBlackJack(blackJack);

		Scene menuScene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(menuScene);
		window.show();
	}

	@FXML
	public void bet20ButtonOnAction() {
		double bettingAmount = 20; 
		blackJack.bet(bettingAmount);
		
		initialize();
		
		startButton.setDisable(false);
		resetButton.setDisable(false);
	}

	@FXML
	public void bet100ButtonOnAction() {
		double bettingAmount = 100; 
		blackJack.bet(bettingAmount);
		
		initialize();
		
		startButton.setDisable(false);
		resetButton.setDisable(false);
	}

	@FXML
	public void bet200ButtonOnAction() {
		double bettingAmount = 200; 
		blackJack.bet(bettingAmount);
		
		initialize();
		
		startButton.setDisable(false);
		resetButton.setDisable(false);
	}
	
	@FXML
	public void startButtonOnAction() {
		disableAll();
		
		//Legger inn to kort for spiller
		blackJack.drawCard();
		firstCard.setImage(getCardImagePlayer());
		blackJack.drawCard();
		secondCard.setImage(getCardImagePlayer());
		int playerHandValue = blackJack.getPlayer().getHandValue();
		playerText.setText("DIN HÅND: "  + playerHandValue);
		
		//Legger inn to kort for 
		blackJack.drawCardDealer();
		dealerFirstCard.setImage(getCardImageDealer());
		blackJack.drawCardDealer();
		dealerSecondCard.setImage(getBackOfCard());
		//int dealerHandValue = blackJack.getDealer().getHandValue();
		dealerText.setText("DEALERS HÅND");
		
		hitButton.setDisable(false);
		standButton.setDisable(false);
	}

	
	@FXML
	public void hitButtonOnAction() {
		blackJack.drawCard();
		
		if (thirdCard.getImage() == null) {
			thirdCard.setImage(getCardImagePlayer());
		}
		else {
			fourthCard.setImage(getCardImagePlayer());
		}
		
		int playerHandValue = blackJack.getPlayer().getHandValue();
		playerText.setText("DIN HÅND: "  + playerHandValue);

		if (blackJack.isBust()) {
			bust();
			disableAll();
			backButton.setDisable(false);
			newGameButton.setDisable(false);
		}
	}
	
	@FXML
	public void standButtonOnAction() {
		dealForDealer();
		
		hitButton.setDisable(true);
		standButton.setDisable(true);
		backButton.setDisable(false);
	}

	@FXML
	public void resetButtonOnAction() {
		blackJack.resetBet();
		activateAvailableButtons();
		balanceField.setText("Saldo: " + blackJack.showBalance() + " ,-");
		betField.setText("Sats: " + blackJack.showBettingAmount() + " ,-");
	}
	
	@FXML
	public void newGameButtonOnAction() {
		blackJack.resetGame();
		
		endText.setText(null);
		endText.setFill(Color.web("#d7c666"));
		dealerText.setText("DEALERS HÅND");
		playerText.setText("DIN HÅND");
		
		double balance = blackJack.getPlayer().getBalance();
		if (balance == 0) {
			disableAll();
			removeCardPics();
			backButton.setDisable(false);
			endText.setFill(Color.RED);
			endText.setText("Fyll kontoen din for å spille");
		} 
		else {
			disableAll();
			removeCardPics();
			activateAvailableButtons();
			balanceField.setText("Saldo: " + blackJack.showBalance() + " ,-");
			betField.setText("Sats: " + blackJack.showBettingAmount() + " ,-");
			backButton.setDisable(false);
			resetButton.setDisable(false);
		}
	}

	public void dealForDealer() {
		dealerSecondCard.setImage(turnCard());
		
		if (!blackJack.getDealer().isDealerAtStand()) {
			blackJack.drawCardDealer();
			dealerThirdCard.setImage(getCardImageDealer());
		}
		
		if (!blackJack.getDealer().isDealerAtStand()) {
			blackJack.drawCardDealer();
			dealerFourthCard.setImage(getCardImageDealer());
		}
		
		int dealerHandValue = blackJack.getDealer().getHandValue();
		dealerText.setText("DEALERS HÅND: " + dealerHandValue);
		
		if (blackJack.isBustDealer()) {
			playerWins();
		}
		else if (blackJack.isTie()) {
			draw();
		}
		else if (blackJack.hasBlackJack() && !blackJack.hasBlackJackDealer()) {
			playerWins();
		}
		else if (blackJack.dealerWins()) {
			bust();
		}
		
		newGameButton.setDisable(false);
	}
	
	public Image getCardImagePlayer() { 
		 String cardPath = "/blackjack/fxui/pictures/cardImages/" + blackJack.getLastCardInHandPlayer().toString() + ".png";
		 Image cardImage = new Image(getClass().getResourceAsStream(cardPath));
		 return cardImage;
    }
	
	public Image getCardImageDealer() { 
		 String cardPath = "/blackjack/fxui/pictures/cardImages/" + blackJack.getLastCardInHandDealer().toString() + ".png";
		 Image cardImage = new Image(getClass().getResourceAsStream(cardPath));
		 return cardImage;
    }
	
	public Image getBackOfCard() {
		String cardPath = "/blackjack/fxui/pictures/cardImages/backCover.png";
		Image cardImage = new Image(getClass().getResourceAsStream(cardPath));
		return cardImage;
	}
	
	public Image turnCard() {
		int indexOfTurnedCard = 1;
		String cardPath = "/blackjack/fxui/pictures/cardImages/" + blackJack.getDealerCard(indexOfTurnedCard) + ".png";
		Image cardImage = new Image(getClass().getResourceAsStream(cardPath));
		return cardImage;
	}
		
	public void playerWins() {
		if (blackJack.hasBlackJack()) {
			endText.setText("BLACKJACK");
		}
		else if (blackJack.isBustDealer()) {
			endText.setText("DU VANT");
		}
		else if (!blackJack.isBust() && blackJack.isBustDealer()) {
			endText.setText("DU VANT");
		}
		
		Double winningAmount = blackJack.getPlayer().getTotalBettingAmount() * 2;
		blackJack.playerWon(winningAmount);
		blackJack.getPlayer().setTotalBettingAmount(0);
	}
	
	public void draw() {
		endText.setText("UAVGJORT");
		blackJack.resetBet();
	}
	
	public void bust() {
		endText.setText("HUSET VANT");
	}
			
	@FXML
	public void disableAll() {
		backButton.setDisable(true);
		bet20Button.setDisable(true);
		bet100Button.setDisable(true);
		bet200Button.setDisable(true);
		startButton.setDisable(true);
		hitButton.setDisable(true);
		standButton.setDisable(true);
		resetButton.setDisable(true);
		newGameButton.setDisable(true);
	}
	
	@FXML
	public void removeCardPics() {
		firstCard.setImage(null);
		secondCard.setImage(null);
		thirdCard.setImage(null);
		fourthCard.setImage(null);
		dealerFirstCard.setImage(null);
		dealerSecondCard.setImage(null);
		dealerThirdCard.setImage(null);
		dealerFourthCard.setImage(null);
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
		
		double balance = blackJack.getPlayer().getBalance();
	
		if (balance < 200) {
			bet200Button.setDisable(true);
		} 
		else {
			bet200Button.setDisable(false);
		}
		if (balance < 100) {
			bet100Button.setDisable(true);
		} 
		else {
			bet100Button.setDisable(false);
		}
		if (balance < 50) {
			bet20Button.setDisable(true);
		}
		else {
			bet20Button.setDisable(false);
		}
	}
}