package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.time.LocalDate;

//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
	
	private BlackJack blackjack;
	
	@Test
	public void testRegConstructor() {
		
		assertDoesNotThrow(() -> {
			blackjack = new BlackJack("JUnitTest", "JUnitTest", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 1000);
		});
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest1", "JUnitTest", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest", "JUnitTest1", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest", "JUnitTest", "JUni!t5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest", "JUnitTest", "JUnit5", "junit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest", "JUnitTest", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.nø" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest1", "JUnitTest", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Ape", 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("JUnitTest1", "JUnitTest", "JUnit5", "JUnit5", 
					"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", -200);
		});
	}
	
	@Test
	public void testLoginConstructor() {
		
		assertDoesNotThrow(() -> {
			blackjack = new BlackJack("JUnit5", "JUnit5");
		});
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("ugyldig_brukernavn","Gyldig1passord");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack = new BlackJack("gyldigbrukernavn", "ugyldig_passord");
		});
		assertThrows(IllegalStateException.class, () -> {
			blackjack = new BlackJack("Ikkeregistrertbrukernavn", "Ikkeregistrertpassord1");
		});
	}
	
	@Test
	public void testPlayerAccount() throws FileNotFoundException {
		blackjack = new BlackJack("JUnit5", "JUnit5");
		
		assertThrows(IllegalArgumentException.class, () -> {
			blackjack.playerWon(-200);
		});
		
		assertDoesNotThrow(() -> {
			blackjack.bet(200);
		});

		blackjack.resetBet();
		assertEquals(0, blackjack.getPlayer().getTotalBettingAmount());
	}
	
	@Test
	public void testGame() throws FileNotFoundException {
		blackjack = new BlackJack("JUnit5", "JUnit5");
		
		assertTrue(blackjack.drawCard() instanceof Card);
		
		blackjack.drawCardDealer();
		assertNotNull(blackjack.getDealerHandValue(), "Hånda skal inneholde et kort");
		
		blackjack.resetGame();
		assertEquals(blackjack.getPlayerHandValue(), 0, "Håndverdien skal være 0");
		assertEquals(blackjack.getDealerHandValue(), 0, "Håndverdien skal være 0");
		
		//Test av seiers og tap sjekk - player.
		blackjack.getPlayer().getHand().add(new Card(Suit.HEARTS, Face.ACE));
		blackjack.getPlayer().getHand().add(new Card(Suit.HEARTS, Face.TEN));
		assertTrue(blackjack.hasBlackJack(), "Spillers hånd skal ha verdien 21");
		
		//Test av seiers og tap sjekk - dealer.
		blackjack.getDealer().getHand().add(new Card(Suit.CLUBS, Face.ACE));
		blackjack.getDealer().getHand().add(new Card(Suit.CLUBS, Face.TEN));
		assertTrue(blackjack.hasBlackJackDealer(), "Dealers hånd skal ha verdien 21");
		
		//Test av uangjort
		assertTrue(blackjack.isTie(), "Spiller og dealer skal ha lik håndverdi");
		
		blackjack.getPlayer().getHand().add(new Card(Suit.SPADES, Face.ACE));
		assertTrue(blackjack.isBust(), "Spiller skal ha tapt");
		
		blackjack.getDealer().getHand().add(new Card(Suit.DIAMONDS, Face.ACE));
		assertTrue(blackjack.isBustDealer(), "Dealer skal ha tapt");
	}
}