using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LabA
{
    class Program
    {
        static void Main(string[] args)
        {
            double total = 0;
            int count = 0;
            int lowest = 1000000;
            int highest = -1;

            bool running = true;
            while (running) {
                Console.Write("Number of Steps(-1): ");
                string inp = Console.ReadLine();

                bool isNum = int.TryParse(inp, out int n);

                if (!isNum || n == -1) {
                    running = false;
                }

                else {
                    count += 1;
                    total += n;
                    if (n > highest) { highest = n; }
                    if (n < lowest) { lowest = n; }
                }
            }

            Console.WriteLine($"Total steps {total} number of days {count} average steps {total / count}.");
            Console.Write($"The most steps in one day is {highest} and the lowest is {lowest}.");
            Console.ReadKey();
        }
    }
}
