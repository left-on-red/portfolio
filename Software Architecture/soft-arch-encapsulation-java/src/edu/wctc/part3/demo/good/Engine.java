package edu.wctc.part3.demo.good;

/**
 * This class is also hidden (encapsulated).
 *
 * @author Jim Lombardo
 */
public class Engine {
    // NO magic numbers -- use constants
    private static final int MIN_CYL = 4;
    private static final int MAX_CYL = 12;

    private int cylinderCount;
    private boolean running;

    // Arguments are validated and a sensible default is applied if they are
    // invalid. Use of constants make change easier and less error prone.
    // Furthermore, using a custom constructor guarantees that the
    // number of cylinders for the engine is set.
    public Engine(int numOfCylinders) {
        if (numOfCylinders < MIN_CYL || numOfCylinders > MAX_CYL) {
            // if invalid use default
            cylinderCount = MIN_CYL;
        } else {
            cylinderCount = numOfCylinders;
        }
    }

    public int getCylinderCount() {
        return cylinderCount;
    }

    public String getType() {
        return "V" + cylinderCount;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
