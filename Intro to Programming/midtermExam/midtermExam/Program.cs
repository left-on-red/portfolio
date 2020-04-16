using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace midtermExam
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("please enter your name > ");
            string name = Console.ReadLine();

            Console.Write("do you want to register for a class(Y/N) > ");
            bool isRegistering = Console.ReadLine().ToLower() == "y";
            while (isRegistering) {
                Console.Write("enter the name of the class > ");
                string className = Console.ReadLine();
                Console.Write("enter the course number for the class > ");
                string courseNumber = Console.ReadLine();
                Console.WriteLine($"you have successfully registered for {className} ({courseNumber})");
                Console.Write("do you want to register for another class(Y/N) > ");
                isRegistering = Console.ReadLine().ToLower() == "y";
            }

            Console.WriteLine($"thank you {name} for using this program");
            Console.Write("press any key to exit...");
            Console.ReadKey();
        }
    }
}
