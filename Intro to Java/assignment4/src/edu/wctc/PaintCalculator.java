package edu.wctc;

import java.util.ArrayList;
import java.util.List;

public class PaintCalculator {
    private final List<Room> roomList = new ArrayList<Room>();

    public void addRoom(double length, double height, double width) { roomList.add(new Room(length, height, width)); }

    public String toString() {
        String output = "";
        if (roomList.size() == 0) { output = "[empty]"; }
        else {
            for (Room room : roomList) {
                if (output.equals("")) { output += room.toString(); }
                else { output += String.format("\n%s", room.toString()); }
            }
        }

        return output;
    }
}
