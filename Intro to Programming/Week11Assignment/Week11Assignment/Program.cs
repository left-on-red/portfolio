using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week11Assignment
{
    class Program
    {
        static void Main(string[] args)
        {

            // static steps table
            int[,] steps = {
                { 4835, 24794, 13827, 10470, 10210, 10310, 14868 },
                { 11384, 16781, 8090, 8565, 10666, 15162, 13828 },
                { 14246, 8416, 19782, 20617, 7700, 21225, 34826 },
                { 22881, 17980, 26924, 18568, 19299, 22164, 21992 }
            };

            // printed headers
            string[] headers = { "sun", "mon", "tue", "wed", "thu", "fri", "sat", "total", "avg." };
            for (int h = 0; h < headers.Length; h++) { Console.Write($"\t{headers[h]}"); }
            Console.Write("\n");
            for (int h = 0; h < headers.Length; h++) { Console.Write("\t-------"); }

            // runs through every row and every column inside each row
            for (int y = 0; y < steps.GetLength(0); y++) {
                int total = 0;
                Console.Write($"\nweek {y+1}:\t");
                for (int x = 0; x < steps.GetLength(1); x++) {
                    total += steps[y, x];
                    Console.Write($"{String.Format("{0:n0}", steps[y, x])}\t");
                }

                double average = total / 7;
                Console.Write($"{String.Format("{0:n0}", total)}\t{String.Format("{0:n0}", average)}");
            }

            Console.Write("\n");

            for (int n = 0; n < 7; n++) { Console.Write("\t-------"); }
            Console.Write("\ntotal:\t");

            int[] colTotals = new int[7];

            // loops through the the array "sideways" to calculate the vertical totals and averages
            for (int x = 0; x < steps.GetLength(1); x++) {
                for (int y = 0; y < steps.GetLength(0); y++) { colTotals[x] += steps[y, x]; }
                Console.Write($"{String.Format("{0:n0}", colTotals[x])}\t");
            }

            Console.Write("\navg.:\t");
            for (int c = 0; c < colTotals.Length; c++) {
                Console.Write($"{String.Format("{0:n0}", colTotals[c] / 4)}\t");
            }



            Console.ReadKey();
        }
    }
}
