package blackjack;

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
    public BlackJackMain(String userName, double balance) {
        deck = new CardDeck();
        dealer = new Dealer(deck);
        player = new Player(userName, balance, deck);
    }
    
   
    
    private static String[] menuItems
    = {
        "1. Registrer spillkonto ",
        "2. Innskudd",
        "3. Sjekk saldo",
        "4. Uttak",
        "5. Start ny runde"
    };
    
    

    private static void start() {
        boolean quit = false;
        try {
	        while (!quit) {
	            int menuSelection = getMenuChoice();
	            switch (menuSelection) {
	                case REGISTER_ACCOUNT:
	                	System.out.print("Tast inn et brukernavn: "); String username = reader.nextLine(); 
	                	System.out.print("Tast inn et ønsket beløp: "); double startDeposit = Double.parseDouble(reader.nextLine());
	                	mainClass = new BlackJackMain(username, startDeposit);
	                	System.out.println("Vellykket registrering!");
	                    break;
	
	                case DEPOSIT_MONEY_FROM_ACCOUNT:
	                	if(mainClass != null){
	                		System.out.println("Innskudd");
	                    	System.out.print("Tast inn ønsket beløp: "); double depositAmount = Double.parseDouble(reader.nextLine());
	                    	mainClass.player.deposit(depositAmount);
	                        System.out.println("Ny saldo: " + mainClass.player.getBalance());
	                	} else {
	                		System.err.println("Spiller er ikke registrert");
						}
	                    break;
	
	                case CHECK_BALANCE:
	                	if(mainClass != null)
	                    	System.out.println("Saldoen din er: " + mainClass.player.getBalance());
	                    break;
	
	                case WITHDRAW_MONEY_FROM_ACCOUNT:
	                	if(mainClass != null){
	                		System.out.println("Innskudd");
	                    	System.out.print("Tast inn ønsket beløp: "); double withdrawAmount = reader.nextDouble();
	                    	mainClass.player.withdraw(withdrawAmount);
	                        System.out.println("Ny saldo: " + mainClass.player.getBalance());
	                	} else {
	                		System.err.println("Spiller er ikke registrert");
						}
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
	            }
	        } 
        } catch (Exception e) {
        	System.out.println("Feil!");
        }
    }

    static BlackJackMain mainClass = null;
    
    static void gamePlay() {
    	System.out.println("Velkommen " + mainClass.player.getUserName());
    	System.out.println("Ønsket spillbeløp: "); double bet = Double.parseDouble(reader.nextLine());
    	mainClass.player.withdraw(bet);
    	System.out.println(mainClass.player.drawCard());
    	
    	while(mainClass.player.getBalance() > 0) {
    		System.out.println("Stå(1) eller trekke nytt kort(2)"); int valg = Integer.parseInt(reader.nextLine());
    		if (valg == 1) {
				System.out.println(mainClass.player);
			} else if (valg == 2) {
				
			}
    	}
    }

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
    
    
  
   public static void main(String[] args) {
	   start();
   }
}
