package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
	
	private Dealer dealer;
	private CardDeck deck;
	
	@BeforeEach
	private void setup() {
		deck = new CardDeck();
		dealer = new Dealer(deck);
	}
	
	@Test
	public void testIsBusted() {
		dealer.addCardToHand(new Card(Suit.HEARTS, Face.TEN));
		dealer.addCardToHand(new Card(Suit.SPADES, Face.TEN));
		dealer.addCardToHand(new Card(Suit.CLUBS, Face.TEN));
		
		assertTrue(dealer.isBusted());
	}
	
	@Test
	public void testIsBlackJack() {
		dealer.addCardToHand(new Card(Suit.HEARTS, Face.TEN));
		dealer.addCardToHand(new Card(Suit.SPADES, Face.ACE));
		
		assertTrue(dealer.isBlackJack());
	}
	
	@Test
	public void isDealerAtStand() {
		dealer.addCardToHand(new Card(Suit.HEARTS, Face.TEN));
		dealer.addCardToHand(new Card(Suit.SPADES, Face.SEVEN));
		
		assertTrue(dealer.isDealerAtStand());
	}
}
