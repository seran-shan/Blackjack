package blackjack.model;

import java.time.LocalDate;

/**
 * All logikken fra modelklassen er kombinert i denne klassen.
 * Denne er klassen er den som benyttes i kontrollerene. 
 * Bruker i all hovedsak delegeringsteknikken i hele klassen.
 * Dette er gjort for å ha minst mulig logikk i kontrollene.
 * @author seranshanmugathas
 *
 */
public class BlackJack {
	private CardDeck deck;
    private Dealer dealer;
    private Player player; 
	private FileSupport fileSupport = new FileSupport();
	
    public BlackJack(String firstName, String lastName, String username, String password, 
    		String email, LocalDate birthday, String gender, double balance) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(deck, firstName, lastName, username, password, email, birthday, gender, balance);
		fileSupport.writeToFile(getPlayer().toString());
    }
    
    public BlackJack(String username, String password) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
		player = new Player(username, password, deck);
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

	public String showBettingAmount() {
		return String.valueOf(getPlayer().getTotalBettingAmount());
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
		Double newBettingAmount = getPlayer().getTotalBettingAmount() + amount;
		getPlayer().setTotalBettingAmount(newBettingAmount);
    }

	public void resetBet() {
		depositMoneyToAccount(getPlayer().getTotalBettingAmount());
		getPlayer().setTotalBettingAmount(0);
	}
   
    public void playerWon(double amount) {
	    depositMoneyToAccount(amount);
    }
   
    public Card drawCard() {
    	Card drawnCard = getPlayer().drawCard();
	    getPlayer().addCardToHand(drawnCard);
	    return drawnCard;
    }	
    
    public void drawCardDealer() {
    	if (!getDealer().isDealerAtStand()) {
    		Card drawnCard = getDealer().drawCard();
    	    getDealer().addCardToHand(drawnCard);
    	}
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
    
    public FileSupport getFileSupport() {
		return fileSupport;
	}
    
    public int getPlayerHandValue() {
		return getPlayer().getHandValue();
	}
   
    public int getDealerHandValue() {
		return getDealer().getHandValue();
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
    
    public Card getDealerCard(int indexOfCard) {
    	Card lastCard = getDealer().getHand().get(indexOfCard);
		return lastCard;
	}
    
    public void resetGame() {
    	//Legger tilbake kortene til spiller
    	getPlayer().moveCardsBack();
		
    	//Legger tilbake kortene til dealer
    	getDealer().moveCardsBack();

    	getPlayer().setTotalBettingAmount(0);
    	getDeck().shuffleDeck();
    	resetBet();
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
    	else if (getPlayerHandValue() < getDealerHandValue()) {
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
    	if (getDealer().isBlackJack() || (isBust() && !isBustDealer())) {
			return true;
		}
		return false;
	}
    
    public boolean isTie() {
    	if (getPlayerHandValue() == getDealerHandValue()) {
			return true;
		}
		return false;
	}
}