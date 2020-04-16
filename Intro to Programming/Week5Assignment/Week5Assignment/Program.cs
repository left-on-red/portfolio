using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week5Assignment
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> types = new List<string>();
            List<double> weights = new List<double>();
            List<double> lengths = new List<double>();

            string getType() {
                Console.Write("enter the type of fish > ");
                string type = Console.ReadLine();
                return type;
            }

            double getWeight() {
                bool isValid = false;
                double weight = 0;
                while (!isValid) {
                    Console.Write("enter the weight of the fish > ");
                    string line = Console.ReadLine();
                    if (double.TryParse(line, out weight)) {
                        isValid = true;
                        if (weight <= 0) { isValid = false; }
                    }

                    if (!isValid) { Console.WriteLine("you entered an invalid number. please try again"); }
                }

                return weight;
            }

            double getLength() {
                bool isValid = false;
                double length = 0;
                while (!isValid) {
                    Console.Write("enter the length of the fish > ");
                    string line = Console.ReadLine();
                    if (double.TryParse(line, out length)) {
                        isValid = true;
                        if (length <= 0) { isValid = false; }
                    }

                    if (!isValid) { Console.WriteLine("you entered an invalid number. please try again"); }
                }

                return length;
            }

            void promptFish() {
                types.Add(getType());
                weights.Add(getWeight());
                lengths.Add(getLength());
            }

            Console.WriteLine("welcome to Fish Tracker™!");
            bool isRunning = true;
            while (isRunning) {
                Console.WriteLine($"fish #{types.Count + 1}");
                promptFish();
                Console.Write($"do you want to add another fish? (y/n) > ");
                if (Console.ReadLine().ToLower() != "y") { isRunning = false; }
            }

            int total = types.Count;
            double weightTotal = 0;
            for (int w = 0; w < weights.Count; w++) { weightTotal += weights.ElementAt(w); }
            double weightAverage = weightTotal / weights.Count;
            double lengthTotal = 0;
            for (int l = 0; l < lengths.Count; l++) { lengthTotal += lengths.ElementAt(l); }
            double lengthAverage = lengthTotal / lengths.Count;

            Console.WriteLine("summary:");
            Console.WriteLine($"you caught {total} fish");
            Console.WriteLine($"the total weight of all fish you caught is {weightTotal} lbs");
            Console.WriteLine($"the total length of all fish you caught is {lengthTotal} inches");
            Console.WriteLine($"the average weight of all fish caught is {weightAverage} lbs");
            Console.WriteLine($"the average length of all fish caught is {lengthAverage} inches");
            Console.WriteLine("press any key to exit...");
            Console.ReadKey();
        }
    }
}
