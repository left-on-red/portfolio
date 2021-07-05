package edu.wctc.part3.demo.good;

/**
 * This class is entirely hidden from the Car class.
 * It can be changed without causing harm to other classes.
 *
 * @author Jim Lombardo
 */
public class CarReportService {
    private Car car;

    public CarReportService(Car car) {
        this.car = car;
    }

    public void printEngineType() {
        System.out.println("Engine Type: " + car.getEngineType());
    }

    public void printRunningStatus() {
        System.out.println("Car running status: " + car.isRunning());
    }
}
