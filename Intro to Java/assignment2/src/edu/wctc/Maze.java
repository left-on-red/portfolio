package edu.wctc;

public class Maze {
    private Room currentRoom;
    private Player player;
    private boolean isFinished = false;

    public Maze(Player player) {
        this.player = player;

        Room r1 = new Room1();
        Room r2 = new Room2();
        Room r3 = new Room3();

        r1.setRoom('n', r2);
        r2.setRoom('e', r3);

        currentRoom = r1;
    }

    public String exitCurrentRoom() {
        if (currentRoom instanceof Exit) { isFinished = true; return ((Exit) currentRoom).exit(player); }
        else { return "this room isn't an exit"; }
    }

    public String interactWithCurrentRoom() {
        if (currentRoom instanceof Interactable) { return ((Interactable) currentRoom).interact(player); }
        else { return "this room isn't interactable"; }
    }

    public String lootCurrentRoom() {
        if (currentRoom instanceof Lootable) { return ((Lootable) currentRoom).loot(player); }
        else { return "this room isn't lootable"; }
    }

    public boolean move(char direction) {
        if (currentRoom.isValidDirection(direction)) {
            if (currentRoom.getName().equals("room 1")) {
                if (((Room1)currentRoom).brickFlag) {
                    currentRoom = currentRoom.getAdjoiningRoom(direction);
                    return true;
                }

                else { return false; }
            }

            else if (currentRoom.getName().equals("room 2")) {
                if (((Room2)currentRoom).keyFlag) {
                    currentRoom = currentRoom.getAdjoiningRoom(direction);
                    return true;
                }

                else { return false; }
            }

            else {
                currentRoom = currentRoom.getAdjoiningRoom(direction);
                return true;
            }
        }

        else { return false; }
    }

    public int getPlayerScore() { return player.getScore(); }
    public String getPlayerInventory() { return player.getInventory(); }

    public String getCurrentRoomDescription() { return currentRoom.getDescription(); }
    public String getCurrentRoomExits() { return currentRoom.getExits(); }

    public boolean isFinished() { return isFinished; }
}
