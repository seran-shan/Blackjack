package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandTest {
	
	private Hand hand;
	private CardDeck deck;

	@BeforeEach
	public void setup() {
		deck = new CardDeck();
		hand = new Hand(deck);
	}
	
	@Test
	public void testDrawCard() {
        Card firstCardInDeck = deck.getCard(0);
        Card drawnCard = hand.drawCard();
        
        assertEquals(firstCardInDeck, drawnCard);
    }


	@Test
    public void testAddCardToHand() {
		Card firstCardInDeck = deck.getCard(0);
        hand.getHand().add(hand.drawCard());
        Card firstCardInHand = hand.getHand().get(0);
        
        assertEquals(firstCardInDeck, firstCardInHand);
    }
    
	@Test
	public void testGetHandValue(){
		hand.getHand().add(new Card(Suit.HEARTS, Face.TEN));
		hand.getHand().add(new Card(Suit.SPADES, Face.TWO));
		
		assertEquals(hand.getHandValue(), 12);
	}

	@Test
    public void testMoveCardsBack() {
		Card drawnCard = hand.drawCard();
        hand.getHand().add(drawnCard);
        
        hand.moveCardsBack();
        
        assertTrue(hand.getDeck().getCards().contains(drawnCard));
    }
}

