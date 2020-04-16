using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week10Assignment
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] steps = new int[7];
            for (int i = 0; i < 7; i++) {
                bool running = true;
                while (running)
                {
                    Console.Write($"steps for day {i+1} > ");
                    string response = Console.ReadLine();
                    int num = -1;
                    bool parsed = int.TryParse(response, out num);
                    if (!parsed || num < 0) { Console.WriteLine("invalid steps!"); }
                    else { steps[i] = num; running = false; }
                }
            }

            int total = 0;
            for (int i = 0; i < steps.Length; i++) {
                total += steps[i];
                Console.WriteLine($"day {i+1}:\t{String.Format("{0:n0}", steps[i])} steps");
            }

            Console.WriteLine("-------------------------------");
            Console.WriteLine($"total steps:\t{String.Format("{0:n0}", total)}");
            Console.Write($"average steps:\t{String.Format("{0:n0}", total / 7.0)}");

            Console.ReadKey();
        }
    }
}