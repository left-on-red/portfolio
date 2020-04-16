using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gpaCalc
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> grades = new List<int>();
            var running = true;
            while (running) {
                Console.Write("enter a grade (A-F) > ");
                String input = Console.ReadLine();
                bool caught = false;
                switch (input.ToUpper()) {
                    case "A": grades.Add(4); caught = true; break;
                    case "B": grades.Add(3); caught = true; break;
                    case "C": grades.Add(2); caught = true;  break;
                    case "D": grades.Add(1); caught = true;  break;
                    case "F": grades.Add(0); caught = true;  break;
                }

                if (caught) {
                    Console.Write("do you want to add another grade?(Y/N) > ");
                    if (Console.ReadLine().ToLower() == "n") { running = false; }
                }

                else { Console.WriteLine("please enter a valid grade"); }
            }

            double total = 0;
            for (int i = 0; i < grades.Count; i++) { total += grades.ElementAt(i); }
            double average = total / grades.Count;

            Console.Write($"{grades.Count} grades were entered with a Grade Point Average of: {average}");
            Console.ReadKey();
        }
    }
}
