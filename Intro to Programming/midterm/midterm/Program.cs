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
            // used to correlate index to house
            String[] houses = { "Gryffindor", "Slytherin", "Ravenclaw", "Hufflepuff" };
            // indexes of the answers corresponding to the questions
            int[] answers = { 1, 4, 3 };

            // tried using 2D arrays; didn't work out :/
            String[] questions = {
                    "What is your favorite house?",
                    "Gryffindor",
                    "Slytherin",
                    "Ravenclaw",
                    "Hufflepuff",
                
                    "What is your least favorite house?",
                    "Gryffindor",
                    "Slytherin",
                    "Ravenclaw",
                    "Hufflepuff",
                    
                    "What is the primary protector against dementors?",
                    "Patronus charm",
                    "Basilisk venom",
                    "Dissendium charm",
                    "Muggle blood",
                
                    "Which spell causes the virtums's leg to collapse?",
                    "Knee-reversal hex",
                    "Locomotor Mortis",
                    "Mobiliarbus",
                    "Locomotor Wibbly",
                
                    "Who discovered the 12 uses of dragon blood?",
                    "Tom Riddle",
                    "Rubeus Hagrid",
                    "Albus Dumbledore",
                    "Nicolas Flamel"
            };

            // parses the questions array and splits it into several smaller arrays specified by index
            String[] getQuestion(int index) {
                List<String> toReturn = new List<String>();
                int offset = index * 5;
                for (int i = offset; i < offset + 5; i++) { toReturn.Add(questions[i]); }
                return toReturn.ToArray();
            }

            // configures a cli arrow-key-controlled interface in which it would ask you a question using an array of strings and would return the index of the selected answer (1-max because index 0 is the question text)
            int prompt(String[] question) {
                bool running = true;
                int selected = 1;
                while (running) {
                    Console.Clear();
                    Console.ForegroundColor = ConsoleColor.Cyan;
                    Console.WriteLine(question[0]);
                    for (int i = 1; i < question.Length; i++) {
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {question[i]}"); }
                        else { Console.ForegroundColor = ConsoleColor.White; Console.WriteLine(question[i]); }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();
                    switch(cki.Key.ToString()) {
                        case "UpArrow": if (selected > 1) { selected -= 1; } break;
                        case "DownArrow": if (selected < 4) { selected += 1; } break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }

            Random rand = new Random();
            bool differentHouses = false;
            int fHouse = 0, lHouse = 0, sHouse = rand.Next(1, 5);

            // handles same least and favorite houses
            // (loops until both houses entered are not the same)
            while (!differentHouses) {
                fHouse = prompt(getQuestion(0)) - 1;
                lHouse = prompt(getQuestion(1)) - 1;
                if (fHouse != lHouse) { differentHouses = true; }
                else {
                    Console.SetCursorPosition(0, 0);
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("you need to select 2 different houses!"); Thread.Sleep(2000);
                }
            }

            int[] responses = { prompt(getQuestion(2)), prompt(getQuestion(3)), prompt(getQuestion(4)) };


            // calculating final house according to how many questions you got correct
            for (int i = 0; i < responses.Length; i++) {
                if (responses[i] == answers[i]) {
                    if (rand.Next(0, 4) == 0) { sHouse = fHouse; } }
                else { if (rand.Next(0, 4) == 0) { sHouse = lHouse; } }
            }

            if (sHouse == fHouse) { Console.ForegroundColor = ConsoleColor.Green; }
            else if (sHouse == lHouse) { Console.ForegroundColor = ConsoleColor.Red; }
            else { Console.ForegroundColor = ConsoleColor.Yellow; }

            Console.Clear();
            Console.WriteLine($"you have been assigned to the House of {houses[sHouse]}");

            Console.ReadKey();
        }
    }
}