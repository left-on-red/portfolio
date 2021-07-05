package edu.wctc.part3.demo.poor;

/**
 * In this poor example the car does not communicate with the Engine. Why not?
 * Isn't that the way it works in real life?
 *
 * @author Jim Lombardo
 */
public class Car {
    // This is composition -- the engine object is a component of the Car object
    // But it's used poorly here. Car does not delegate to Engine and it
    // doesn't hide what the Engine does (encapsulation).
    private Engine engine;

    // Is using the default constructor always a good idea?
    // What happens if the setEngine() method is never called?
    public Car() {

    }

    public Engine getEngine() {
        return engine;
    }

    // The "engine" argument is not validated! If engine == null then
    // calls to engine.isRunning() will produce a NullPointerException.
    // Always validate method arguments!
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
