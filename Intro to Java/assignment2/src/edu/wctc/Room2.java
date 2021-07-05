package edu.wctc;

public class Room2 extends Room implements Lootable {
    public boolean keyFlag = false;

    public Room2() {
        super("room 2");
    }

    @Override
    public String loot(Player player) {
        if (keyFlag) { return "You already got the key."; }
        else {
            keyFlag = true;
            return "You opened the chest and found a key!";
        }
    }

    @Override
    public String getDescription() {
        return "You're in a small room with a door that's seemingly locked. There's a small chest in the center of the room that looks like it holds some sort of significance.";
    }
}
