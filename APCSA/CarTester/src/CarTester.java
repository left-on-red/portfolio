// CarTester.java

public class CarTester {
	public static void main(String[] args) {
		Car myHybrid = new Car(100);
	    myHybrid.addGas(20);
	    myHybrid.drive(100);
	    double gasLeft = myHybrid.getGasInTank();
	    double distance = myHybrid.getDistance();
	    System.out.println("gas: " + gasLeft);
	    System.out.println("distance: " + distance);
	}
}