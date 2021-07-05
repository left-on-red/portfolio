package edu.wctc;

import java.util.ArrayList;

public class Player {
    private ArrayList<String> inventory = new ArrayList<String>();
    private int score = 0;

    public Player() {}

    public void addToInventory(String item) { this.inventory.add(item); }

    public void addToScore(int toAdd) { this.score += toAdd; }

    // I don't know if I'm just sadistic, but I really like one-liners like this for some reason
    public String getInventory() { return inventory.size() == 0 ? "you don't have anything in your inventory..." : String.format("inventory: %s", String.join(", ", this.inventory)); }

    public int getScore() { return this.score; }
}
