package blackjack.model;

import java.util.Objects;

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
        if (!(suit instanceof Suit)) {
            throw new IllegalArgumentException("Kortet må være av riktig sort");
        }
    }

    private void checkValidFace(Face face) {
        if (!(face instanceof Face)) {
            throw new IllegalArgumentException("Kortet må ha gyldig verdi");
        }
    }

    public String toString() {
        return String.format("%s-%s", getSuit(), getFace());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(face, suit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return face == other.face && suit == other.suit;
    }
}
