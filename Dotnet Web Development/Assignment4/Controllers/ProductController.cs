using Microsoft.AspNetCore.Mvc;
using Assignment4.Models;
using System.Linq;

namespace Assignment4.Controllers
{
    public class ProductController : Controller
    {
        public IActionResult Category() => View(_northwindContext.Categories);

        private NorthwindContext _northwindContext;
        public ProductController(NorthwindContext db) => _northwindContext = db;

    }
}