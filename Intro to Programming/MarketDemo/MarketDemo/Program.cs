using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MarketDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            Fruit apple = new Fruit("apple", 5.00);
            Fruit orange = new Fruit("orange", 2.25);
            Fruit watermelon = new Fruit("watermelon", 7.30);
            Fruit pineapple = new Fruit("pineapple", 8.90);
            Fruit breadfruit = new Fruit("breadfruit", 10.00);

            Fruit[] arr = { apple, orange, watermelon, pineapple, breadfruit };
            for (int i = 0; i < arr.Length; i++)
            {
                Console.WriteLine($"{arr[i].getType()} - ${arr[i].getPrice().ToString("F")}");
            }

            bool running = true;

            List<string> cart = new List<string>();
            double total = 0;

            while (running)
            {
                Console.Write("enter the name of the fruit that you want to buy > ");
                string input = Console.ReadLine();
                bool found = false;
                for (int i = 0; i < arr.Length; i++)
                {
                    if (arr[i].getType() == input)
                    {
                        found = true;
                    }
                }
                
                if (found == true)
                {
                    cart.Add(input);
                    for (int i = 0; i < arr.Length; i++)
                    {
                        if (arr[i].getType() == input)
                        {
                            total += arr[i].getPrice();
                        }
                    }

                    Console.Write("do you want to add another fruit? (Y/N) > ");
                    if (Console.ReadLine().ToLower() == "n")
                    {
                        running = false;
                    }
                }

                else
                {
                    Console.WriteLine("that fruit doesn't exist");
                }
            }

            Console.WriteLine("your cart:");
            foreach (string fruit in cart)
            {
                Console.WriteLine(fruit);
            }

            Console.WriteLine($"your total is: ${total}");

            Console.ReadKey();
        }
    }
}
