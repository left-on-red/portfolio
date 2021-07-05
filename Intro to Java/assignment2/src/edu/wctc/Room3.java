package edu.wctc;

public class Room3 extends Room implements Exit {
    public Room3() {
        super("room 3");
    }

    @Override
    public String exit(Player player) {
        return "You opened the door, and was greeted by the open free air of the wilderness.";
    }

    @Override
    public String getDescription() {
        return "You're in a room with a with a cracked open door. You hear birds chirping and see faint rays of sunlight.";
    }
}
