package edu.wctc.part3.demo.good;

/**
 * In this good example, notice that the Car talks to the engine so that the
 * Main class does not need to do this. We're hiding the details of how the
 * engine works by encapsulating those details here.
 *
 * @author Jim Lombardo
 */
public class Car {
    // This is good composition -- the engine object is a component of the Car object
    // It's used correctly here. Car delegates to Engine. The Engine itself
    // and its functions are hidden from the Main class (or any other class),
    // resulting in good encapsulation.
    private Engine engine;

    // We can reduce the number of setter method needed if we use custom constructor
    // arguments, which promotes reliability. But don't use more than three or four.
    public Car(int numOfCylinders) {
        engine = new Engine(numOfCylinders);
    }

    public String getEngineType() {
        return engine.getType();
    }

    public boolean isRunning() {
        return engine.isRunning();
    }

    // Car delegates to engine
    public void start() {
        engine.start();
    }

    // Car delegates to engine
    public void turnOff() {
        engine.stop();
    }

}
