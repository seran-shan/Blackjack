package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	private ArrayList<Card> cards = new ArrayList<>();
	private final int NUM_OF_CARDS = 52; //For å kontrollere at kortstokken inneholder 52 kort.
	
	/**
	 * Opprettes en hel kortstokk når et instans av klassen CardDeck opprettes.
	 */
	public CardDeck() {
		createFullDeck();
	}
	
	/**
	 * Get-metode
	 * @return liste av alle kortene i bunken
	 */
	public ArrayList<Card> getCards() {
        return cards;
    }
	
	/**
	 * Bruker shuffle metoden i Collections biblioteket.
	 */
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Bruker verdiene i enumene definert for type og verdi,
	 * for å opprette kort gjennom Card objektet. 
	 */
	public void createFullDeck() {
		for (Suit suit : Suit.values()) {
			for (Face face : Face.values()) {
				Card card = new Card(suit, face);
				cards.add(card);
			}
 		}
		checkFullDeck();
	}
	
	
	public void giveFaceValue() {
	
	}
	
	/**
	 * Sjekker om kortstokken inneholder 52 kort.
	 */
	private void checkFullDeck() {
		if (cards.size() != NUM_OF_CARDS) {
			throw new IllegalArgumentException("Kortstokken inneholder ikke 52 kort");
		}
	}
}


