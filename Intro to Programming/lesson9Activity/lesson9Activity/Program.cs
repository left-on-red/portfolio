using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lesson9Activity
{
    class Program
    {
        static void Main(string[] args)
        {
            bool running = true;
            while (running) {
                if (!File.Exists("text.txt")) { File.Create("text.txt"); }
                string[] lines = File.ReadAllLines("text.txt");
                int lineCount = lines.Length;

                Console.WriteLine($"line count is: {lineCount}");
                string toOut = "";

                for (int i = 0; i < lines.Length; i++) { toOut += $"{String.Format("{0:00}", i + 1)} : {lines[i]}\n"; }
                Console.Write($"{toOut}\n");

                Console.Write("do you want to continue? (Y/N) > ");
                if (Console.ReadLine().ToLower() != "y") { running = false; }
            }

            Console.Write("enter a key to continue...");
            Console.ReadKey();
        }
    }
}