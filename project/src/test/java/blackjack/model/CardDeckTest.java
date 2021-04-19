package blackjack.model;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CardDeckTest {

	private CardDeck cardDeck;
	
	@BeforeEach
	public void setup() {
		cardDeck = new CardDeck();
	}
	
	@Test
	void testCreateFullDeck() {
		setup();
		int numberOfCards = 52;

		Assertions.assertEquals(cardDeck.getCards().size(), numberOfCards);
		
		cardDeck.getCards().remove(0);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			cardDeck.checkFullDeck();
		});
    }

    @Test
    void testShuffleDeck() {
    	setup();
        ArrayList<Card> deckBeforeShuffle = cardDeck.getCards();
        cardDeck.shuffleDeck();
        ArrayList<Card> deckAfterShuffle = cardDeck.getCards();
       
        Assertions.assertNotEquals(deckBeforeShuffle, deckAfterShuffle);
    }

}
