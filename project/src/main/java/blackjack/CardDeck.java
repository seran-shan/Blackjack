package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
	private ArrayList<Card> cards = new ArrayList<>();
	//private final int NUM_OF_CARDS = 52; //Muligens bruke denne for å kontrollere at kortstokken inneholder 52 kort.
	private final int CARDS_PER_TYPE = 13;
	
	
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
	 * Burde vurdere å lage "enums" for suit og face, slik at vi slipper å definere suits og face i denne metoden.
	 */
	public void createFullDeck() {
		//String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		char[] suits = {'S', 'H', 'D', 'C'};
		
		for (char suit : suits) {
			for (int i = 1; i <= CARDS_PER_TYPE; i++) {
				cards.add(new Card(suit, i));
			}
 		}
	}
	
	
}
