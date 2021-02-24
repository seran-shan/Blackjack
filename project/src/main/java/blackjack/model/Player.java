package blackjack.model;

import java.lang.IllegalArgumentException;
import java.util.Date;

public class Player extends Hand {
	private double balance;
	private Person person;

	public Player(String name, String userName, String email, Date birthday, char gender, double balance, CardDeck deck) {
		super(deck);
		this.balance = balance;
		if (!isValidBalance()) {
			throw new IllegalArgumentException("Kan ikke være negativt beløp!");
		}
		this.person = new Person(name, userName, email, birthday, gender);
	}
	
	public String getUserName() {
		return person.getUsername();
	}
	
	public boolean isBlackJack() {
		if(getHandValue() == BLACK_JACK) 
			return true;
		
		else 
			return false;
	}
	
	public double getBalance() {
		return balance;
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
		if (balance > 0) {
			return true;
		} return false;
	}
	
	@Override
	public String toString() {
		System.out.println(getUserName());
		return super.toString();
	}
}
