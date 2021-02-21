package blackjack;

import java.lang.IllegalArgumentException;

public class Player extends Hand {
	private final String userName;
	private double balance;
	private final int BLACK_JACK = 21;

	public Player(String userName, double balance, CardDeck deck) {
		super(deck);
		//checkNegativeBalance(balance);
		this.userName = userName;
		this.balance = balance;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public boolean isBusted() {
		if(getHandValue() > BLACK_JACK)
			return true;
		else
			return false;
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
		//checkNegativeBalance(depositAmpount);
		balance += depositAmpount;
	}
	
	public void withdraw(double withdrawAmount) {
		//checkNegativeBalance(withdrawAmount);
		balance += withdrawAmount;
	}
	
	
	private void checkNegativeBalance(double money) {
		if ((balance + money) < 0 || (balance - money) < 0 || balance < 0) {
			throw new IllegalArgumentException("Saldoen kan ikke være negativ");
		}
	}
	
	@Override
	public String toString() {
		getUserName();
		return super.toString();
	}
}
