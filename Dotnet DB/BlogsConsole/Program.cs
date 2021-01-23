using System;
using System.IO;
using System.Linq;

namespace BlogsConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            var db = new BloggingContext();

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
                        var blogs = db.Blogs.OrderBy(b => b.Name);
                        string[] names = new string[blogs.Count<Blog>() + 2];
                        int i = 0;
                        foreach (var item in blogs) { names[i] = item.Name; i++; }
                        names[names.Length - 2] = "[create blog]";
                        names[names.Length - 1] = "[exit]";
                        int blogSelect = prompt(names, "pick one");

                        // create
                        if (blogSelect == names.Length - 2) {
                            Console.Clear();
                            Console.Write("enter a name for the new blog > ");
                            string blogName = Console.ReadLine();
                            Console.Clear();

                            if (names.Contains(blogName)) {
                                Console.Write("a blog with that name already exists.\npress any key to continue...");
                                Console.ReadKey();
                                Console.Clear();
                            }

                            else {
                                db.AddBlog(new Blog { Name = blogName });
                                Console.Write($"blog added - {blogName}\npress any key to continue...");
                                Console.ReadKey();
                                Console.Clear();
                            }
                        }

                        // exit
                        else if (blogSelect == names.Length - 1) {
                            blogging = false;
                        }

                        // select
                        else {
                            var blog = blogs.ToArray().ElementAt(blogSelect);
                            Console.Clear();
                            Console.Write("enter post title > ");
                            string title = Console.ReadLine();
                            Console.Clear();
                            Console.Write("enter the body of your post > ");
                            string body = Console.ReadLine();
                            Console.Clear();
                            db.AddPost(new Post { Title = title, Content = body, BlogId = blog.BlogId, Blog = blog });
                            Console.Write($"blog post added under blog {blog.Name} - {title}\npress any key to continue...");
                            Console.ReadKey();
                            Console.Clear();
                        }
                    }
                }

                // browse
                else if (action == 1) {
                    bool viewing = true;
                    while (viewing) {
                        Console.Clear();
                        var blogs = db.Blogs.OrderBy(b => b.Name);
                        string[] names = new string[blogs.Count<Blog>() + 1];
                        int i = 0;
                        foreach (var item in blogs) { names[i] = item.Name; i++; }
                        names[names.Length - 1] = "[exit]";
                        int blogSelect = prompt(names, "pick one");

                        // exit
                        if (blogSelect == names.Length - 1) {
                            viewing = false;
                        }

                        // viewing
                        else {
                            bool viewingPosts = true;
                            while (viewingPosts) {
                                Console.Clear();
                                var blog = blogs.ToArray().ElementAt(blogSelect);
                                var posts = db.Posts.Where(p => p.BlogId.Equals(blog.BlogId));
                                string[] postNames = new string[posts.Count() + 1];
                                int p = 0;
                                foreach (var item in posts) { postNames[p] = item.Title; p++; }
                                postNames[postNames.Length - 1] = "[exit]";
                                int postSelect = prompt(postNames, $"{blog.Name} - {posts.Count()} posts");
                                
                                // exit
                                if (postSelect == postNames.Length - 1) {
                                    viewingPosts = false;
                                }

                                //select
                                else {
                                    Console.Clear();
                                    Post post = posts.ToArray().ElementAt(postSelect);
                                    Console.Write($"blog \"{blog.Name}\" > post \"{post.Title}\"\ncontent: {post.Content}\n\n\npress any key to continue...");
                                    Console.ReadKey();
                                    Console.Clear();
                                }
                            }
                        }
                    }
                }

                // quit
                else if (action == 2) {
                    running = false;
                }
            }
        }
    }
}
