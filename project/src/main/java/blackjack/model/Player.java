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
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		if (!isValidBalance()) {
			throw new IllegalArgumentException("Saldoen kan ikke være negativ");
		}
		this.balance = balance;
	}
	
	public Player(String username, String password, CardDeck deck) {
		super(deck);
		this.username = username;
		this.password = password;
		try {
			this.balance = getBalanceExisistingPlayer();
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

	
	
	public boolean isBlackJack() {
		if(getHandValue() == BLACK_JACK) 
			return true;
		
		else 
			return false;
	}

	public Double getBalanceExisistingPlayer() throws FileNotFoundException {
		String userInfoPath = "src/main/resources/blackjack/fxui/userinfo/BlackJackUsers.txt";
		Scanner scanner = new Scanner(new File(userInfoPath));
		String[] lineInfo = null;
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineInfo = line.split("\'");
		}
		scanner.close();

		String checkUsername = lineInfo[3];
		String checkPassword = lineInfo[5];

		if (checkUsername.equals(getUsername()) && checkPassword.equals(getPassword())) {
			String balance = lineInfo[13];
			return Double.parseDouble(balance);
		}
		return null;
	}
	
	public void deposit(double depositAmpount) {
		balance += depositAmpount;
		if(!isValidBalance()) {
			balance -= depositAmpount;
			throw new IllegalArgumentException("Beløpet kan ikke være negativt!");
		}
		
	}
	
	public void withdraw(double withdrawAmount) {
		balance -= withdrawAmount;
		if(!isValidBalance()) {
			balance += withdrawAmount;
			throw new IllegalArgumentException("Beløpet overskrider saldoen!");
		}
	}
	
	private boolean isValidBalance() {
		if (balance >= 0) {
			return true;
		} return false;
	}
	
	@Override
	public java.lang.String toString() {
		return "User{" +
				"Name='" + getFirstname() + " " + getLastname() + '\'' +
				", username='" + getUsername() + '\'' +
				", password='" + getPassword() + '\'' +
				", email='" + getEmail() + '\'' +
				", birthday='" + getBirthday() + '\'' +
				", gender='" + getGender() + '\'' +
				", balance='" + getBalance() + '\'' +
				'}';
	}
}
