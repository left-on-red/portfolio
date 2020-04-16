using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinalProject
{
    class Account
    {
        private string name;
        private int number;
        private double balance;

        public string NAME { get { return this.name; } }
        public int NUMBER { get { return this.number; } }
        public double BALANCE { get { return this.balance; } }

        public Account(string name, int number, double balance)
        {
            this.name = name;
            this.number = number;
            this.balance = balance;
        }

        public void Withdraw(double toTake)
        {
            if (toTake > this.balance) { throw new Exception("not enough funds!"); }
            else { this.balance -= toTake; }
        }

        public void Deposit(double toPut) { this.balance += toPut; }

        public void Transfer(Account account, double money)
        {
            this.Withdraw(money);
            account.Deposit(money);
        }

        public override string ToString()
        {
            return 
                "--------------------" +
                $"account name: {this.name}" +
                $"account number: {this.number}" +
                $"account balance: ${this.balance}" +
                $"--------------------";
        }
    }
}
