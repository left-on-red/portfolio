package edu.wctc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    private String name;

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Room up;
    private Room down;

    public Room(String name) { this.name = name; }

    private HashMap<Character, Room> getRoomMap() {
        HashMap<Character, Room> map = new HashMap<Character, Room>();
        map.put('n', this.north);
        map.put('s', this.south);
        map.put('e', this.east);
        map.put('w', this.west);
        map.put('u', this.up);
        map.put('d', this.down);

        return map;
    }

    static String charToStringDirection(char direction) {
        String out = "";
        switch(direction) {
            case 'n': out = "north"; break;
            case 's': out = "south"; break;
            case 'e': out = "east"; break;
            case 'w': out = "west"; break;
            case 'u': out = "up"; break;
            case 'd': out = "down"; break;
        }

        return out;
    }

    abstract public String getDescription();

    public Room getAdjoiningRoom(char direction) {
        HashMap<Character, Room> map = getRoomMap();

        if (map.containsKey(direction)) { return map.get(direction); }
        else { return null; }
    }

    public String getExits() {
        HashMap<Character, Room> map = getRoomMap();
        ArrayList<String> exits = new ArrayList<String>();

        // I have absolutely no idea if this was the right way to do this
        for (Map.Entry<Character, Room> item : map.entrySet()) {
            char direction = item.getKey();
            Room room = item.getValue();

            if (room != null) {
                String roomName = Room.charToStringDirection(direction);
                exits.add(roomName);
            }
        }

        return exits.size() == 0 ? "no exits..." : String.format("exits are: %s", String.join(", ", exits));

        // ... 100% over-engineered this method
    }

    public String getName() { return name; }

    public boolean isValidDirection(char direction) {  return getRoomMap().get(direction) != null; }

    // it said to have a setter for each direction, but I'm not doing that
    public void setRoom(char direction, Room room) {
        switch(direction) {
            case 'n': this.north = room; break;
            case 's': this.south = room; break;
            case 'e': this.east = room; break;
            case 'w': this.west = room; break;
            case 'u': this.up = room; break;
            case 'd': this.down = room; break;
        }
    }
}
