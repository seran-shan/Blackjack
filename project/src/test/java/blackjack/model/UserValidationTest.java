package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UserValidationTest {
	
	private UserValidation userValidation = new UserValidation();
	
	@Test
	public void testValidateFirstName() {
		assertTrue(userValidation.validateFirstName("Sander"));
		assertFalse(userValidation.validateFirstName("!Sander"));
	}
	
	@Test
	public void testValidateLastName() {
		assertTrue(userValidation.validateFirstName("Olsen"));
		assertFalse(userValidation.validateFirstName("!Olsen"));
	}
	
	@Test
	public void testValidateMail() {
		assertTrue(userValidation.validateMail("Sander@gmail.com"));
		assertFalse(userValidation.validateMail("Sander@gmail.cåm"));
		assertFalse(userValidation.validateMail("!Sander@gmail.com"));
	}
	
	@Test
	public void testValidateBirthday() {
		assertTrue(userValidation.validateBirthday(LocalDate.of(2001, 8, 26)));
		assertFalse(userValidation.validateBirthday(LocalDate.now()));
	}
	
	@Test
	public void testValidateGender() {
		assertTrue(userValidation.validateGender("Mann"));
		assertTrue(userValidation.validateGender("Dame"));
		assertTrue(userValidation.validateGender("Udefinert"));
		assertFalse(userValidation.validateGender("Ape"));
	}
	
	@Test
	public void testValidateUsername() {
		assertTrue(userValidation.validateUsername("Olsen44"));
		assertFalse(userValidation.validateUsername("Olsen!"));
	}
	
	@Test
	public void testValidatePassword() {
		assertTrue(userValidation.validatePassword("Test123"));
		assertTrue(userValidation.validatePassword("Test123!"));
		assertFalse(userValidation.validatePassword("Test%"));
	}
	
	@Test
	public void testValidateBalance() {
		String balanceAsString = "200";
		assertTrue(userValidation.validateBalance(balanceAsString));
		assertFalse(userValidation.validateBalance("Ape"));
	}
}
