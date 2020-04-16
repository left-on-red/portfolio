package taxSchedule;
import java.util.Scanner;

public class taxSchedule {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter an amount of money: ");
		double amount = reader.nextDouble();
		reader.close();
		
		double tax = 0;
		double temp = amount;
		
		System.out.println(temp % 50000);
		
		/*if (temp > 50000) {
			if (temp <= 75000) {
				tax += (temp * 0.02);
			}
			
			else {
				temp
			}
		}*/
		
		amount -= tax;
		
		System.out.println("amount: " + amount);
	}

}