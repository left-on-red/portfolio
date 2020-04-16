package fibonacci;
import java.util.Scanner;
public class fibonacci {
	public static void main(String[] args) {
		System.out.print("what number in the fibonacci sequence would you like to see? ");
		Scanner scan = new Scanner(System.in);
		int times = scan.nextInt();
		scan.close();
		
		int value1 = 0;
		int value2 = 1;
		int newValue = 0;
		
		for (int t = 0; t < times; t++) {
			newValue = value1 + value2;
			value1 = value2;
			value2 = newValue;
		}
		
		System.out.println(newValue);
	}

}