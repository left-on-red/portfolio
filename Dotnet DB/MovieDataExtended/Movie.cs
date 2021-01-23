using System.Collections.Generic;

namespace MovieDataExtended {
    class Movie : Media {

        public static List<Movie> list = new List<Movie>();

        public string director;
        public string runtime;
        public string[] genres;
        public Movie(int id, string title, string director, string runtime, string[] genres) {
            this.id = id;
            this.title = title;
            this.director = director;
            this.runtime = runtime;
            this.genres = genres;
        }

        public static Movie getById(int id) {
            Movie toReturn = null;
            for (int i = 0; i < list.Count; i++) { if (list[i].id == id) { toReturn = list[i]; } }
            return toReturn;
        }

        public static int getNextId() {
            int id = 1;
            bool found = false;

            while (!found) {
                if (Movie.getById(id) == null) { found = true; }
                else { id++; }
            }

            return id;
        }

        public override string Display() {
            return $"{base.Display()}\ndirector: {this.director}\nruntime: {this.runtime}\ngenres: {string.Join(", ", this.genres)}";
        }
    }
}