package blackjack;

import java.util.ArrayList;

public class Hand {
    private CardDeck deck;
    private final int INDEX_OF_FIRST_CARD = 0;
    private ArrayList<Card> hand;

    /**
     * @param deck
     */
    public Hand(CardDeck deck) {
        this.deck = deck;
        hand = new ArrayList<>();
    }

    /**
     * Metode får trekke et kort fra kortstokken.
     */
    public Card drawCard() {
        Card drawnCard = deck.getCard(INDEX_OF_FIRST_CARD);
        deck.getCards().remove(INDEX_OF_FIRST_CARD);
        return drawnCard;
    }

    /**
     * 
     * @return selve hånda spillerene og dealeren får
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Gir både spilleren eller dealeren nytt kort.
     */
    public void addCardToHand() {
        getHand().add(drawCard());
    }

    /**
     * Setter verdien av hvert trukket kort.
     * @return verdien på kortet som er trukket
     */
	public int getHandValue(){
        checkEmptyHand();
		int handValue = 0;
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
                case ACE -> handValue += 1;
            }			
        }
		return handValue;
	}

    public void moveCardBack() {
        checkEmptyHand();
        Card cardToMoveBack = getHand().get(INDEX_OF_FIRST_CARD);
        deck.getCards().add(cardToMoveBack);

    }

    /**
     * Sjekker om spilleren har fått tildelt kort
     */
    private void checkEmptyHand() {
        if (getHand().isEmpty()) {
            throw new IllegalStateException("Ingen kort tildelt");
        }
    }

    /**
     * En toString metode for å printe ut hånda
     */
    public String toString() {
        String out = "";
        for (Card card : hand) {
            out += card.toString() + "\n";
        }
        return out;
    }
}