using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week13Assignment
{
    class Program
    {
        // total, uppercase, lowercase, whitespace, punctuation
        static int[] getMeta(string input)
        {
            int[] toReturn = new int[5];
            toReturn[0] = input.Length;
            toReturn[1] = input.Count(c => char.IsUpper(c));
            toReturn[2] = input.Count(c => char.IsLower(c));
            toReturn[3] = input.Count(c => c == ' ');
            toReturn[4] = input.Count(c => c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':');

            return toReturn;
        }

        static void Main(string[] args)
        {
            Console.Write("enter a sentence > ");
            string input = Console.ReadLine();

            int[] meta = getMeta(input);
            string[] headers = { "total", "uppercase", "lowercase", "whitespace", "punctuation" };
            for (int h = 0; h < headers.Length; h++) { Console.WriteLine($"{headers[h]}: {meta[h]}"); }

            Console.Write("press any key to exit...");
            Console.ReadKey();
        }
    }
}
