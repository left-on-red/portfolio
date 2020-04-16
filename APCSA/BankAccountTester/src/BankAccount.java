/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount {  
   private double balance;

   /**
      Constructs a bank account with a zero balance
   */
   public BankAccount() {   
      balance = 0;
   }

   /**
      Constructs a bank account with a given balance
      @param initialBalance the initial balance
   */
   public BankAccount(double initialBalance) {   
      balance = initialBalance;
   }

   /**
      Deposits money into the bank account.
      @param amount the amount to deposit
   */
   public void deposit(double amount) {  
      double newBalance = balance + amount;
      balance = newBalance;
   }

   /**
      Withdraws money from the bank account.
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
   public void addInterest(double rate) {
	   balance += balance * (rate / 100);
   }

   /**
      Gets the current balance of the bank account.
      @return the current balance
   */
   public double getBalance() {   
      return balance;
   }
}