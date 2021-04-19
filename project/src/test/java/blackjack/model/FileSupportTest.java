package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class FileSupportTest {
	
	private FileSupport fileSupport = new FileSupport();
	
	@Test
	public void testFilReading() throws FileNotFoundException {
		String existingUsername = "Test12";
		String existingPassword = "Test123";
		assertTrue(fileSupport.checkIfUserExist(existingUsername, existingPassword));
		
		String nonExistingUsername = "Test11";
		assertFalse(fileSupport.checkIfUserExist(nonExistingUsername, existingPassword));
	}

	@Test
	public void testFileWriting() throws FileNotFoundException {
		CardDeck deck = new CardDeck();
		Player existingPlayer = new Player("Test12", "Test123", deck);
		double oldBalance = existingPlayer.getBalance();
		existingPlayer.deposit(200);
		fileSupport.saveNewBalance(existingPlayer.toString(), 
				existingPlayer.getUsername());
		String[] userinfoLine = fileSupport.getUserInfo(existingPlayer.getUsername()).split(",");
		
		double newBalance = Double.parseDouble(userinfoLine[7]);
		assertTrue(oldBalance < newBalance);
		
		String nonExistingUsername = "Test11";
		assertThrows(NullPointerException.class, () -> {
			fileSupport.saveNewBalance(fileSupport.getUserInfo(nonExistingUsername), 
					nonExistingUsername);
		});
	}
}
