package blackjack;

public class Card {
    private final Suit suit;
    private final Face face;

    public Card(Suit suit, Face face) {
        checkValidSuit(suit);
        checkValidFace(face);
        this.suit = suit;
        this.face = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    private void checkValidSuit(Suit suit) {
        if (!(suit instanceof Suit)){
            throw new IllegalArgumentException("Kortet må være av riktig sort");
        }
    }

    private void checkValidFace(Face face) {
        if (!(face instanceof Face)){
            throw new IllegalArgumentException("Kortet må ha gyldig verdi");
        }
    }

    public String toString() {
        return String.format("%s%s", getSuit(), getFace());
    }
}
