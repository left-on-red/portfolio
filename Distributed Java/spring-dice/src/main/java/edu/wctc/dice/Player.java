package edu.wctc.dice;

public class Player {
    private String name;
    private int bet;
    private int cash;

    public Player(String name) {
        this.name = name;
        this.bet = 0;
        this.cash = 10;
    }

    public int getCash() {
        return cash;
    }

    public String getName() {
        return name;
    }

    public boolean isBankrupt() {
        return cash <= 0;
    }

    public void loses() {
        bet = 0;
    }

    public void setBet(int bet) {
        this.bet = Math.min(cash, bet);
        cash -= bet;
    }

    public String toString() {
        return name + ": $" + cash;
    }

    public void wins() {
        cash += bet * 2;
        bet = 0;
    }
}
