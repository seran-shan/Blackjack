package blackjack.model;

import java.util.Objects;

/**
 * Klassen har i oppgave å danne et kort bestående av et gyldig mønster og verdi
 * @author seranshanmugathas og pravinthevakan
 *
 */
public class Card {
    private final Suit suit;
    private final Face face;
    

    /**
     * Konstruktør oppretter et kort instans bestående av både tall og mønster
     * @param suit
     * @param face
     */

    public Card(Suit suit, Face face) {
        checkValidSuit(suit);
        checkValidFace(face);
        this.suit = suit;
        this.face = face;
    }

    /**
     * Tilgangsmetode for mønster på kortet
     * @return mønsteret til kortet
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Tilgangsmerode for verdien på kortet
     * @return verdien til kortet 
     */
    public Face getFace() {
        return face;
    }

    /**
     * Sjekker om argumentet som brukes til denne metoden er et instans av suit
     * og ikke er null
     * @param suit
     */
    private void checkValidSuit(Suit suit) {
        if (!(suit instanceof Suit) && (suit == null) ) {
            throw new IllegalArgumentException("Kortet må være av riktig sort");
        }
    }


    /**
     * Sjekker om argumentet som brukes til denne metoden er et instans av face
     * og ikke er null
     * @param face
     */
    private void checkValidFace(Face face) {
        if (!(face instanceof Face) && (face == null)) {
            throw new IllegalArgumentException("Kortet må ha gyldig verdi");
        }
    }

    /**
     * ToString metode for å skrive ut et kort bestående av et mønster og verdi
     */
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

    /**
     * Sjekker om 2 kort er like ved å sammenligne mønsteret og verdien
     * @param obj
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
