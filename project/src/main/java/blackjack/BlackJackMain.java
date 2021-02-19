package blackjack;

public class BlackJackMain {
    private CardDeck deck;
    private Hand hand;

    /**
     * 
     */
    public BlackJackMain() {
        deck = new CardDeck();
        hand = new Hand(deck);
    }

    public Hand getHand() {
        return hand;
    }
    
}