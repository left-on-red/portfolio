package edu.wctc.pocker;

public class Card {
    private String face;
    private String suit;
    private int score;

    public Card(String face, String suit, int score) {
        this.face = face;
        this.suit = suit;
        this.score = score;
    }

    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Card{" +
                "face='" + face + '\'' +
                ", suit='" + suit + '\'' +
                ", score=" + score +
                '}';
    }
}
