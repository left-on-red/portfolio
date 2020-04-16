// Employee.java

/**
   An employee object that is used to manage employee salaries
*/
public class Employee {  
   private String name;
   private double salary;

   /**
      Constructs a bank account with a zero balance
   */
   public Employee() {   
      name = "";
      salary = 0;
   }

   /**
      Constructs an employee object with a name and salary
      @param initialBalance the initial balance
   */
   public Employee(String employeeName, double currentSalary) {   
      name = employeeName;
      salary = currentSalary;
   }

   /**
      Raises the employee's salary.
      @param the percentage of the salary to raise
   */
   public void raiseSalary(double byPercent) {  
      salary += salary * (byPercent / 100);
   }
   
   /**
      Gets the name of the employee.
      @return the name of the employee
   */
   public String getName() {
	   return name;
   }
   
   /**
      Gets the salary of the employee
   	  @return the salary of the employee
   */
   public double getSalary() {
	   return salary;
   }
}