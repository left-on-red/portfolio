public class BankAccountTester {
	public static void main(String[] args) {
		BankAccount momsSavings = new BankAccount(1000);
	    momsSavings.addInterest(10);
	    
	    double balance = momsSavings.getBalance();
	    
	    System.out.println("expected: $1100.0");
	    System.out.println("actual: $" + balance);
		
	    /*
		BankAccount account = new BankAccount(1000);
		account.withdraw(500);
		account.withdraw(400);
		
		double balance = account.getBalance();
		
		System.out.println("expected: $100.0");
		System.out.println("actual: $" + balance);
		*/
	}
}
