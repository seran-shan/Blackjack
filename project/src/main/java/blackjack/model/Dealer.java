package blackjack.model;

public class Dealer extends Hand {
	private final String dealerName = "MoneyMitch the dealer";
	private final int DEALER_STOP_DRAW = 17;
	

	public Dealer(CardDeck deck) {
		super(deck);
	}
	
	public String getDealerName() {
		return dealerName;
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
	
	public boolean isDealerAtStand() {
		if (getHandValue() < DEALER_STOP_DRAW) 
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		System.out.println(getDealerName());
		return super.toString();
	}
}
