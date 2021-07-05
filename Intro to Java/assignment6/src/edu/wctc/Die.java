package edu.wctc;

import java.util.Random;

public class Die {
    // Static field tracks ID to be assigned to next Die object
    // that is created
    private static char nextDieNum = 'a';
    private final Random randomGen = new Random();
    private final char dieNum;
    private final int sides;

    private boolean isHeld;
    private int faceValue;


    public Die(int sides) {
        this.dieNum = nextDieNum++;
        this.sides = sides;

        rollDie();
    }

    public char getDieNum() {
        return dieNum;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void holdDie() {
        this.isHeld = true;
    }

    public boolean isBeingHeld() {
        return this.isHeld;
    }

    public void resetDie() {
        this.isHeld = false;
    }

    public void rollDie() {
        if (!isHeld) {
            faceValue = randomGen.nextInt(sides) + 1;
        }
    }

    public String toString() {
        return dieNum + ". " + getFaceValue() + (isHeld ? " (H)" : " (-)");
    }
}
