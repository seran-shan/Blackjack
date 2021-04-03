package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BlackJackTest {
	
	private BlackJack blackjack;
	
	@Test
	public void testConstructor() {
		blackjack = new BlackJack("JUnitTest", "JUnitTest", "Juni5", "Junit5", 
				"JUnit5@ntnu.no" ,LocalDate.of(2001, 8, 26), "Udefinert", 0);
		assertNotNull(blackjack.getPlayer(), "Spiller ble ikke initiert");
		
		
	}
}