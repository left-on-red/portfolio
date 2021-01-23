using System;
using System.IO;
using System.Collections.Generic;

namespace StringInterpolation
{
    class Program
    {
        static void Main(string[] args)
        {
            /*
            schema:
            StartDate(/ deliminated),SunHrs|MonHrs|TueHrs|ThurHrs|FriHrs|SatHrs
            */

            string[] options = { "add sleep week", "view sleep weeks", "quit" };
            int prompt(string[] menu) {
                bool running = true;
                int selected = 0;
                while (running) {
                    Console.Clear();
                    
                    for (int i = 0; i < menu.Length; i++) {
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {menu[i]}"); Console.ForegroundColor = ConsoleColor.White; }
                        else { Console.WriteLine(menu[i]); Console.ForegroundColor = ConsoleColor.White; }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();
                    switch (cki.Key.ToString()) {
                        case "UpArrow": if (selected > 0) { selected -= 1; } break;
                        case "DownArrow": if (selected < menu.Length - 1) { selected += 1; } break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }

            void printSleep(int[] sleep, DateTime date) {
                string[] headers = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Tot", "Avg" };
                string[] lines = { "--", "--", "--", "--", "--", "--", "--", "---", "---" };
                Console.WriteLine($"Week of {date.ToString("MMM, dd, yyyy")}");
                Console.WriteLine($" {String.Join(" ", headers)}");
                Console.Write($" {String.Join(" ", lines)}\n ");
                
                int total = 0;
                for (int s = 0; s < sleep.Length; s++) {
                    total += sleep[s];
                    Console.Write($"{sleep[s].ToString().PadLeft(2)} ");
                }

                double avg = total / 7.0;

                Console.WriteLine("{0} {1:0.0}\n", total.ToString().PadLeft(3), avg);
            }

            List<int[]> sleep = new List<int[]>();
            List<DateTime> dates = new List<DateTime>();

            if (File.Exists("sleep.csv")) {
                string file = File.ReadAllText("sleep.csv");
                if (file != "") {
                    string[] weeks = file.Split('\n');
                    for (int w = 0; w < weeks.Length; w++) {
                        string d = weeks[w].Split(",")[0];

                        string d1 = d.Split("/")[0];
                        string d2 = d.Split("/")[1];
                        string d3 = d.Split("/")[2];

                        if (d3.Length == 2) { d3 = $"20{d3}"; }

                        DateTime date = new DateTime(int.Parse(d3), int.Parse(d1), int.Parse(d2));
                        dates.Add(date);

                        string[] times = weeks[w].Split(",")[1].Split("|");
                        sleep.Add(new int[] { int.Parse(times[0]), int.Parse(times[1]), int.Parse(times[2]), int.Parse(times[3]), int.Parse(times[4]), int.Parse(times[5]), int.Parse(times[6]) });
                    }
                }
            }

            bool running = true;
            while (running) {
                int opt = prompt(options);
                
                // add sleep week
                if (opt == 0) {
                    string[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
                    int[] hours = new int[7];
                    DateTime date = new DateTime();

                    bool error = false;

                    try {
                        // FINISH //
                        Console.Write("todays date > ");
                        string d = Console.ReadLine();
                        int d1 = int.Parse(d.Split("/")[0]);
                        int d2 = int.Parse(d.Split("/")[1]);
                        int d3 = int.Parse(d.Split("/")[2]);
                        
                        // I feel like it would be better to do this when it's a string and not an int, but oh well
                        if (d3 < 100) { d3 += 2000; }

                        date = new DateTime(d3, d1, d2);
                    }

                    catch (System.Exception) { error = true; }
                    
                    if (!error) {
                        for (int d = 0; d < days.Length; d++) {
                            Console.Write($"how many hours of sleep did you get on {days[d]} > ");
                        
                            if (!(int.TryParse(Console.ReadLine(), out int result))) {
                                error = true;
                                break;
                            }

                            hours[d] = result;
                        }
                    }

                    if (error) {
                        Console.WriteLine("there was a problem with the data you provided");
                        Console.Write("press any key to continue...");
                        Console.ReadKey();
                    }

                    else {
                        sleep.Add(hours);
                        dates.Add(date);
                    }
                }


                // view sleep weeks
                else if (opt == 1) {
                    bool viewing = true;
                    while (viewing) {
                        string[] viewOpts = new string[dates.Count + 2];
                        for (int d = 0; d < dates.Count; d++) { viewOpts[d] = dates[d].ToString("MM/dd/yyyy"); }
                        viewOpts[dates.Count] = "view all";
                        viewOpts[dates.Count + 1] = "exit";

                        int toView = prompt(viewOpts);
                        if (toView == dates.Count) {
                            Console.Clear();
                            for (int d = 0; d < dates.Count; d++) { printSleep(sleep[d], dates[d]); }
                            Console.Write("press any key to continue...");
                            Console.ReadKey();
                        }

                        else if (toView == dates.Count + 1) { viewing = false; }

                        else {
                            Console.Clear();
                            printSleep(sleep[toView], dates[toView]);
                            Console.Write("press any key to continue...");
                            Console.ReadKey();
                        }
                    }
                }

                // exit
                else if (opt == 2) {
                    running = false;

                    string[] lines = new string[dates.Count];

                    for (int i = 0; i < dates.Count; i++) {
                        string dateStr = $"{dates[i].Month}/{dates[i].Day}/{dates[i].Year-2000}";
                        string sleepStr = String.Join('|', sleep[i]);

                        lines[i] = $"{dateStr},{sleepStr}";
                    }

                    File.WriteAllText("sleep.csv", String.Join('\n', lines));
                }
            }
        }
    }
}
