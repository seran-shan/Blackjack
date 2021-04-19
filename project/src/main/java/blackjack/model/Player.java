package blackjack.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.time.LocalDate;
import java.util.Scanner;

public class Player extends Hand {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private LocalDate birthday;
	private String gender;
	private double balance;
	private double totalBettingAmount = 0;
	private UserValidation userValidation = new UserValidation();
	private FileSupport fileSupport = new FileSupport();

	/**
	 * @param deck
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 * @param birthday
	 * @param gender
	 * @param balance
	 */
	public Player(CardDeck deck, String firstName, String lastName, String username, String password, String email,
			LocalDate birthday, String gender, double balance) {
		super(deck);
		checkFields(firstName, lastName, username, password, email, birthday, gender, balance);
		
	}
	
	public Player(String username, String password, CardDeck deck) {
		super(deck);
		if (!userValidation.validateUsername(username)) {
			throw new IllegalArgumentException("Brukernavnet må bestå av bokstaver og tall");
		} else {
			this.username = username;
		}
		if (!userValidation.validatePassword(password)) {
			throw new IllegalArgumentException("Passordet krever: \n" +
											   "Et tall, en liten bokstav, og en stor bokstav");
		} else {
			this.password = password;
		}
		try {
			if (fileSupport.checkIfUserExist(username, password)) {
				setExisistingPlayerInfo();
			} else {
				throw new IllegalStateException("Prøv igjen!");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fant ikke filen");
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstName;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the totalBettingAmount
	 */
	public double getTotalBettingAmount() {
		return totalBettingAmount;
	}

	/**
	 * @param totalBettingAmount the totalBettingAmount to set
	 */
	public void setTotalBettingAmount(double totalBettingAmount) {
		this.totalBettingAmount = totalBettingAmount;
	}

	public boolean isBlackJack() {
		if(getHandValue() == BLACK_JACK) 
			return true;
		
		else 
			return false;
	}

	public void setExisistingPlayerInfo() throws FileNotFoundException {
		String userInfoPath = "src/main/resources/blackjack/fxui/userinfo/BlackJackUsers.txt";
		Scanner scanner = new Scanner(new File(userInfoPath));
		String[] lineInfo = null;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineInfo = line.split(",");

			String checkUsername = lineInfo[2];
			String checkPassword = lineInfo[3];

			if (checkUsername.equals(getUsername()) && checkPassword.equals(getPassword())) {
				this.firstName = lineInfo[1];
				this.lastName = lineInfo[2];
				this.email = lineInfo[4];
				this.birthday = LocalDate.parse(lineInfo[5]);
				this.gender = lineInfo[6];
				this.balance = Double.parseDouble(lineInfo[7]);
				break;
			}
		}
		scanner.close();
	}
	
	public void deposit(double depositAmpount) {
		balance += depositAmpount;
		if(!isValidBalance(depositAmpount)) {
			balance -= depositAmpount;
			throw new IllegalArgumentException("Beløpet kan ikke være negativt!");
		}
		
	}
	
	public void withdraw(double withdrawAmount) {
		balance -= withdrawAmount;
		if(!isValidBalance(withdrawAmount)) {
			balance += withdrawAmount;
			throw new IllegalArgumentException("Beløpet overskrider saldoen!");
		}
	}
	
	private boolean isValidBalance(double amount) {
		if (amount >= 0) {
			return true;
		} return false;
	}
	
	private void checkFields(String firstName, String lastName, String username, String password, String email,
			LocalDate birthday, String gender, double balance) {
		if (!userValidation.validateFirstName(firstName)) {
			throw new IllegalArgumentException("Fornavn må bestå av bokstaver");
		} else {
			this.firstName = firstName;
		}
		if (!userValidation.validateLastName(lastName)) {
			throw new IllegalArgumentException("Etternavn må bestå av bokstaver");
		} else {
			this.lastName = lastName;
		}
		if (!userValidation.validateUsername(username)) {
			throw new IllegalArgumentException("Brukernavnet må bestå av bokstaver og tall");
		} else {
			this.username = username;
		}
		if (!userValidation.validatePassword(password)) {
			throw new IllegalArgumentException("Passordet krever: \n" +
											   "Et tall, en liten bokstav, og en stor bokstav");
		} else {
			this.password = password;
		}
		if (!userValidation.validateMail(email)) {
			throw new IllegalArgumentException("Eposten er ugyldig");
		} else {
			this.email = email;
		}
		if (birthday == null || !userValidation.validateBirthday(birthday)) {
			throw new IllegalArgumentException("Du må være 18 år for å bruke appen");
		} else {
			this.birthday = birthday;
		}
		if (!userValidation.validateGender(gender)) {
			throw new IllegalArgumentException("Velg kjønn");
		} else {
			this.gender = gender;
		}
		if (!userValidation.validateBalance(String.valueOf(balance))) {
			throw new IllegalArgumentException("Balanse krav: \n" + 
											   "Kun tall og 0kr eller mer");
		} else {
			this.balance = balance;
		}
	}

	@Override
	public String toString() {
		return getFirstname() + ',' + 
		       getLastname() + ',' + 
			   getUsername() + ',' + 
			   getPassword() + ',' + 
			   getEmail() + ',' + 
			   getBirthday() + ',' + 
			   getGender() + ',' + 
			   getBalance();
	}
}
