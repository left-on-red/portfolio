using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week4Assignment
{
    class Program
    {
        static void Main(string[] args)
        {
            // question 1
            Console.Write("are you lactose intolerant? (Y/N) > ");
            Boolean isLactose = Console.ReadLine().ToLower() == "y";
            if (!isLactose) {
                // question 2
                Console.Write("are you socially awkward? (Y/N) > ");
                Boolean isAwkward = Console.ReadLine().ToLower() != "n";
                if (!isAwkward) {
                    // question 3
                    Console.Write("do you have a high level of social awareness? (Y/N) > ");
                    Boolean isAware = Console.ReadLine().ToLower() == "y";
                    if (!isAware) {
                        // question 4
                        Console.Write("would you like to be called Rocket Man instead of Froot Loops? (Y/N) > ");
                        Boolean isRocket = Console.ReadLine().ToLower() == "y";
                        if (!isRocket) {
                            // question 5
                            Console.Write("are you afraid to talk to the opposite gender? (Y/N) > ");
                            Boolean genderAfraid = Console.ReadLine().ToLower() == "y";
                            if (!genderAfraid) { Console.Write("you are Dr. Rajesh Ramayan Koothrappali"); }
                            // catchall
                            else { Console.WriteLine("you are not any of the characters on \"The Big Bang Theory\""); }
                        }

                        else { Console.Write("you are Howard Wolowitz"); }
                    }

                    else { Console.Write("you are Penny"); }
                }

                else { Console.WriteLine("you are Dr. Sheldon Cooper"); }
            }

            else { Console.WriteLine("you are Dr. Leonard Hofstadter"); }

            Console.Write("press any key to continue:");
            Console.ReadKey();
        }
    }
}
