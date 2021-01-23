using System;
using System.Collections.Generic;
using System.IO;

namespace ObjectOrientedTickets
{
    class Program
    {
        class Ticket {
            public int ID;
            public string summary;
            
            // 0 = open, 1 = closed
            public int status;

            // 0 = low, 1 = medium, 2 = high
            public int priority;
            public string submitted;
            public List<string> assigned = new List<string>();
            public List<string> watching = new List<string>();

            public Ticket(int ID, string summary, int status, int priority, string submitted, string assigned, string watching) {
                this.ID = ID;
                this.summary = summary;
                this.status = status;
                this.priority = priority;

                this.submitted = submitted;

                string[] assigns = assigned.Split('|');
                for (int i = 0; i < assigns.Length; i++) { this.assigned.Add(assigns[i]); }

                string[] watches = watching.Split('|');
                for (int i = 0; i < watches.Length; i++) { this.watching.Add(watches[i]); }
            }

            public void Close() { status = 1; }

            override public string ToString() {
                string status = this.status == 0 ? "OPEN" : "CLOSED";
                string priority = this.priority == 0 ? "LOW" : this.priority == 1 ? "MEDIUM" : this.priority == 2 ? "HIGH" : "LOW";
                string assigned = this.assigned.ToArray().Length > 0 ? $"- ASSIGNED: {string.Join(',', this.assigned.ToArray())}" : "";
                string watching = this.watching.ToArray().Length > 0 ? $"- WATCHING: {string.Join(',', this.watching.ToArray())}" : "";
                return $"{this.ID} - {status} - {priority} - {this.summary} - AUTHOR: {this.submitted} {assigned} {watching}";
            }
        }

        static void Main(string[] args)
        {
            // stores all the currently loaded tickets
            List<Ticket> tickets = new List<Ticket>();
            if (File.Exists("data.csv")) {
                string file = File.ReadAllText("data.csv");
                if (file != "") {
                    string[] rows = file.Split('\n');
                    for (int r = 0; r < rows.Length; r++) {
                        string[] cols = rows[r].Split(',');
                        tickets.Add(new Ticket(int.Parse(cols[0]), cols[1], int.Parse(cols[2]), int.Parse(cols[3]), cols[4], cols[5], cols[6]));
                    }
                }
            }

            Console.Write("please enter your name: ");
            string name = Console.ReadLine();

            string[] options = { "open ticket", "close ticket", "browse tickets", "quit" };
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

            bool loop = true;
            while (loop) {
                int selected = prompt(options);

                // open ticket
                if (selected == 0) {
                    Console.Clear();
                    int ID = tickets.Count;
                    
                    Console.Write("summary > ");
                    string summary = Console.ReadLine();

                    int status = 0;

                    Console.Write("priority (low, medium, high) > ");
                    string priorityString = Console.ReadLine().ToUpper();
                    int priority = priorityString == "LOW" ? 0 : priorityString == "MEDIUM" ? 1 : priorityString == "HIGH" ? 3 : 0;
                    
                    Console.Write("assigned (separated by comma) > ");
                    string assigned = String.Join('|', Console.ReadLine().Split(','));

                    Console.Write("watching (separated by comma) > ");
                    string watching = String.Join('|', Console.ReadLine().Split(','));

                    tickets.Add(new Ticket(ID, summary, status, priority, name, assigned, watching));

                    Console.Write("successfully added ticket (press any key to continue)");
                    Console.ReadKey();
                }

                // close tickets
                else if (selected == 1) {
                    int removeMenu() {
                        int openCount = 0;
                        for (int t = 0; t < tickets.Count; t++) { openCount += tickets[t].status == 0 ? 1 : 0; }

                        string[] menu = new string[openCount + 1];
                        Ticket[] arr = tickets.ToArray();
                        List<Ticket> relatives = new List<Ticket>();
                        int c = 0;
                        for (int a = 0; a < arr.Length; a++) {
                            if (arr[a].status == 0) {
                                menu[c] = arr[a].ToString();
                                relatives.Add(arr[a]);
                                c++;
                            }
                        }

                        menu[openCount] = "exit";

                        int response = prompt(menu);
                        if (response == openCount) { response = -1; }
                        else { response = relatives[response].ID; }
                        return response;
                    }

                    bool removing = true;
                    while (removing) {
                        int toRemove = removeMenu();
                        if (toRemove > -1) { tickets[toRemove].Close(); }
                        else { removing = false; }
                    }
                }

                // browse tickets
                else if (selected == 2) {
                    int browseMenu() {
                        string[] menu = new string[tickets.Count + 1];
                        Ticket[] arr = tickets.ToArray();
                        for (int a = 0; a < arr.Length; a++) {
                            string status = arr[a].status == 0 ? "OPEN" : "CLOSED";
                            string priority = arr[a].priority == 0 ? "LOW" : arr[a].priority == 1 ? "MEDIUM" : arr[a].priority == 2 ? "HIGH" : "LOW";
                            string assigned = arr[a].assigned.ToArray().Length > 0 ? $"- ASSIGNED: {string.Join(',', arr[a].assigned.ToArray())}" : "";
                            string watching = arr[a].watching.ToArray().Length > 0 ? $"- WATCHING: {string.Join(',', arr[a].watching.ToArray())}" : "";
                            menu[a] = $"{arr[a].ID} - {status} - {priority} - {arr[a].summary} - AUTHOR: {arr[a].submitted} {assigned} {watching}";
                        }

                        menu[tickets.Count] = "exit";

                        int response = prompt(menu);
                        if (response == tickets.Count) { response = -1; }
                        return response;
                    }

                    bool quit = false;
                    while (!quit) {
                        int toRemove = browseMenu();
                        if (toRemove == -1) { quit = true; }
                    }
                }

                // quit
                else if (selected == 3) {
                    loop = false;
                    string[] rows = new string[tickets.Count];
                    for (int t = 0; t < tickets.Count; t++) {
                        rows[t] = $"{tickets[t].ID},{tickets[t].summary},{tickets[t].status},{tickets[t].priority},{tickets[t].submitted},{string.Join('|', tickets[t].assigned.ToArray())},{string.Join('|', tickets[t].watching.ToArray())}";
                    }

                    File.WriteAllText("data.csv", string.Join('\n', rows));
                }
            }
        }
    }
}
