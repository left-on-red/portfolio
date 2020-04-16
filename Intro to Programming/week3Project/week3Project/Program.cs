using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace week3Project
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("please enter your name: ");
            String name = Console.ReadLine();
            Console.WriteLine($"Hello, {name}. Welcome to the tip calculator");
            Console.Write("please enter the restaurant: ");
            String restaurant = Console.ReadLine();
            bool isValidTotal = false;
            double billTotal = 0;

            // validates that the value that was entered is a valid number value
            // loops through it until the input is a number
            while (!isValidTotal) {
                Console.Write("please enter the bill total: ");
                String totalStr = Console.ReadLine();
                isValidTotal = double.TryParse(totalStr, out billTotal);
                if (!isValidTotal) {
                    Console.WriteLine("you entered an incorrect value");
                }
            }

            bool isValidTip = false;
            double tipPercent = 0;

            // same procedure that was used to validate the billTotal number
            while (!isValidTip) {
                Console.Write("please enter the tip percentage: ");
                String tipStr = Console.ReadLine();
                isValidTip = double.TryParse(tipStr, out tipPercent);
                if (!isValidTip) {
                    Console.WriteLine("you entered an incorrect value");
                }
            }

            // converts a percentage such as 65 into a math readable number like 1.65
            double calcPercent = (tipPercent / 100) + 1;
            double tipDiff = (billTotal * calcPercent) - billTotal;
            Console.WriteLine($"a {tipPercent}% tip of ${billTotal} is ${tipDiff}");

            double masterTotal = billTotal * calcPercent;
            Console.WriteLine($"the grand total is ${masterTotal}");
            Console.WriteLine($"thank you for dining at {restaurant}, {name}!");
            Console.ReadKey();
        }
    }
}
