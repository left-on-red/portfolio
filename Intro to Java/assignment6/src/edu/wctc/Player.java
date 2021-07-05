package edu.wctc;

public class Player {
    // Static field tracks ID to be assigned to next Player object
    // that is created
    private static int nextPlayerNum = 1;
    private final int playerNum;
    private int countWin;
    private int countLoss;
    private int score;
    private int rollsUsed;

    public Player() {
        playerNum = nextPlayerNum++;
    }

    public void addLoss() {
        this.countLoss++;
    }

    public void addWin() {
        this.countWin++;
    }

    public int getPlayerNumber() {
        return playerNum;
    }

    public int getRollsUsed() {
        return this.rollsUsed;
    }

    public int getScore() {
        return score;
    }

    public int getWins() {
        return countWin;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetPlayer() {
        score = 0;
        rollsUsed = 0;
    }

    public void roll() {
        rollsUsed++;
    }

    public String toString() {
        return String.format("Player %d: %d (Won: %d, Lost: %d)",
                playerNum, score, countWin, countLoss);
    }
}
