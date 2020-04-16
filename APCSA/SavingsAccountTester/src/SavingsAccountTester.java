// SavingsAccountTester.java

public class SavingsAccountTester {
	public static void main(String[] args) {
		SavingsAccount account = new SavingsAccount(1000, 10);
		account.addInterest();
		
		double balance = account.getBalance();
		
		System.out.println("expected: $1100.0");
		System.out.println("actual: $" + balance);
	}

}
