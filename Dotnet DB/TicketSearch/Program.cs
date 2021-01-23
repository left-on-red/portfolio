using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;

namespace TicketSearch
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Bug> bugs = new List<Bug>();
            List<Enhancement> enhancements = new List<Enhancement>();
            List<Task> tasks = new List<Task>();

            if (File.Exists("Tickets.csv")) {
                // id,summary,status,priority,submitter,assigned,watching,severity
                string[] csv = File.ReadAllLines("Tickets.csv");
                for (int c = 0; c < csv.Length; c++) {
                    string[] split = csv[c].Split(",");

                    int id = int.Parse(split[0]);
                    string summary = split[1];
                    int status = int.Parse(split[2]);
                    int priority = int.Parse(split[3]);
                    string submitter = split[4];
                    string assigned = split[5];
                    string watching = split[6];

                    int severity = int.Parse(split[7]);

                    bugs.Add(new Bug(id, summary, status, priority, submitter, assigned, watching, severity));
                }
            }

            if (File.Exists("Enhancements.csv")) {
                // id,summary,status,priority,submitter,assigned,watching,software,cost,reason,estimate
                string[] csv = File.ReadAllLines("Enhancements.csv");
                for (int c = 0; c < csv.Length; c++) {
                    string[] split = csv[c].Split(",");

                    int id = int.Parse(split[0]);
                    string summary = split[1];
                    int status = int.Parse(split[2]);
                    int priority = int.Parse(split[3]);
                    string submitter = split[4];
                    string assigned = split[5];
                    string watching = split[6];

                    string software = split[7];
                    string cost = split[8];
                    string reason = split[9];
                    string estimate = split[10];

                    enhancements.Add(new Enhancement(id, summary, status, priority, submitter, assigned, watching, software, cost, reason, estimate));
                }
            }

            if (File.Exists("Tasks.csv")) {
                // id,summary,status,priority,submitter,assigned,watching,projectName,dueDate
                string[] csv = File.ReadAllLines("Tasks.csv");
                for (int c = 0; c < csv.Length; c++) {
                    string[] split = csv[c].Split(",");

                    int id = int.Parse(split[0]);
                    string summary = split[1];
                    int status = int.Parse(split[2]);
                    int priority = int.Parse(split[3]);
                    string submitter = split[4];
                    string assigned = split[5];
                    string watching = split[6];

                    string projectName = split[7];
                    string dueDate = split[8];

                    tasks.Add(new Task(id, summary, status, priority, submitter, assigned, watching, projectName, dueDate));
                }
            }

            Console.Write("please enter your name > ");
            string name = Console.ReadLine();

            string[] options = { "open ticket", "close ticket", "browse tickets", "search tickets", "quit" };
            int prompt(string[] menu, string title) {
                bool running = true;
                int selected = 0;
                while (running) {
                    Console.Clear();
                    
                    Console.ForegroundColor = ConsoleColor.Cyan;
                    Console.WriteLine(title);
                    Console.ForegroundColor = ConsoleColor.White;

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

            bool running = true;
            while (running) {
                int input = prompt(options, "pick an option");

                // open ticket
                if (input == 0) {
                    int type = prompt(new string[]{"bug", "enhancement", "task", "exit"}, "ticket type");
                    
                    // bug
                    if (type == 0) {
                        Console.Clear();
                        int id = bugs.Count;
                        
                        Console.Write("summary > ");
                        string summary = Console.ReadLine();
                        int status = 0;
                        int priority = prompt(new string[]{"LOW", "MEDIUM", "HIGH"}, "priority");
                        
                        Console.Write("assigned (separated by comma) > ");
                        string assigned = String.Join('|', Console.ReadLine().Split(','));

                        Console.Write("watching (separated by comma) > ");
                        string watching = String.Join('|', Console.ReadLine().Split(','));

                        int severity = prompt(new string[]{"LOW", "MEDIUM", "HIGH", "FATAL"}, "severity");

                        bugs.Add(new Bug(id, summary, status, priority, name, assigned, watching, severity));

                        Console.Write("successfully added bug ticket (press any key to continue)");
                        Console.ReadKey();
                    }

                    // enhancement
                    else if (type == 1) {
                        Console.Clear();
                        int id = enhancements.Count;
                        
                        Console.Write("summary > ");
                        string summary = Console.ReadLine();
                        int status = 0;
                        int priority = prompt(new string[]{"LOW", "MEDIUM", "HIGH"}, "priority");
                        
                        Console.Write("assigned (separated by comma) > ");
                        string assigned = String.Join('|', Console.ReadLine().Split(','));

                        Console.Write("watching (separated by comma) > ");
                        string watching = String.Join('|', Console.ReadLine().Split(','));

                        Console.Write("software > ");
                        string software = Console.ReadLine();

                        Console.Write("cost > ");
                        string cost = Console.ReadLine();

                        Console.Write("reason > ");
                        string reason = Console.ReadLine();

                        Console.Write("estimate > ");
                        string estimate = Console.ReadLine();

                        enhancements.Add(new Enhancement(id, summary, status, priority, name, assigned, watching, software, cost, reason, estimate));

                        Console.Write("successfully added enhancement ticket (press any key to continue)");
                        Console.ReadKey();
                    }

                    // task
                    else if (type == 2) {
                        Console.Clear();
                        int id = enhancements.Count;
                        
                        Console.Write("summary > ");
                        string summary = Console.ReadLine();
                        int status = 0;
                        int priority = prompt(new string[]{"LOW", "MEDIUM", "HIGH"}, "priority");
                        
                        Console.Write("assigned (separated by comma) > ");
                        string assigned = String.Join('|', Console.ReadLine().Split(','));

                        Console.Write("watching (separated by comma) > ");
                        string watching = String.Join('|', Console.ReadLine().Split(','));

                        Console.Write("project name > ");
                        string projectName = Console.ReadLine();

                        Console.Write("due date > ");
                        string dueDate = Console.ReadLine();

                        tasks.Add(new Task(id, summary, status, priority, name, assigned, watching, projectName, dueDate));

                        Console.Write("successfully added task ticket (press any key to continue)");
                        Console.ReadKey();
                    }
                }

                // close ticket
                else if (input == 1) {
                    int type = prompt(new string[]{"bug tickets", "enhancement tickets", "task tickets"}, "what kind of tickets do you want to close");

                    // bugs
                    if (type == 0) {
                        int removeMenu() {
                            int openCount = 0;
                            for (int t = 0; t < bugs.Count; t++) { openCount += bugs[t].status == 0 ? 1 : 0; }

                            string[] menu = new string[openCount + 1];
                            Bug[] arr = bugs.ToArray();
                            List<Bug> relatives = new List<Bug>();
                            int c = 0;
                            for (int a = 0; a < arr.Length; a++) {
                                if (arr[a].status == 0) {
                                    menu[c] = $"{arr[a].id} - {arr[a].summary}";
                                    relatives.Add(arr[a]);
                                    c++;
                                }
                            }

                            menu[openCount] = "exit";

                            int response = prompt(menu, "choose a ticket that you wish to close");
                            if (response == openCount) { response = -1; }
                            else { response = relatives[response].id; }
                            return response;
                        }

                        bool removing = true;
                        while (removing) {
                            int toRemove = removeMenu();
                            if (toRemove > -1) { bugs[toRemove].Close(); }
                            else { removing = false; }
                        }
                    }

                    // enhancements
                    if (type == 1) {
                        int removeMenu() {
                            int openCount = 0;
                            for (int t = 0; t < enhancements.Count; t++) { openCount += enhancements[t].status == 0 ? 1 : 0; }

                            string[] menu = new string[openCount + 1];
                            Enhancement[] arr = enhancements.ToArray();
                            List<Enhancement> relatives = new List<Enhancement>();
                            int c = 0;
                            for (int a = 0; a < arr.Length; a++) {
                                if (arr[a].status == 0) {
                                    menu[c] = $"{arr[a].id} - {arr[a].summary}";
                                    relatives.Add(arr[a]);
                                    c++;
                                }
                            }

                            menu[openCount] = "exit";

                            int response = prompt(menu, "choose a ticket that you wish to close");
                            if (response == openCount) { response = -1; }
                            else { response = relatives[response].id; }
                            return response;
                        }

                        bool removing = true;
                        while (removing) {
                            int toRemove = removeMenu();
                            if (toRemove > -1) { enhancements[toRemove].Close(); }
                            else { removing = false; }
                        }
                    }

                    // tasks
                    else if (type == 0) {
                        int removeMenu() {
                            int openCount = 0;
                            for (int t = 0; t < tasks.Count; t++) { openCount += tasks[t].status == 0 ? 1 : 0; }

                            string[] menu = new string[openCount + 1];
                            Task[] arr = tasks.ToArray();
                            List<Task> relatives = new List<Task>();
                            int c = 0;
                            for (int a = 0; a < arr.Length; a++) {
                                if (arr[a].status == 0) {
                                    menu[c] = $"{arr[a].id} - {arr[a].summary}";
                                    relatives.Add(arr[a]);
                                    c++;
                                }
                            }

                            menu[openCount] = "exit";

                            int response = prompt(menu, "choose a ticket that you wish to close");
                            if (response == openCount) { response = -1; }
                            else { response = relatives[response].id; }
                            return response;
                        }

                        bool removing = true;
                        while (removing) {
                            int toRemove = removeMenu();
                            if (toRemove > -1) { tasks[toRemove].Close(); }
                            else { removing = false; }
                        }
                    }
                }

                // browse tickets
                else if (input == 2) {
                    List<Ticket> master = new List<Ticket>();

                    for (int b = 0; b < bugs.Count; b++) { master.Add(bugs[b]); }
                    for (int e = 0; e < enhancements.Count; e++) { master.Add(enhancements[e]); }
                    for (int t = 0; t < tasks.Count; t++) { master.Add(tasks[t]); }

                    int browseMenu() {
                        string[] menu = new string[master.Count + 1];
                        for (int m = 0; m < master.Count; m++) { menu[m] = $"{master[m].type} {master[m].id} - {master[m].summary}"; }

                        menu[master.Count] = "exit";

                        int response = prompt(menu, "pick a ticket to view");
                        if (response == master.Count) { response = -1; }
                        else {
                            Console.Clear();
                            Console.WriteLine(master[response].ToString());
                            Console.Write("press any key to continue...");
                            Console.ReadKey();
                        }

                        return response;
                    }

                    bool quit = false;
                    while (!quit) {
                        int resp = browseMenu();
                        if (resp == -1) { quit = true; }
                    }
                }

                // search tickets
                else if (input == 3) {
                    Console.Clear();
                    Console.Write("enter your search terms > ");
                    string search = Console.ReadLine();


                    List<Ticket> master = new List<Ticket>();

                    for (int b = 0; b < bugs.Count; b++) { master.Add(bugs[b]); }
                    for (int e = 0; e < enhancements.Count; e++) { master.Add(enhancements[e]); }
                    for (int t = 0; t < tasks.Count; t++) { master.Add(tasks[t]); }

                    master = master.Where(m => m.summary.Contains(search)).OrderBy(m => m.type).ToList();

                    int browseMenu() {
                        string[] menu = new string[master.Count + 1];
                        for (int m = 0; m < master.Count; m++) { menu[m] = $"{master[m].type} {master[m].id} - {master[m].summary}"; }

                        menu[master.Count] = "exit";

                        int response = prompt(menu, $"results for \"{search}\"");
                        if (response == master.Count) { response = -1; }
                        else {
                            Console.Clear();
                            Console.WriteLine(master[response].ToString());
                            Console.Write("press any key to continue...");
                            Console.ReadKey();
                        }

                        return response;
                    }

                    bool quit = false;
                    while (!quit) {
                        int resp = browseMenu();
                        if (resp == -1) { quit = true; }
                    }
                }

                // quit
                else if (input == 4) {
                    running = false;
                }
            }

            string[] bugLines = new string[bugs.Count];
            string[] enhancementLines = new string[enhancements.Count];
            string[] taskLines = new string[tasks.Count];

            for (int b = 0; b < bugs.Count; b++) { bugLines[b] = $"{bugs[b].id},{bugs[b].summary},{bugs[b].status},{bugs[b].priority},{bugs[b].submitter},{bugs[b].assigned},{bugs[b].watching},{bugs[b].severity}"; }
            for (int e = 0; e < enhancements.Count; e++) { enhancementLines[e] = $"{enhancements[e].id},{enhancements[e].summary},{enhancements[e].status},{enhancements[e].priority},{enhancements[e].submitter},{enhancements[e].assigned},{enhancements[e].watching},{enhancements[e].software},{enhancements[e].cost},{enhancements[e].reason},{enhancements[e].estimate}"; }
            for (int t = 0; t < tasks.Count; t++) { taskLines[t] = $"{tasks[t].id},{tasks[t].summary},{tasks[t].status},{tasks[t].priority},{tasks[t].submitter},{tasks[t].assigned},{tasks[t].watching},{tasks[t].projectName},{tasks[t].dueDate}"; }

            File.WriteAllLines("Tickets.csv", bugLines);
            File.WriteAllLines("Enhancements.csv", enhancementLines);
            File.WriteAllLines("Tasks.csv", taskLines);
        }
    }
}
