using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Week14Assignment
{
    class Program
    {
        static void Main(string[] args)
        {
            Car car = new Car("make", "model", "red", 10, 10);
            Console.WriteLine(car);
            car.drive(100);
            Console.WriteLine(car);
            car.refuel();
            Console.WriteLine(car);
            Console.ReadKey();
        }
    }
}
