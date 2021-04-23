package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	private CardDeck deck;
	private Player player;
	
	@BeforeEach
	private void setup() throws FileNotFoundException {
		deck = new CardDeck();
		player = new Player("Test12", "Test123", deck);
	}
	
	@Test
	public void testSetExisistingPlayerInfo() {
		assertNotNull(player.getFirstname());
		assertNotNull(player.getLastname());
		assertNotNull(player.getEmail());
		assertNotNull(player.getBirthday());
		assertNotNull(player.getGender());
		assertNotNull(player.getBalance());
	}
	
	@Test
	public void testDeposit() {
		double oldBalance = player.getBalance();
		player.deposit(200);
		double newBalance = player.getBalance();
		
		assertEquals(200, newBalance - oldBalance);
		
		assertThrows(IllegalArgumentException.class, () -> {
			player.deposit(-200);
		});
	}
	
	@Test
	public void testWithdraw() {
		double oldBalance = player.getBalance();
		player.withdraw(200);
		double newBalance = player.getBalance();
		
		assertEquals(200, oldBalance - newBalance);
		
		assertThrows(IllegalArgumentException.class, () -> {
			player.withdraw(-200);
		});
	}
}
