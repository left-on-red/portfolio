import java.util.Scanner;

public class Exercises {
	public static void main(String[] args) {
		System.out.println("enter a list of integers seperated by spaces (1 2 3 4):");
		double total = 0;
		double average;
		Scanner reader = new Scanner(System.in);
		String[] numbersString = reader.nextLine().split(" ");
		reader.close();
		int[] numbers = new int[numbersString.length];
		for (int n = 0; n < numbersString.length; n++) {
			numbers[n] = Integer.parseInt(numbersString[n]);
		}
		
		for (int n = 0; n < numbers.length; n++) {
			total += numbers[n];
		}
		
		average = total / numbers.length;
		
		System.out.println("your average is: " + average);
	}
}

/*
import java.util.Scanner;

public class Exercises {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the distance in meters: ");
		double distance = reader.nextDouble();
		reader.close();
		double miles = (distance * 0.0006);
		double feet = (distance * 3.281);
		double inches = (distance * 39.37);
		System.out.println("miles: " + miles);
		System.out.println("feet: " + feet);
		System.out.println("inches: " + inches);
	}
}
*/