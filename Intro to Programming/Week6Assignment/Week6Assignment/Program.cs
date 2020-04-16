using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace midterm
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] options = { "add", "subtract", "multiply", "divide", "surprise me!", "quit" };
            string[] operators = { "+", "-", "*", "/" };

            double add(double num1, double num2) { return num1 + num2; }
            double subtract(double num1, double num2) { return num1 - num2; }
            double multiply(double num1, double num2) { return num1 * num2; }
            double divide(double num1, double num2) { return num1 / num2; }

            // configures a cli arrow-key-controlled interface in which it would ask you a question using an array of strings and would return the index of the selected answer (1-max because index 0 is the question text)
            int prompt() {
                bool running = true;
                int selected = 0;
                while (running)
                {
                    Console.Clear();
                    Console.ForegroundColor = ConsoleColor.Cyan;
                    
                    for (int i = 0; i < options.Length; i++)
                    {
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {options[i]}"); }
                        else { Console.ForegroundColor = ConsoleColor.White; Console.WriteLine(options[i]); }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();
                    switch (cki.Key.ToString())
                    {
                        case "UpArrow": if (selected > 0) { selected -= 1; } break;
                        case "DownArrow": if (selected < 5) { selected += 1; } break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }

            double[] getNumbers() {
                double[] toReturn = new double[2];
                Console.Clear();

                double getNumber() {
                    double number = 0;
                    bool validNumber = false;
                    while (!validNumber) {
                        Console.Clear();
                        Console.ForegroundColor = ConsoleColor.Cyan;
                        Console.Write("enter a number > ");
                        double input;
                        bool isValid = double.TryParse(Console.ReadLine(), out input);
                        if (isValid) {
                            validNumber = true;
                            number = input;
                        }
                    }

                    return number;
                }

                toReturn[0] = getNumber();
                toReturn[1] = getNumber();

                return toReturn;
            }

            bool isDead = false;
            while (!isDead) {
                int option = prompt();
                if (option != 5) {
                    Random rand = new Random();
                    if (option == 4) { option = rand.Next(0, 4); }
                    double[] numbers = getNumbers();
                    Console.Clear();
                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.Write($"{numbers[0]} {operators[option]} {numbers[1]} = ");
                    double result = 0;
                    switch (option) {
                        case 0: result = numbers[0] + numbers[1]; break;
                        case 1: result = numbers[0] - numbers[1]; break;
                        case 2: result = numbers[0] * numbers[1]; break;
                        case 3: result = numbers[0] / numbers[1]; break;
                    }

                    Console.WriteLine(result);
                    Console.Write("press a key to continue:");
                    Console.ReadKey();
                }

                else {
                    isDead = true;
                    Console.Clear();
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    Console.WriteLine("thank you for using my calculator!");
                    Console.Write("press any key to continue...");
                }
            }

            Console.ReadKey();
        }
    }
}