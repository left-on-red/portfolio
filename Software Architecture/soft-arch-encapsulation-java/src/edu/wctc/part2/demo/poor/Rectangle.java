package edu.wctc.part2.demo.poor;

public class Rectangle {
    private double length, width;

    /*
    There are so many things wrongs with this method.
    It's violating command/query separation.
    It's both a setter (command) and a getter (query)!
     */
    public double getArea(double length, double width) {
        this.length = length;
        this.width = width;
        return length * width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length < 0) {
            throw new IllegalArgumentException(" length cannot be negative");
        }
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException(" width cannot be negative");
        }
        this.width = width;
    }
}
