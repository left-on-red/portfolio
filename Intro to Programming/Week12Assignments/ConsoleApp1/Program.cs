using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> itemNames = new List<string>();
            List<double> orderPrices = new List<double>();
            List<string> customs = new List<string>();

            string[] options = {
                "add item",
                "remove item",
                "check order",
                "complete order"
            };

            string[] food = { "pizza", "sandwich", "soft drink", "milkshake" };
            double[] prices = { 2.50, 1.75, 1.75, 3.00 };

            string[] menu = new string[food.Length + 1];
            for (int i = 0; i < food.Length; i++) { menu[i] = $"{food[i]} : {String.Format("{0:0.00}", prices[i])}"; }
            menu[food.Length] = "cancel";

            // configures a cli arrow-key-controlled interface in which it would ask you a question using an array of strings and would return the index of the selected answer (1-max because index 0 is the question text)
            int prompt(string[] arr)
            {
                bool running = true;
                int selected = 0;
                while (running)
                {
                    Console.Clear();
                    Console.ForegroundColor = ConsoleColor.Cyan;

                    for (int i = 0; i < arr.Length; i++)
                    {
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {arr[i]}"); }
                        else { Console.ForegroundColor = ConsoleColor.White; Console.WriteLine(arr[i]); }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();
                    switch (cki.Key.ToString())
                    {
                        case "UpArrow": if (selected > 0) { selected -= 1; } break;
                        case "DownArrow": if (selected < arr.Length - 1) { selected += 1; } break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }

            bool menuRunning = true;

            while (menuRunning) {
                int response = prompt(options);
                if (response == 0) {
                    int selectedFood = prompt(menu);
                    if (selectedFood != menu.Length - 1) {
                        Console.Clear();
                        Console.ForegroundColor = ConsoleColor.White;
                        Console.Write("custom order? > ");
                        string customOrder = Console.ReadLine();
                        itemNames.Add(food[selectedFood]);
                        orderPrices.Add(prices[selectedFood]);
                        customs.Add(customOrder);
                    }
                }

                else if (response == 1) {
                    string[] orderArr = itemNames.ToArray();
                    double[] priceArr = orderPrices.ToArray();
                    string[] displayOrder = new string[orderArr.Length + 1];
                    for (int i = 0; i < orderArr.Length; i++) { displayOrder[i] = $"{orderArr[i]} : {String.Format("{0:0.00}", priceArr[i])}"; }
                    displayOrder[orderArr.Length] = "cancel";

                    int toRemove = prompt(displayOrder);
                    if (toRemove != orderArr.Length) {
                        itemNames.RemoveAt(toRemove);
                        orderPrices.RemoveAt(toRemove);
                        customs.RemoveAt(toRemove);
                    }
                }

                else if (response == 2) {
                    Console.Clear();
                    string[] orderArr = itemNames.ToArray();
                    double[] priceArr = orderPrices.ToArray();
                    string[] customsArr = customs.ToArray();
                    for (int i = 0; i < orderArr.Length; i++) { Console.WriteLine($"{orderArr[i]} : {String.Format("{0:0.00}", priceArr[i])} : {customsArr[i]}"); }
                    Console.Write("enter any key to continue...");
                    Console.ReadKey();
                }

                else { menuRunning = false; }
            }

            string[] fooda = itemNames.ToArray();
            double[] pricea = orderPrices.ToArray();
            string[] customa = customs.ToArray();
            double total = 0;

            Console.ForegroundColor = ConsoleColor.White;
            Console.Clear();

            for (int i = 0; i < fooda.Length; i++) {
                Console.WriteLine($"menu item:\t {fooda[i]}");
                Console.WriteLine($"price    :\t ${String.Format("{0:0.00}", pricea[i])}");
                if (customa[i] != "") { Console.WriteLine($"customs  :\t {customa[i]}"); }
                total += pricea[i];
                Console.WriteLine("---------------------------------------------");
            }

            Console.WriteLine($"subtotal:\t${String.Format("{0:0.00}", total)}");
            double tax = total * 0.051;
            Console.WriteLine($"tax     :\t${String.Format("{0:0.00}", tax)}");
            Console.WriteLine($"total   :\t${String.Format("{0:0.00}", tax + total)}");

            Console.ReadKey();
        }
    }
}
