// SavingsAccount.java

/**
   A savings account has a balance and an interest value
*/
public class SavingsAccount {  
   private double balance;
   private double interest;

   /**
      Constructs a savings account with a zero balance and zero interest rate
   */
   public SavingsAccount() {   
      balance = 0;
      interest = 0;
   }

   /**
      Constructs a savings account with the given balance and interest rate.
      @param initialBalance the initial balance
   */
   public SavingsAccount(double initialBalance, double initialInterest) {   
      balance = initialBalance;
      interest = initialInterest;
   }

   /**
      Deposits money into the savings account.
      @param amount the amount to deposit
   */
   public void deposit(double amount) {  
      double newBalance = balance + amount;
      balance = newBalance;
   }

   /**
      Withdraws money from the savings account.
      @param amount the amount to withdraw
   */
   public void withdraw(double amount) {   
      double newBalance = balance - amount;
      balance = newBalance;
   }
   
   /**
       Adds interest to the current balance from the provided interest rate.
       @param percentage rate
    */
   public void addInterest() {
	   balance += balance * (interest / 100);
   }

   /**
      Gets the current balance of the savings account.
      @return the current balance
   */
   public double getBalance() {   
      return balance;
   }
}