using System;
using System.Collections.Generic;
using System.IO;
using NLog;

namespace MovieData
{
    class Program
    {
        //private static Logger logger = LogManager.GetCurrentClassLogger();
            private static Logger logger = LogManager.GetCurrentClassLogger();
            //string path = $"{Directory.GetCurrentDirectory()}/nlog.config";
            //var logger = NLog.Web.NLogBuilder.ConfigureNLog(path).GetCurrentClassLogger();

        class Rating {
            public int id;
            public double rating;
            public long timestamp;

            public Rating(int id, double rating, long timestamp) {
                this.id = id;
                this.rating = rating;
                this.timestamp = timestamp;
            }
        }

        class Movie {
            public int id;
            public string title;
            public string[] genres;

            public static List<Movie> ALL = new List<Movie>();

            private List<Rating> ratings = new List<Rating>();
            public Movie(int id, string title, string[] genres) {
                this.id = id;
                this.title = title;
                this.genres = genres;
            }

            public static int getNextId() {
                int id = 0;
                bool found = false;

                while (!found) {
                    if (Movie.getById(id) == null) { found = true; }
                    else { id++; }
                }

                return id;
            }

            public void addRating(int user, double rating, long timestamp) {
                this.ratings.Add(new Rating(user, rating, timestamp));
            }

            public Rating[] getRatings() {
                Rating[] array = new Rating[this.ratings.Count];
                for (int r = 0; r < this.ratings.Count; r++) { array[r] = this.ratings[r]; }
                return array;
            }

            public double averageRating() {
                Rating[] ratings = this.getRatings();
                double total = 0;
                for (int i = 0; i < ratings.Length; i++) { total += ratings[i].rating; }
                return ratings.Length > 0 ? total / ratings.Length : 0;
            }

            public static Movie getById(int id) {
                Movie movie = null;
                for (int i = 0; i < Movie.ALL.Count; i++) { if (Movie.ALL[i].id == id) { movie = Movie.ALL[i]; } }
                return movie;
            }

            public static void parseMovie(string csv) {
                try {
                    if (csv.IndexOf('"') == -1) {
                        string[] split = csv.Split(',');
                        string idStr = split[0];
                        int id = int.Parse(idStr);

                        string title = split[1];

                        string genreString = split[2];
                        string[] genres = genreString.Split('|');

                        Movie.ALL.Add(new Movie(id, title, genres));
                    }

                    else {
                        string[] split = csv.Split(',');
                        string idStr = split[0];
                        int id = int.Parse(idStr);

                        string title = $"{split[1]},{split[2]}".Trim('"');
                        
                        string genreString = split[3];
                        string[] genres = genreString.Split('|');

                        Movie.ALL.Add(new Movie(id, title, genres));
                    }
                }
                
                catch(Exception e) { logger.Error($"movie failed to parse with error:\n{e.Message}"); }
            }

            public static void parseRating(string csv) {
                string[] split = csv.Split(',');
                string userIdStr = split[0];
                string movieIdStr = split[1];
                string ratingStr = split[2];
                string timestampStr = split[3];

                bool userSuccess = int.TryParse(userIdStr, out int userId);
                if (!userSuccess) { logger.Error($"error parsing user id \"{userIdStr}\" @ parseRating"); return; }

                bool movieSuccess = int.TryParse(movieIdStr, out int movieId);
                if (!movieSuccess) { logger.Error($"error parsing movie id \"{movieIdStr}\" @ parseRating"); return; }

                bool ratingSuccess = double.TryParse(ratingStr, out double rating);
                if (!ratingSuccess) { logger.Error($"error parsing movie rating \"{ratingStr}\" @ parseRating"); return; }

                bool timestampSuccess = long.TryParse(timestampStr, out long timestamp);
                if (!timestampSuccess) { logger.Error($"error parsing rating timestamp \"{timestampStr}\" @ parseRating"); return; }

                bool found = false;
                for (int i = 0; i < Movie.ALL.Count; i++) { if (Movie.ALL[i].id == movieId) { found = true; } }

                if (!found) { logger.Error($"movie with id {movieId} not found"); return; }

                Movie.getById(movieId).addRating(userId, rating, timestamp);
            }
        }

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


