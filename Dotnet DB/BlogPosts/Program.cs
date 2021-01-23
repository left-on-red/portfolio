using System;
using System.Linq;

namespace BlogPosts
{
    class Program
    {
        static void Main(string[] args)
        {
            var db = new Context();
            Console.WriteLine(db.blogs.Count(null));
            Console.ReadKey();

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

            string[] opts = { "create", "browse", "exit" };

            bool running = true;
            while (running) {
                int action = prompt(opts, "blogs and posts");
                
                // create
                if (action == 0) {
                    bool blogging = true;
                    
                    while (blogging) {
                        var blogs = db.blogs.OrderBy(b => b.Name);
                        string[] names = new string[blogs.Count<Blog>() + 1];
                        int i = 0;
                        foreach (var item in blogs) { names[i] = item.Name; i++; }
                        //for (int b = 0; b < blogs.Length; b++) { names[b] = blogs[b].Name; }
                        names[names.Length - 1] = "[create blog]";
                        int blogSelect = prompt(names, "pick one");
                        
                        // create
                        if (blogSelect == names.Length - 1) {
                            Console.Clear();
                            Console.Write("enter a name for the new blog > ");
                            string blogName = Console.ReadLine();
                            Console.Clear();
                            db.AddBlog(new Blog { Name = blogName });
                            Console.Write($"blog added - {blogName}\npress any key to continue...");
                            Console.ReadKey();
                            Console.Clear();
                        }

                        // select
                        else {
                            blogging = false;
                        }
                    }
                }

                // browse
                else if (action == 1) {
                    var blog = new Blog { Name = "test blog" };
                    Console.WriteLine(blog.BlogId);
                    db.AddBlog(blog);
                    //foreach(var item in db.blogs) { Console.WriteLine(((Blog)item).Name); }
                }

                // exit
                else if (action == 2) {
                    running = false;
                }
            }
        }
    }
}
