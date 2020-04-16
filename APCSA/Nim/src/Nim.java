import java.util.Random;
import java.util.Scanner;

public class Nim {

	public static void main(String[] args) {
		Random random = new Random();
		
		boolean playersTurn = false;
		int startRand = random.nextInt(2);
		if (startRand == 0) { playersTurn = true; }
		
		boolean stupidMode = false;
		int stupidRand = random.nextInt(2);
		if (stupidRand == 0) { stupidMode = true; }

		int pile = random.nextInt((100 - 10) + 1) + 10;
		
		Scanner scan = new Scanner(System.in);
		playersTurn = true;
		
		while (pile > 0) {
			System.out.println("there are " + pile + " marbles left!");
			if (playersTurn) {
				System.out.print("how many marbles do you want to take? (1-" + (int) Math.floor(pile/2) + "): ");
				int toTake = scan.nextInt();
				pile -= toTake;
			}
			
			else {
				if (stupidMode) {
					int toTake = random.nextInt(((int) Math.floor(pile/2) - 1) + 1) + 1;
					pile -= toTake;
					System.out.println("the computer took " + toTake + " marbles");
				}
				
				else {
					int canTake = (int) Math.floor(pile/2);
					int toTake = 0;
					for (int i = 2; i < 6; i++) {
						if (canTake >= (int) Math.pow(2, i) - 1) {
							toTake = (int) (Math.pow(2, i) - 1);
						}
					}
					
					if (toTake == 0) {
						toTake = random.nextInt(((int) Math.floor(pile/2) - 1) + 1) + 1;
					}
					
					pile -= toTake;
					System.out.println("the computer took " + toTake + " marbles");
				}
			}
			
			playersTurn = !playersTurn;
		}
		
		if (playersTurn) {
			System.out.println("you win!");
		}
		
		else {
			System.out.println("you lose!");
		}
		
		scan.close();
	}
}
