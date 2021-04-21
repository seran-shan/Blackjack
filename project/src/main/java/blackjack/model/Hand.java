package blackjack.model;

import java.util.ArrayList;

/**
 * Parent klasse for player og dealer
 * @author seranshanmugathas og pravinthevakan
 *
 */
public class Hand {
    private CardDeck deck;
    private final int INDEX_OF_FIRST_CARD = 0;
    private ArrayList<Card> hand;
    protected final int BLACK_JACK = 21;

    /**
     * konstruktør som oppretter et objekt av hand
     * her initieres et objektvariablene deklarert over
     * @param deck
     */
    public Hand(CardDeck deck) {
    	if (deck == null) {
			throw new IllegalArgumentException("Må inneholde kortstokk");
		}
        this.deck = deck;
        hand = new ArrayList<>();
    }

    /**
     * Metode for trekke et kort fra kortstokken.
     */
    public Card drawCard() {
        Card drawnCard = deck.getCard(INDEX_OF_FIRST_CARD);
        deck.getCards().remove(INDEX_OF_FIRST_CARD);
        return drawnCard;
    }

    /**
     * Tilgangssmetode for å hente kortene til spilleren/dealeren
     * @return selve hånda spillerene og dealeren får
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Gir både spilleren eller dealeren nytt kort.
     */
    public void addCardToHand(Card card) {
        getHand().add(card);
    }
    
    public CardDeck getDeck() {
		return deck;
	}

    /**
     * Setter verdien av hvert trukket kort.
     * @return verdien på kortet som er trukket
     */
	public int getHandValue(){
		int handValue = 0;
        if (hasEmptyHand()) {
			return handValue;
		}
        else {
	        for (Card card : getHand()) {
	            switch(card.getFace()){
	                case TWO -> handValue += 2; 
	                case THREE -> handValue += 3;  
	                case FOUR -> handValue += 4;  
	                case FIVE -> handValue += 5;  
	                case SIX -> handValue += 6;  
	                case SEVEN -> handValue += 7;
	                case EIGHT -> handValue += 8;
	                case NINE -> handValue += 9; 
	                case TEN -> handValue += 10;  
	                case JACK -> handValue += 10; 
	                case QUEEN -> handValue += 10;
	                case KING -> handValue += 10; 
	                case ACE -> {
	                            if (handValue <= 10)
	                                handValue += 11;
	                            else
	                                handValue += 1;
	                            }
	                default -> System.err.println("Ugyldig verdi");
	            }			
	        }
	        return handValue;
        }
	}

	/**
	 * Flytter kortene fra hånda tilbake til bunken.
	 */
    public void moveCardsBack() {
        //checkEmptyHand();
        for (Card card : hand) {
        	getDeck().getCards().add(card);
        } 
        hand = new ArrayList<>();
    }

    /**
     * Sjekker om spilleren har fått tildelt kort
     */
    private void checkEmptyHand() {
        if (getHand().isEmpty()) {
            throw new IllegalStateException("Ingen kort tildelt");
        }
    }
    
    private boolean hasEmptyHand() {
        if (getHand().isEmpty()) {
            return true;
        }
		return false;
    }

    /**
    * Sjekker duplikat
    * @param newCard
    */
	private void checkDuplicate(Card newCard) {
		for (Card card : deck.getCards()) {
			if(card.equals(newCard)) {
                throw new IllegalArgumentException();
            }
		}
	}
	
	/**
	 * Sjekker om man har Blackjack eller ikke
	 * @return True hvis man har 21 poeng, eller false dersom man har 
	 * over/under 21 poeng
	 */
	protected boolean isBlackJack() {
		return getHandValue() == BLACK_JACK;
	}
	
	protected boolean isBusted() {
		return getHandValue() > BLACK_JACK;
	}
	
    /**
     * En toString metode for å printe ut hånda
     */
	@Override
    public String toString() {
        String out = "";
        for (Card card : hand) {
            out += card.toString() + "\n";
        }
        out += "Verdi: " + getHandValue() + "\n";
        return out;
    }
	
	
}