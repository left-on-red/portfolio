using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinalProject
{
    class Program
    {
        static string header = "";
        // configures a cli arrow-key-controlled interface in which it would ask you a question using an array of strings and would return the index of the selected answer (1-max because index 0 is the question text)
        static int prompt(string[] arr)
        {
            bool running = true;
            int selected = 0;
            while (running)
            {
                Console.Clear();
                Console.ForegroundColor = ConsoleColor.Cyan;
                Console.WriteLine(header);

                for (int i = 0; i < arr.Length; i++)
                {
                    if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {arr[i]}"); }
                    else { Console.ForegroundColor = ConsoleColor.White; Console.WriteLine(arr[i]); }
                }

                // arrow-key event handling
                ConsoleKeyInfo cki = Console.ReadKey();
                switch (cki.Key.ToString())
                {
                    case "UpArrow": if (selected > 0) { selected -= 1; } break;
                    case "DownArrow": if (selected < arr.Length - 1) { selected += 1; } break;
                    case "Enter": running = false; break;
                }
            }

            Console.ForegroundColor = ConsoleColor.White;
            Console.Clear();
            return selected;
        }
        static void Main(string[] args)
        {
            // deserializes json file into data class if it exists. else it creates an empty data class
            Data data;
            if (File.Exists("data.json"))
            {
                string json = File.ReadAllText("data.json");
                data = JsonConvert.DeserializeObject<Data>(json);
            }

            else { data = new Data(); }

            string[] credOpts = { "login", "create a user account", "delete a user account", "exit" };
            bool runningCred = true;
            while (runningCred)
            {
                int credSelected = prompt(credOpts);
                if (credSelected == 0)
                {
                    // login
                    Console.Write("username > ");
                    string username = Console.ReadLine();
                    Console.Write("password > ");
                    string password = Console.ReadLine();
                    string actual;

                    bool isAuth = false;
                    if (data.credentials.TryGetValue(username, out actual)) { if (password == actual) { isAuth = true; } }

                    if (isAuth)
                    {
                        bool accountSelectRunning = true;
                        while (accountSelectRunning)
                        {
                            Customer user;
                            data.customers.TryGetValue(username, out user);
                            string[] accountOpts = new string[user.accounts.Count + 3];
                            for (int i = 0; i < user.accounts.Count; i++) { accountOpts[i] = $"{user.accounts[i].NAME} account"; }
                            accountOpts[user.accounts.Count] = "open an account";
                            accountOpts[user.accounts.Count + 1] = "close an account";
                            accountOpts[user.accounts.Count + 2] = "exit";

                            header = "";
                            int selectedBankOpt = prompt(accountOpts);
                            if (selectedBankOpt < user.accounts.Count)
                            {
                                // do stuff with individual bank accounts
                                Account bank = user.accounts[selectedBankOpt];

                                string[] opts = { "deposit", "withdraw", "transfer", "exit" };

                                bool isOperationMenu = true;
                                while (isOperationMenu)
                                {
                                    header = $"{string.Format("{0:C0}", bank.BALANCE)} in bank account ${bank.NAME} (#{bank.NUMBER})";
                                    int opt = prompt(opts);
                                    if (opt == 0)
                                    {
                                        // deposit
                                        Console.Write("enter amount of money that you wish to deposit > ");
                                        double toPut;
                                        string input = Console.ReadLine();
                                        input = input.TrimStart(new char[] { '$' });

                                        bool success = double.TryParse(input, out toPut);
                                        if (success)
                                        {
                                            bank.Deposit(toPut);
                                        }

                                        else
                                        {
                                            Console.Write("invalid input...");
                                            Console.ReadKey();
                                        }
                                    }

                                    else if (opt == 1)
                                    {
                                        // withdraw
                                        Console.Write("enter amount of money that you wish to withdraw > ");
                                        double toTake;
                                        string input = Console.ReadLine();
                                        input = input.TrimStart(new char[] { '$' });

                                        bool success = double.TryParse(input, out toTake);
                                        if (success)
                                        {
                                            if (bank.BALANCE < toTake)
                                            {
                                                Console.Write("you don't have enough money...");
                                                Console.ReadKey();
                                            }

                                            else { bank.Withdraw(toTake); }
                                        }

                                        else
                                        {
                                            Console.Write("invalid input...");
                                            Console.ReadKey();
                                        }
                                    }

                                    else if (opt == 2)
                                    {
                                        // transfer
                                        header = "select which account you wish to transfer funds into";
                                        List<string> transferOpts = new List<string>();
                                        //string[] transferOpts = new string[user.accounts.Count - 1];
                                        List<int> indexes = new List<int>();
                                        //for (int i = 0; i < user.accounts.Count; i++) { transferOpts.Add(user.accounts[i]); }

                                        // TODO: THIS
                                        for (int i = 0; i < user.accounts.Count; i++)
                                        {
                                            if (!user.accounts[i].NUMBER.Equals(bank.NUMBER))
                                            {
                                                transferOpts.Add($"{user.accounts[i].NAME} account");
                                                indexes.Add(i);
                                            }
                                        }
                                        
                                        transferOpts.Add("cancel");
                                        int transferInput = prompt(transferOpts.ToArray());
                                        if (transferInput < user.accounts.Count)
                                        {
                                            Console.Write("how much do you wish to transfer > ");
                                            double toTransfer;
                                            string input = Console.ReadLine();
                                            input = input.TrimStart(new char[] { '$' });

                                            bool success = double.TryParse(input, out toTransfer);
                                            if (success)
                                            {
                                                if (toTransfer > bank.BALANCE)
                                                {
                                                    Console.Write("you don't have enough in this account to transfer that much...");
                                                    Console.ReadKey();
                                                }

                                                else
                                                {
                                                    bank.Transfer(user.accounts[indexes[transferInput]], toTransfer);
                                                    Console.Write($"successfully transferred ${string.Format("{0:C0}", toTransfer)} into account {user.accounts[indexes[transferInput]].NAME} (#{user.accounts[indexes[transferInput]].NUMBER})");
                                                }
                                            }
                                        }
                                    }

                                    else if (opt == 3)
                                    {
                                        isOperationMenu = false;
                                    }
                                }

                            }

                            else if (selectedBankOpt == user.accounts.Count)
                            {
                                // open an account
                                Console.Write("enter the name of your new account > ");
                                string name = Console.ReadLine();
                                string number;

                                int index = 0;
                                foreach (KeyValuePair<string, string> s in data.credentials)
                                {
                                    index += 1;
                                    if (s.Key == username) { break; }
                                }

                                number = $"{index}";
                                number += $"{user.accounts.Count}";

                                user.accounts.Add(new Account(name, int.Parse(number), 0));
                                Console.Write("your new account has successfully been opened...");
                                Console.ReadKey();
                            }

                            else if (selectedBankOpt == user.accounts.Count + 1)
                            {
                                // close an account
                                header = "select which account you wish to close";
                                string[] deleteAccountOpts = new string[user.accounts.Count + 1];
                                for (int i = 0; i < user.accounts.Count; i++) { deleteAccountOpts[i] = $"{user.accounts[i].NAME} account"; }
                                deleteAccountOpts[user.accounts.Count] = "cancel";
                                int deleteOpt = prompt(deleteAccountOpts);
                                if (deleteOpt < user.accounts.Count)
                                {
                                    int confirm = prompt(new string[] { "confirm", "cancel" });
                                    if (confirm == 0)
                                    {
                                        user.accounts.RemoveAt(deleteOpt);
                                        Console.Write("your account has been closed successfully...");
                                        Console.ReadKey();
                                    }
                                }
                            }

                            else if (selectedBankOpt == user.accounts.Count + 2) {
                                // exiting the menu
                                accountSelectRunning = false;
                            }
                        }
                    }

                    else
                    {
                        Console.Write("incorrect username or password...");
                        Console.ReadKey();
                    }
                }

                else if (credSelected == 1)
                {
                    // create an account
                    bool isValidUser = false;
                    while (!isValidUser)
                    {
                        Console.Write("enter the username that you would like to use > ");
                        string username = Console.ReadLine();
                        if (data.credentials.ContainsKey(username)) { Console.WriteLine("that username is already taken... please try again!"); }
                        else
                        {
                            Console.Write("please enter a password > ");
                            string password = Console.ReadLine();
                            data.credentials.Add(username, password);

                            Customer newCustomer = new Customer();
                            data.customers.Add(username, newCustomer);

                            isValidUser = true;
                        }
                    }

                    Console.Write("user account created successfully...");
                    Console.ReadKey();
                }

                else if (credSelected == 2)
                {
                    // delete an account
                    bool deleteLoop = true;
                    while (deleteLoop)
                    {
                        Console.Write("username > ");
                        string username = Console.ReadLine();
                        Console.Write("password > ");
                        string password = Console.ReadLine();

                        if (data.credentials.ContainsKey(username))
                        {
                            string actual;
                            data.credentials.TryGetValue(username, out actual);
                            if (actual == password)
                            {
                                data.credentials.Remove(username);
                                data.customers.Remove(username);
                                deleteLoop = false;
                                Console.WriteLine("successfully removed your user account!");
                                Console.ReadKey();
                            }
                        }

                        Console.WriteLine("incorrect username or password... please try again!");
                        Console.ReadKey();
                    }
                }

                else if (credSelected == 3)
                {
                    runningCred = false;
                    Console.Write("thank you for using banker v1.0...");
                }
            }

            // serializes data back into json file
            string jsonStr = JsonConvert.SerializeObject(data, Formatting.Indented);
            File.WriteAllText("data.json", jsonStr);

            Console.ReadKey();
        }
    }
}
