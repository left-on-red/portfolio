import java.util.Scanner;

public class TicketSeller {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tickets = 100;
		int buyerTotal = 0;
		
		while (tickets > 0) {
			System.out.print("how many tickets would you like to purchase? ");
			int toBuy = scan.nextInt();
			if (toBuy > 4) {
				System.out.println("you cannot buy more than 4 tickets!");
			}
			
			else {
				if (toBuy > tickets) {
					System.out.println("there aren\'t that many tickets left!");
				}
				
				else {
					buyerTotal++;
					tickets -= toBuy;
					if (tickets == 1) {
						System.out.println("there is 1 more ticket left!");
					}
				
					else if (tickets == 0) {
						System.out.println("there are no more tickets left!");
					}
				
					else {
						System.out.println("there are " + tickets + " more tickets left!");
					}
				}
			}
		}
		
		System.out.print(buyerTotal + " buyers have bought tickets!");
		
		scan.close();
	}

}
