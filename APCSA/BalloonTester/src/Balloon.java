public class Balloon {
	private double radius;
	public Balloon() {
		radius = 0;
	}
	
	public void inflate(double amount) {
		radius += amount;
	}
	
	public double getVolume() {
		double volume = (4/3) * Math.PI * (radius * radius * radius);
		return volume;
	}
}
