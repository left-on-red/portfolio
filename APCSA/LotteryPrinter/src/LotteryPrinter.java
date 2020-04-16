import java.util.Random;
import java.util.Scanner;

public class LotteryPrinter {
	public static void main(String[] args) {
		Random randomNumber = new Random();
		int lotto[] = {
				randomNumber.nextInt(48) + 1,
				randomNumber.nextInt(48) + 1,
				randomNumber.nextInt(48) + 1,
				randomNumber.nextInt(48) + 1,
				randomNumber.nextInt(48) + 1,
				randomNumber.nextInt(48) + 1
			};
		
		String lottoStr = "";
		
		for (int i = 0; i < lotto.length; i++) {
			lottoStr += lotto[i];
			if (i + 1 != lotto.length) {
				lottoStr += "-";
			}
		}
		
		Scanner reader = new Scanner(System.in);
		System.out.println("enter some lottery numbers using numbers 1-49 (xx-xx-xx-xx-xx-xx): ");
		String inputStr = reader.next();
		
		String inputStrArr[] = inputStr.split("-");
		int inputArr[];
		inputArr = new int[6];
		boolean error = false;
		
		if (inputStrArr.length == 6) {
			for (int i = 0; i < inputStrArr.length; i++) {
				inputArr[i] = Integer.parseInt(inputStrArr[i]);
				if (inputArr[i] > 49 || inputArr[i] < 1) {
					error = true;
				}
			}
			
			if (!error) {
				boolean passed = true;
				for (int i = 0; i < inputArr.length; i++) {
					if (inputArr[i] != lotto[i]) {
						passed = false;
					}
				}
				
				if (passed) {
					System.out.println("you won!");
				}
				
				else {
					System.out.println("you lost! the winning numbers were: " + lottoStr);
				}
			}
			
			else {
				System.out.println("please specify numbers 1-49");
			}
		}
		
		else {
			System.out.println("there must be 6 numbers");
		}
	}

}
