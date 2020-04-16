// Car.java

public class Car {
	private double efficiency;
	private double fuel;
	private double distance;
	
	public Car(double e) {
	 efficiency = e;
	 fuel = 0;
	 distance = 0;
	}
	
	public void drive(double d) {
		double fuelReq = d / efficiency;
		if (fuel < fuelReq) {
			distance += efficiency * fuel;
			fuel = 0;
		}
		
		else {
			fuel -= fuelReq;
			distance += d;
		}
	}
	
	public void addGas(double f) {
		fuel += f;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getGasInTank() {
		return fuel;
	}
}
