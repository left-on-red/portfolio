package horoscopes;

import java.util.Scanner;

public class horoscopes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your birthday(month and day): ");
		String bday = scan.nextLine();
		scan.close();
		
		int parsed = Integer.parseInt(bday.replaceAll(" ", ""));
		System.out.println(parsed);
		
		// Aries
		if (parsed >= 321 && parsed <= 419) {
			
		}
	}

}
