package blackjack.model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class BlackJackMain {
	private CardDeck deck;
    private Dealer dealer;
    private Player player; 

    public BlackJackMain(String firstName, String lastName, String userName, String password, String email, LocalDate birthday, String string, double balance) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(firstName, lastName, userName, password, email, birthday, string, balance, deck);
    }
    
    public BlackJackMain(String userName, String password, CardDeck deck) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(userName, password, deck);
    }
    
    public void withdrawMoneyFromAccount(String amount) {
    	Double amountDouble = Double.parseDouble(amount);
        getPlayer().withdraw(amountDouble);
	}
    
    public void withdrawMoneyFromAccount(double amount) {
        getPlayer().withdraw(amount);
	}
    
    public String showBalance() {
    	Double balance = getPlayer().getBalance();
    	return String.valueOf(balance);
	}
    
    public void depositMoneyToAccount(String amount) {
    	Double amountDouble = Double.parseDouble(amount);
        getPlayer().deposit(amountDouble);
	}
    
    public void depositMoneyToAccount(double amount) {
        getPlayer().deposit(amount);
	}
   
    public void bet(double amount) {
    	withdrawMoneyFromAccount(amount);
    }
   
    public void playerWon(double amount) {
	    depositMoneyToAccount(amount);
    }
   
    public Card drawCard() {
    	Card drawnCard = getPlayer().drawCard();
	    getPlayer().addCardToHand(drawnCard);
	    return drawnCard;
    }	
    
    public Card drawCardDealer() {
    	if (!getDealer().isDealerAtStand()) {
    		Card drawnCard = getDealer().drawCard();
    	    getPlayer().addCardToHand(drawnCard);
    	    return drawnCard;
		}
		return null;	
    }
   
    public Player getPlayer() {
	    return player;
    }
   
    public Dealer getDealer() {
	    return dealer;
    }
   
    public CardDeck getDeck() {
	    return deck;
    }
    
    public int getPlayerHandValue() {
		return getPlayer().getHandValue();
	}
   
    public int getDealerrHandValue() {
		return getDealer().getHandValue();
	}
    
    public Image getCardImage(Card card) {
		 String imagePath = "project/src/main/resources/blackjack/fxui/pictures/cardImages/";
		 Image cardImage = new Image(imagePath + card.getSuit() + "-" + card.getFace());
		 return cardImage;
    }
    
    public Card getLastCardInHandPlayer() {
    	int cardIndex = getPlayer().getHand().size() - 1;
    	Card lastCard = getPlayer().getHand().get(cardIndex);
		return lastCard;
	}
    
    public Card getLastCardInHandDealer() {
    	int cardIndex = getDealer().getHand().size() - 1;
    	Card lastCard = getDealer().getHand().get(cardIndex);
		return lastCard;
	}
    
    public boolean hasBlackJack() {
    	if (getPlayer().isBlackJack()) {
			return true;
		}
		return false;
	}
    
    public boolean hasBlackJackDealer() {
    	if (getDealer().isBlackJack()) {
			return true;
		}
		return false;
	}
    
    public boolean isBust() {
    	if (getPlayer().isBusted()) {
			return true;
		}
    	else if (getPlayerHandValue() < getDealerrHandValue()) {
			return true;
		}
		return false;
	}
    
    public boolean isBustDealer() {
    	if (getDealer().isBusted()) {
			return true;
		}
		return false;
	}
    
    public boolean dealerWins() {
    	if (getDealer().isBlackJack() && isBust()) {
			return true;
		}
		return false;
	}
    
    public boolean isTie() {
    	if (getPlayerHandValue() == getDealerrHandValue()) {
			return true;
		}
		return false;
	}
}
