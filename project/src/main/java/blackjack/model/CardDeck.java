package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasse for å å håndtere en kortstokk
 * @author seranshanmugathas og pravinthevakan
 *
 */
public class CardDeck {
	private ArrayList<Card> cards;
	private final int NUM_OF_CARDS = 52; //For å kontrollere at kortstokken inneholder 52 kort.
	
	
	/**
	 * Opprettes en hel kortstokk når et instans av klassen CardDeck opprettes.
	 */
	public CardDeck() {
		this.cards = new ArrayList<>();
		createFullDeck();
		shuffleDeck();
	}

	/**
	 * Tilgangsmetode for å hente alle kortene 
	 * @return liste av alle kortene i bunken
	 */
	public ArrayList<Card> getCards() {
        return cards;
    }

	/**
	 * Tilgangsmetode for å hente et spesifikt kort fra kortstokken
	 * @param indexOfCard
	 * @return
	 */
	public Card getCard(int indexOfCard) {
		return getCards().get(indexOfCard);
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
		//sjekk om kortet ekistirerer fra før
	}

	
	/**
	 * Sjekker om kortstokken inneholder 52 kort.
	 */
	void checkFullDeck() {
		if (cards.size() != NUM_OF_CARDS) {
			throw new IllegalArgumentException("Kortstokken inneholder ikke 52 kort");
		}
	}
}