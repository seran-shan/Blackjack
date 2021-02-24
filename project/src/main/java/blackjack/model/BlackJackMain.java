package blackjack.model;

import java.util.Date;
import java.util.Scanner;

public class BlackJackMain {
    private CardDeck deck;
    private Dealer dealer;
    private Player player; 
    
    private static Scanner reader = new Scanner(System.in);
    
    private static final int REGISTER_ACCOUNT = 1;
    private static final int DEPOSIT_MONEY_FROM_ACCOUNT = 2;
    private static final int CHECK_BALANCE = 3;
    private static final int WITHDRAW_MONEY_FROM_ACCOUNT = 4;
    private static final int START_NEW_ROUND = 5;
    private static final int EXIT = 6;

    /**
     * 
     */
    public BlackJackMain(String name, String userName, String password, String email, Date birthday, char gender, double balance) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(name, userName, password, email, birthday, gender, balance, deck);
    }
    
   
    
    private static String[] menuItems
    = {
        "1. Registrer spillkonto ",
        "2. Innskudd",
        "3. Sjekk saldo",
        "4. Uttak",
        "5. Start ny runde"
    };
    
    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items..
     * The method returns the input from the user. If the input from the user is
     * invalid, 0 is returned.
     *
     * @return the menu number (between 1 and max menu item number) provided by
     *         the user.
     */
    private static int getMenuChoice() {
        int menuSelection = 0;

        System.out.println("\n**** Black Jack app ****\n");
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Vennligst velg et menyvalg (1-" + maxMenuItemNumber + "): ");

        if (reader.hasNextInt()) {
            menuSelection = Integer.parseInt(reader.nextLine());
        } else {
            System.out.println("Du må taste inn et tall, ikke tekst!");
        }
        return menuSelection;
    }
    

    private static void start() {
        boolean quit = false;
        try {
	        while (!quit) {
	            int menuSelection = getMenuChoice();
	            switch (menuSelection) {
	                case REGISTER_ACCOUNT:
	                	 registerAccount();
	                    break;
	
	                case DEPOSIT_MONEY_FROM_ACCOUNT:
	                	depositMoneyFromAccount();
	                    break;
	
	                case CHECK_BALANCE:
	                	checkBalance();
	                    break;
	
	                case WITHDRAW_MONEY_FROM_ACCOUNT:
	                	withdrawMoneyFromAccount();
	                    break;
	
	                case START_NEW_ROUND:
	                    gamePlay();
	                    break;
	
	                case EXIT:
	                	System.out.println("\nTakk for at du brukte Black Jack applikasjonen, hadet!");
	                    quit = true;
	                    reader.close();
	                    break;
	
	                default:
	                    System.out.println(
	                            "\nERROR: Vennligst oppgi et tall mellom 1 og " + menuItems.length + "..\n");
	                    getMenuChoice();
	            }
	        } 
        } catch (IllegalArgumentException iae) {
        	System.out.println(iae.getMessage());
        	//getMenuChoice();
        } catch (IllegalStateException ise) {
			System.out.println(ise.getMessage());
			//getMenuChoice();
		}
    }
    
    private static void withdrawMoneyFromAccount() {
    	if(mainClass != null){
    		System.out.println("Uttak");
        	System.out.print("Tast inn ønsket beløp: "); double withdrawAmount = reader.nextDouble();
        	mainClass.player.withdraw(withdrawAmount);
            System.out.println("Ny saldo: " + mainClass.player.getBalance() + " kr");
    	} else {
    		System.err.println("Spiller er ikke registrert");
		}
	}
    
    private static void checkBalance() {
    	if(mainClass != null)
        	System.out.println("Saldoen din er: " + mainClass.player.getBalance() + " kr");
	}
    
    private static void depositMoneyFromAccount() {
    	if(mainClass != null){
    		System.out.println("Innskudd");
        	System.out.print("Tast inn ønsket beløp: "); double depositAmount = Double.parseDouble(reader.nextLine());
        	mainClass.player.deposit(depositAmount);
            System.out.println("Ny saldo: " + mainClass.player.getBalance() + "kr");
    	} else {
    		System.err.println("Spiller er ikke registrert");
		}
	}
    
    private static void registerAccount() {
    	if (mainClass == null) {
    		System.out.println("Tast inn fornavn: "); String firstname = reader.nextLine();
    		System.out.println("Tast inn etternavn: "); String lastname = reader.nextLine();
    		String fullname = firstname + " " + lastname;
    		System.out.print("Tast inn et brukernavn: "); String username = reader.nextLine();
    		System.out.print("Tast inn et passord: "); String password = reader.nextLine();
    		System.out.print("Tast inn e-post: "); String email = reader.nextLine();
    		System.out.println("Dag: "); int day = Integer.parseInt(reader.nextLine());
    		System.out.println("Måned: "); int month = Integer.parseInt(reader.nextLine());
    		System.out.println("År: "); int year = Integer.parseInt(reader.nextLine());
    		System.out.println("Mann(M), Dame(D) eller Udefinert(0)"); String gender = reader.nextLine();
    																   char genderAsChar = gender.toUpperCase().charAt(0);
    		Date birthday = new Date((((year * 365) * (month * 12) + day)) * 24 * 60 * 60 * 1000);
        	System.out.print("Tast inn et ønsket beløp: "); double startDeposit = Double.parseDouble(reader.nextLine());
        	mainClass = new BlackJackMain(fullname, username, password, email, birthday, genderAsChar, startDeposit);
        	System.out.println("Vellykket registrering!");
        	
		} else if (mainClass != null) {
			System.out.println("Bruker er allerede registrert!");
		} else {
			System.out.println("Registrering mislyktes");
		}
    }
    
    private static void gamePlay() {
    	System.out.println("Velkommen " + mainClass.player.getUserName());
    	System.out.println("Saldo: " + mainClass.player.getBalance());
    	boolean finish = false;
    	
    	while(mainClass.player.getBalance() > 0) {
    		System.out.println("Ønsket spillbeløp: "); double bet = Double.parseDouble(reader.nextLine());
        	
    		//Stokker kortene
    		mainClass.deck.shuffleDeck();
        	System.out.println("Fordeler kort...");
        	//trekker for spiller
        	mainClass.player.addCardToHand();
        	mainClass.player.addCardToHand();
        	System.out.println(mainClass.player);
        	
        	//trekker for dealer
        	mainClass.dealer.addCardToHand();
        	System.out.println(mainClass.dealer);
        	//Dette kortet er skjult for bruker
        	mainClass.dealer.addCardToHand();
        	
        	
        	while (!finish) {
				System.out.println("Stå(1) eller trekke nytt kort(2)"); int valg = Integer.parseInt(reader.nextLine());
	    		if (valg == 1) {
					System.out.println(mainClass.player);
					while ((mainClass.dealer.isDealerAtStand() == false) && (finish == false)) {
						mainClass.dealer.addCardToHand();
						System.out.println(mainClass.dealer);
		        	}
		        	//Dealers hånd
		        	System.out.println(mainClass.dealer);
				} else if (valg == 2) {
					mainClass.player.addCardToHand();
					System.out.println(mainClass.player);
					
					
				} else {
					System.out.println("Tast inn et gyldig valg");
				}
	    		
	    		if (mainClass.player.isBusted()) {
					System.out.println("Tap");
					mainClass.player.withdraw(bet);
					finish = true;
				}
	
	        	//dealers hand vs player
				else if (mainClass.dealer.getHandValue() > mainClass.player.getHandValue() && finish != true) {
	        		System.out.println("\nDealer vant");
	        		mainClass.player.withdraw(bet);
	        		finish = true;
	    		}
	        	
	        	//Se om det er uavgjort
				else if (mainClass.player.getHandValue() == mainClass.player.getHandValue()) {
					System.out.println("Uavgjort");
					finish = true;
				}
	        	
	        	//Se om spiller vinenr
				else if (mainClass.dealer.getHandValue() < mainClass.player.getHandValue()) {
					System.out.println("Gratulerer! Du vant!");
					mainClass.player.deposit(bet);
					finish = true;
				} 
				else if (finish == false) {
					//dealer vinner
					System.out.println("Dealer vinner.");
					mainClass.player.withdraw(bet);
				} 
	    		
				else {
					System.out.println("feil!");
				}
        	}
        	//legge kortene tilbake
        	for (int i = 1; i <= mainClass.player.getHand().size(); i++) {
        		mainClass.player.moveCardBack();
        	}
    	}
    	System.out.println("Takk for at du spilte!");	
   }
   
    private static void exitApplication() {
    	
    }
    
    private static BlackJackMain mainClass = null;

    public static void main(String[] args) {
	   start();
  	}
}
