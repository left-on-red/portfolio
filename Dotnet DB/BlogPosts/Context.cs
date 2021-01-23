using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

namespace BlogPosts {
    public class Context : DbContext {
        public DbSet<Blog> blogs;
        public DbSet<Post> posts;

        public void AddBlog(Blog blog) {
            this.blogs.Add(blog);
            this.SaveChanges();
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) {
            IConfiguration config = new ConfigurationBuilder().AddJsonFile("config.json", true, true).Build();
            //optionsBuilder.UseSqlServer(@config["connectionString"]);
            optionsBuilder.UseSqlServer(@"Server=bitsql.wctc.edu;Database=Blogs_31_TA;User ID=tachatz;Password=000515734");
        }
    }
}