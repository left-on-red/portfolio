using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week11Activity
{
    class Program
    {
        static void Main(string[] args)
        {
            int[,] dimensions = new int[4, 2];
            string[] rooms = { "living room", "bedroom", "kitchen", "bathroom" };
            int total = 0;

            for (int x = 0; x < 4; x++) {
                Console.WriteLine($"enter the dimensions for {rooms[x]}");
                Console.Write("length > ");
                int length = int.Parse(Console.ReadLine());
                Console.Write("width > ");
                int width = int.Parse(Console.ReadLine());

                dimensions[x, 0] = length;
                dimensions[x, 1] = width;
            }

            for (int d = 0; d < dimensions.GetLength(0); d++) {
                Console.WriteLine($"the {rooms[d]} is {dimensions[d, 0] * dimensions[d, 1]} square feet");
                total += dimensions[d, 0] * dimensions[d, 1];
            }

            Console.WriteLine($"the apartment is {total} square feet");
            Console.ReadKey();


        }
    }
}
