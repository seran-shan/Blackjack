package blackjack.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.scene.image.Image;

public class BlackJackMain {
	private CardDeck deck;
    private Dealer dealer;
    private Player player; 
	private String userInfoPath = "src/main/resources/blackjack/fxui/userinfo/BlackJackUsers.txt";

    public BlackJackMain(String firstName, String lastName, String username, String password, String email, LocalDate birthday, String gender, double balance) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(deck, firstName, lastName, username, password, email, birthday, gender, balance);
		writeToFile(userInfoPath);
    }
    
    public BlackJackMain(String username, String password) {
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

	/**
	 * @return the userInfoPath
	 */
	public String getUserInfoPath() {
		return userInfoPath;
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

	public void writeToFile(String filename) {
		try {
			PrintWriter printWriter = new PrintWriter(filename);
			printWriter.println(getPlayer());
			printWriter.flush();
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean checkIfUserExist(String username, String password, String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		String[] lineInfo = null;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineInfo = line.split("\'");
		}
		scanner.close();
		
		String checkUsername = lineInfo[3];
		String checkPassword = lineInfo[5];

		if (checkUsername.equalsIgnoreCase(username) && checkPassword.equals(password)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		BlackJackMain blackJackMain = new BlackJackMain("seran26", "Ps!244");

		System.out.println(blackJackMain.getPlayer().getBalance());

		// try {
		// 	Scanner scanner = new Scanner(new File("src/main/resources/blackjack/fxui/userinfo/BlackJackUsers.txt"));
		// 	String[] lineInfo = {};
		// 	while(scanner.hasNextLine()) {
		// 		String line = scanner.nextLine();
		// 		lineInfo = line.split("\'");
		// 	}
		// 	scanner.close();

		// 	String checkUsername = lineInfo[3];
		// 	String checkPassword = lineInfo[5];

		// 	System.out.println(checkUsername);
		// 	System.out.println(checkPassword);
		// } catch (FileNotFoundException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
	}
}