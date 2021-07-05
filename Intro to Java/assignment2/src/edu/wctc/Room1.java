package edu.wctc;

public class Room1 extends Room implements Interactable {
    public boolean brickFlag = false;

    public Room1() {
        super("room 1");
    }

    @Override
    public String interact(Player player) {
        if (brickFlag) { return "You already opened up the way forward."; }

        else {
            brickFlag = true;
            return "You felt around the brick wall and found a brick that you could push. That seemed to open a path forward...";
        }
    }

    @Override
    public String getDescription() {
        return "You're in a narrow corridor with no apparent way out. The stone bricks around you seem loose...";
    }
}
