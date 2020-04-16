public class Paycheck {
	private String name;
	private double wage;
	private double hours;
	
	public Paycheck(String n, double w, double h) {
		name = n;
		wage = w;
		hours = h;
	}
	
	public double getPay() {
		if (hours > 40) {
			double temp = hours - 40;
			return (temp * wage * 1.5) + (wage * hours);
		}
		
		else {
			return wage * hours;
		}
	}
}
