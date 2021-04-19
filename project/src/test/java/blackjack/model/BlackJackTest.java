package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
	
	private BlackJack blackjack;
	private FileSupport fileSupport = new FileSupport();
	
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
	public void testPlayerAccount() {
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
	public void testGame() {
		blackjack = new BlackJack("Test12", "Test123");
		
		assertTrue(blackjack.drawCard() instanceof Card);
		
		blackjack.drawCardDealer();
		assertNotNull(blackjack.getDealerHandValue());
		
		blackjack.resetGame();
		assertEquals(blackjack.getPlayerHandValue(), 0);
		assertEquals(blackjack.getDealerHandValue(), 0);
	}
	
//	@AfterAll
//	public void removeTestUser() {
//		blackjack = new BlackJack("username", "Password1");
//		blackjack.getFileSupport().saveNewBalance(null, "JUnit5");
//	}
}