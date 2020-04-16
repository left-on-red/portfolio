using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace week9Assignment
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] options = {
                "append to file",
                "display line count of file",
                "display character count for each line in file",
                "display a random number of lines from file",
                "quit"
            };

            if (File.Exists("Analytics.txt")) { File.Delete("Analytics.txt"); }
            FileStream create = File.Create("Analytics.txt");
            create.Close();

            void analyze(string str) {
                StreamWriter writer = File.AppendText("Analytics.txt");
                Console.WriteLine(str);
                writer.WriteLine(str);
                writer.Close();
            }

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

            bool isDead = false;
            while (!isDead) {
                int option = prompt();
                Console.Clear();
                switch (option) {
                    case 0: {
                            if (!File.Exists("file.txt")) { File.Create("file.txt"); }
                            StreamWriter writer = File.AppendText("file.txt");

                            bool isEditing = true;
                            while (isEditing) {
                                Console.Write("what do you want to append? (enter nothing to exit) > ");
                                string toEnter = Console.ReadLine();
                                if (toEnter != "") { writer.WriteLine(toEnter); }
                                else { isEditing = false; }
                            }

                            writer.Close();
                        break;
                    }

                    case 1: {
                            if (!File.Exists("file.txt")) { File.Create("file.txt"); }
                            int count = File.ReadAllLines("file.txt").Length;
                            
                            analyze($"file.txt is {count} lines long");
                            Console.ReadKey();
                            break;
                        }
                    case 2: {
                            if (!File.Exists("file.txt")) { File.Create("file.txt"); }
                            string[] lines = File.ReadAllLines("file.txt");
                            for (int l = 0; l < lines.Length; l++) { analyze($"line {l+1} : {lines[l].Length} characters"); }
                            Console.ReadKey();
                            break;
                    }

                    case 3: {
                            if (!File.Exists("file.txt")) { File.Create("file.txt"); }
                            string[] lines = File.ReadAllLines("file.txt");
                            Random rng = new Random();
                            int start = rng.Next(0, lines.Length);
                            int end = rng.Next(start, lines.Length);

                            for (int l = 0; l < lines.Length; l++) {
                                if (l >= start && end >= l) { analyze($"{l+1}: {lines[l]}"); }
                            }

                            Console.ReadKey();

                            break;
                    }
                    case 4: { isDead = true; break; }
                }
            }
        }
    }
}