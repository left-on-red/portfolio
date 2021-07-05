package edu.wctc.part2.demo.good;

public class Rectangle {
    private double length, width;

    /*
    Much better. Now this getter is only a query
    method. It has no side effects.
     */
    public double getArea() {
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
