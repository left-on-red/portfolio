// EmployeeTester.java

public class EmployeeTester {
	public static void main(String[] args) {
		Employee harry = new Employee("Hacker, Harry", 50000);
	    harry.raiseSalary(10);
	    
	    double salary = harry.getSalary();
	    
	    System.out.println("expected: $55000.0/y");
	    System.out.println("actual: $" + salary + "/y");
	}
}