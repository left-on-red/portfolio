package edu.wctc.pocker;

import java.util.List;

public class Poker {

    public boolean isStraight(List<Card> hand) {
        boolean isTheHand = true;
        int prevScore = 0;
        int score = 0;
        for (int i=0;i<5;i++) {
            Card card = hand.get(i);
            score = card.getScore();
            if ((score != prevScore+1) && (i != 0)) isTheHand = false;
            prevScore = score;
        }
        return isTheHand;
    }
    public boolean isStraightFlush(List<Card> hand) {
        boolean isTheHand = true;
        int prevScore = 0;
        String prevSuit = "";
        int score = 0;
        String suit = "";
        for (int i=0;i<5;i++) {
            Card card = hand.get(i);
            score = card.getScore();
            suit = card.getSuit();
            if (((score != prevScore+1) && (i != 0)) || ((!suit.equalsIgnoreCase(prevSuit)) && (i != 0))) isTheHand = false;
            prevScore = score;
            prevSuit = suit;
        }
        return isTheHand;
    }

    public boolean isFourOfAKind(List<Card> hand) {
        boolean isTheHand = true;
        int score = hand.get(0).getScore();
        for (int i = 0; i < 4; i++) { if (hand.get(i).getScore() != score) { isTheHand = false; break; } }
        return isTheHand;
    }

    //public boolean isOnePair(List<Card> hand) {

    //}

    // 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
}
