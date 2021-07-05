package edu.wctc;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Wall> wallList = new ArrayList<Wall>();

    private double length;
    private double height;
    private double width;

    public Room(double length, double height, double width) {
        wallList.add(new Wall(width, height));
        wallList.add(new Wall(length, height));
        wallList.add(new Wall(width, height));
        wallList.add(new Wall(length, height));

        this.length = length;
        this.height = height;
        this.width = width;
    }

    // I wasn't sure which "area" was needed, but given the context of this program, I assumed that it would just be the area of all 4 walls
    public double getArea() {
        double total = 0;
        for (Wall wall : wallList) { total += wall.getArea(); }
        return total;
    }

    public String toString() { return String.format("%f,%f,%f", this.length, this.height, this.width); }
}
