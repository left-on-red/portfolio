package edu.wctc.part3.demo.poor;

/**
 * @author Jim Lombardo
 */
public class Engine {

    private int cylinderCount;
    private boolean running;

    // Is using the default constructor always a good idea?
    // What happens if setCylinderCount() is never called?
    public Engine() {
    }

    public int getCylinderCount() {
        return cylinderCount;
    }

    // This allows us to change the number of cylinders in
    // an existing engine. Should that even be possible?
    public void setCylinderCount(int value) {
        cylinderCount = value;
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
