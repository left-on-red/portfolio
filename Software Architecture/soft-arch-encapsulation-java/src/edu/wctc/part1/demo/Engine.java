package edu.wctc.part1.demo;

/**
 * Here's a simple example of property encapsulation -- hiding data and,
 * potentially behavior, by making instance properties private and by choosing
 * getter/setter methods appropriately.
 *
 * Benefits:
 * - no direct access to property values outside of this class
 * (protects against illegal or inappropriate values being assigned or accessed.)
 * - combine with public get and/or set methods for controlled access
 * (setters control values via validation).
 *
 * Note that we can choose to provide getters AND setters for read/write access,
 * or just getters for read-only access. Or, we could make the class immutable by
 * providing a constructor that initializes all properties and eliminating all
 * public setter methods. This class looks immutable, but it's not because the
 * start() method mutates the state of the running property.
 *
 * Also notice that this class has a Single Responsibility: to do what an
 * Engine can do and nothing more.
 */
public class Engine {

    private int cylinderCount;
    private boolean running;

    public Engine(int cylinders) {
        setCylinderCount(cylinders);
    }

    // We are providing read-only access to this property (no setter).
    // This is a requirements issue.
    public int getCylinderCount() {
        return cylinderCount;
    }

    // By making this private you cannot change the cylinder count once
    // the engine is created. This is a requirements issue.
    private void setCylinderCount(int value) {
        if (value < 1 || value > 12) { // magic numbers!
           throw new IllegalArgumentException("Invalid cylinder count");
        }
        cylinderCount = value;
    }

    public void start() {
        if (!running) {
            running = true;
        }
    }

    public void stop() {
        if (running) {
            running = false;
        }
    }

    // This is a getter method for the running property. By convention
    // boolean properties have getter methods that start with "is".
    // Notice there is no setter method - why? Because we want to control
    // how the property is mutated by only using the start() method.
    public boolean isRunning() {
        return running;
    }

}
