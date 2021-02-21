package blackjack;

public class Dealer extends Hand {
	private final String dealerName = "MoneyMitch the dealer";
	private final int BLACK_JACK = 21;
	private final int DEALER_DRAW_NUM = 21;
	

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
	
	public void dealerDrawCard() {
		if (getHandValue() < DEALER_DRAW_NUM) 
			drawCard();
	}
	
	@Override
	public String toString() {
		getDealerName();
		return super.toString();
	}
}
