package blackjack.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

	private boolean checkState(Card card, Suit suit, Face face){
		return card.getSuit() == suit && card.getFace() == face;
	}

	@Test
	public void testConstructor() {
		Assertions.assertTrue(checkState(new Card(Suit.SPADES, Face.ACE), Suit.SPADES, Face.ACE));
		Assertions.assertTrue(checkState(new Card(Suit.CLUBS, Face.TEN), Suit.CLUBS, Face.TEN));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Card(null, null);
		});
	}
	
	@Test
	public void testToString() {
		Assertions.assertEquals("SPADES-ACE", new Card(Suit.SPADES, Face.ACE).toString());
		Assertions.assertEquals("CLUBS-TEN", new Card(Suit.CLUBS, Face.TEN).toString());
	}
}
