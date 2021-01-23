﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace MovieDataSearch
{
    class Program
    {
        static void Main(string[] args)
        {

            int prompt(string[] menu, string header) {
                bool running = true;
                int selected = 0;
                while (running) {
                    Console.Clear();

                    if (header != null) {
                        Console.ForegroundColor = ConsoleColor.Cyan;
                        Console.WriteLine(header);
                        Console.ForegroundColor = ConsoleColor.White;
                    }
                    
                    for (int i = 0; i < menu.Length; i++) {
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {menu[i]}"); Console.ForegroundColor = ConsoleColor.White; }
                        else { Console.WriteLine(menu[i]); Console.ForegroundColor = ConsoleColor.White; }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();;
                    switch (cki.Key.ToString()) {
                        case "UpArrow": if (selected > 0) { selected -= 1; } break;
                        case "DownArrow": if (selected < menu.Length - 1) { selected += 1; } break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }

            int promptPages(string[] menu, string header) {
                bool running = true;
                int selected = 0;

                int page = 0;
                int maxPage = menu.Length / 10;

                while (running) {
                    Console.Clear();

                    Console.ForegroundColor = ConsoleColor.Cyan;
                    Console.WriteLine(header);
                    Console.WriteLine($"press the right and left arrows to move between pages (page {page+1}/{maxPage+1})");
                    Console.ForegroundColor = ConsoleColor.White;

                    for (int i = (page*10); (i < menu.Length && i < ((page)*10)+10); i++) {
                         
                        if (i == selected) { Console.ForegroundColor = ConsoleColor.Green; Console.WriteLine($"> {menu[i]}"); Console.ForegroundColor = ConsoleColor.White; }
                        else { Console.WriteLine(menu[i]); }
                    }

                    // arrow-key event handling
                    ConsoleKeyInfo cki = Console.ReadKey();
                    switch (cki.Key.ToString()) {
                        case "UpArrow": if (selected > 0) { selected -= 1; if ((selected/10) < page) { page--; } } break;
                        case "DownArrow": if (selected < menu.Length - 1) { selected += 1; if (selected/10 > page) { page++; } } break;
                        case "LeftArrow": page -= page == 0 ? 0 : 1; selected = page*10; break;
                        case "RightArrow": page += page == maxPage ? 0 : 1; selected = page*10; break;
                        case "Escape": selected = -1; running = false; break;
                        case "Enter": running = false; break;
                    }
                }

                return selected;
            }


            string[] csv = File.ReadAllLines("movies.csv");

            for (int m = 0; m < csv.Length; m++) {
                try {
                    if (csv[m].IndexOf('"') == -1) {
                        string[] split = csv[m].Split(',');
                        string idStr = split[0];
                        int id = int.Parse(idStr);

                        string title = split[1];

                        string genreString = split[2];
                        string[] genres = genreString.Split('|');

                        string director = split[3];
                        string runtime = split[4];

                        Movie.list.Add(new Movie(id, title, director, runtime, genres));
                    }

                    else {
                        string[] split = csv[m].Split(',');
                        string idStr = split[0];
                        int id = int.Parse(idStr);

                        string title = $"{split[1]},{split[2]}".Trim('"');
                        
                        string genreString = split[3];
                        string[] genres = genreString.Split('|');
                        
                        string director = split[4];
                        string runtime = split[5];

                        Movie.list.Add(new Movie(id, title, director, runtime, genres));
                    }
                }
                
                catch(Exception e) {}
            }

            string[] opts = { "browse movies", "add a movie", "search for movies", "exit" };
            bool running = true;

            while (running) {
                int input = prompt(opts, "please select an option");

                // browse movies
                if (input == 0) {
                    string[] arr = new string[Movie.list.Count];
                    for (int c = 0; c < Movie.list.Count; c++) { arr[c] = Movie.list[c].title; }
                    int movieId = promptPages(arr, $"{Movie.list.Count} total movies");
                    if (movieId != -1) {
                        Movie movie = Movie.list[movieId];
                        Console.Clear();
                        Console.Write($"{movie.Display()}\npress any key to continue...");
                        Console.ReadKey();
                    }
                }

                // add a movie
                else if (input == 1) {
                    Console.Write("enter movie title > ");
                    string title = Console.ReadLine();
                    
                    Console.Write("enter movie genres (separated by comma) > ");
                    string[] genres = Console.ReadLine().Split('|');

                    Console.Write("enter the name of the director > ");
                    string director = Console.ReadLine();
                    
                    Console.Write("enter runtime (hh:mm:ss) > ");
                    string runtime = Console.ReadLine();

                    int id = Movie.getNextId();

                    Movie movie = new Movie(id, title, director, runtime, genres);
                    Movie.list.Add(movie);

                    Console.Clear();
                    Console.Write($"added movie with id {id}\npress any key to continue...");
                    Console.ReadKey();
                }

                // search for movies
                else if (input == 2) {
                    Console.Clear();
                    Console.Write("enter your search terms > ");
                    string search = Console.ReadLine();
                    var results = Movie.list.Where(m => m.title.Contains(search)).OrderBy(m => m.title).ToList();

                    string[] arr = new string[results.Count];
                    for (int r = 0; r < results.Count; r++) { arr[r] = results[r].title; }
                    promptPages(arr, $"search results for \"{search}\"");
                }

                // exit
                else if (input == 3) {
                    running = false;

                    string[] movies = new string[Movie.list.Count];

                    for (int m = 0; m < Movie.list.Count; m++) {
                        try {
                            string title = Movie.list[m].title.IndexOf('"') == -1 ? Movie.list[m].title : $"\"{Movie.list[m].title}\"";
                            movies[m] = $"{Movie.list[m].id},{title},{String.Join("|", Movie.list[m].genres)},{Movie.list[m].director},{Movie.list[m].runtime}";
                        }

                        catch(Exception e) {}
                    }

                    File.WriteAllLines("movies.csv", movies);
                }
            }
        }
    }
}
