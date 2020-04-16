// Salary.java

import java.util.Scanner;

public class Salary {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("What is Johns Hourly Wage?");
		double wage = reader.nextDouble();
		System.out.println("How many hours has John worked in the past week?");
		double hours = reader.nextDouble();
		reader.close();
		
		Paycheck John = new Paycheck("Johnny Appleseed", wage, hours);
		double pay = John.getPay();
		
		System.out.println("John has worked " + hours + " hours and made $" + pay);
	}

}