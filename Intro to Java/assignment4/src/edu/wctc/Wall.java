package edu.wctc;

public class Wall {
    private double width;
    private double height;

    public Wall(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }

    public double getArea() { return this.width * this.height; }
}