            string[] moviesCsv = File.ReadAllLines("movies.csv");
            for (int m = 1; m < moviesCsv.Length; m++) { Movie.parseMovie(moviesCsv[m]); }

            string[] ratingsCsv = File.ReadAllLines("ratings.csv");
            for (int r = 1; r < ratingsCsv.Length; r++) { Movie.parseRating(ratingsCsv[r]); }

            string[] opts = { "browse movies", "add a movie", "exit" };
            bool running = true;

            while (running) {
                int input = prompt(opts, "please select an option");

                // browse movies
                if (input == 0) {
                    string[] arr = new string[Movie.ALL.Count];
                    for (int c = 0; c < Movie.ALL.Count; c++) { arr[c] = Movie.ALL[c].title; }
                    int movieId = promptPages(arr, $"{Movie.ALL.Count} total movies");
                    if (movieId != -1) {
                        Movie movie = Movie.ALL[movieId];
                        Rating[] ratings = movie.getRatings();
                        string[] ratingsStr = new string[ratings.Length+1];
                        ratingsStr[ratings.Length] = "add new rating";
                        for (int r = 0; r < ratings.Length; r++) {
                            ratingsStr[r] = $"{ratings[r].rating}/5";
                        }

                        bool isRating = true;
                        while (isRating) {
                            int ratingInput = promptPages(ratingsStr, $"average rating: {movie.averageRating()}");
                            if (ratingInput == -1) { isRating = false; break; }
                            else if (ratingInput == ratings.Length) {
                                isRating = false;
                                Console.Clear();
                                Console.Write("enter a rating (out of 5) > ");

                                try {
                                    double userRating = double.Parse(Console.ReadLine());
                                    if (userRating > 5) { logger.Error($"the user rating needs to be 5 or less"); }
                                    else if (userRating < 0) { logger.Error($"the user rating needs to be 0 or higher"); }
                                    else {
                                        Console.Write("enter your user id > ");
                                        try {
                                            int userId = int.Parse(Console.ReadLine());
                                            movie.addRating(userId, userRating, DateTimeOffset.Now.ToUnixTimeSeconds());
                                            Console.Write("successfully added rating. press any key to continue...");
                                            Console.ReadKey();
                                        }

                                        catch(Exception e) { logger.Error($"an error occurred while the user entered their id:\n{e.Message}"); }
                                    }
                                }

                                catch(Exception e) { logger.Error($"error parsing user rating:\n{e.Message}"); }
                            }   
                        }
                    }
                }

                // add a movie
                else if (input == 1) {
                    Console.Write("enter movie title > ");
                    string title = Console.ReadLine();
                    
                    Console.Write("enter movie genres (separated by comma) > ");
                    string[] genres = Console.ReadLine().Split('|');

                    int id = Movie.getNextId();

                    Movie movie = new Movie(id, title, genres);
                    Movie.ALL.Add(movie);
                }

                // exit
                else if (input == 2) {
                    running = false;

                    string[] movies = new string[Movie.ALL.Count+1];
                    movies[0] = "movieId,title,genres";

                    List<string> ratings = new List<string>();

                    for (int m = 0; m < Movie.ALL.Count; m++) {
                        try {
                            string title = Movie.ALL[m].title.IndexOf('"') == -1 ? Movie.ALL[m].title : $"\"{Movie.ALL[m].title}\"";
                            movies[m] = $"{Movie.ALL[m].id},{title},{String.Join(",", Movie.ALL[m].genres)}";
                            Rating[] ratingArr = Movie.ALL[m].getRatings();
                            for (int r = 0; r < ratingArr.Length; r++) {
                                try {
                                    ratings.Add($"{ratingArr[r].id},{Movie.ALL[m].id},{ratingArr[r].rating},{DateTimeOffset.Now.ToUnixTimeSeconds()}");
                                }

                                catch(Exception e) { logger.Error($"failed to parse rating for movie: {Movie.ALL[m].id} with error:\n{e.Message}"); }
                            }
                        }

                        catch(Exception e) { logger.Error($"failed to parse movie with error:\n{e.Message}"); }
                    }

                    File.WriteAllLines("movies.csv", movies);
                    File.WriteAllLines("ratings.csv", ratings);
                }
            }
        }
    }
}
