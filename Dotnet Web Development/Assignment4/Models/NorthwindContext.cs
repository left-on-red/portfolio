using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace Assignment4.Models {
    public class NorthwindContext : DbContext
    {
        public NorthwindContext(DbContextOptions<NorthwindContext> options) : base(options) { }

        public DbSet<Category> Categories { get; set; }
        public DbSet<Product> Products { get; set; }
    }
}