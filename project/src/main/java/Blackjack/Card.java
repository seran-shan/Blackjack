package Blackjack;

import java.util.Arrays;
import java.util.List;

public class Card {
    private final char suit;
    private final int face;

    public Card(char suit, int face) {
        checkValidSuit(suit);
        checkValidFace(face);
        this.suit = suit;
        this.face = face;
    }

    public char getSuit() {
        return suit;
    }

    public int getFace() {
        return face;
    }

    private void checkValidSuit(char suit) {
        List<Character> validChars = Arrays.asList('S', 'H', 'D', 'C');
        if (!validChars.contains(suit)){
            throw new IllegalArgumentException("S (spades), H (heart), D (diamond), C (clubs)");
        }
    }

    private void checkValidFace(int face) {
        List<Integer> validFaces = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        if (!validFaces.contains(face)){
            throw new IllegalArgumentException("Tallet må være mellom 1 og 13");
        }
    }

    public String toString() {
        String out = String.valueOf(getSuit()) + String.valueOf(getFace());
        return out;
    }

    public static void main(String[] args) {
        Card card = new Card('S',1);
        System.out.println(card);
    }
}
