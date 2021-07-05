using Microsoft.AspNetCore.Mvc;
using Assignment4.Models;

namespace Assignment4.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index() => View(_northwindContext.Categories);

        private NorthwindContext _northwindContext;
        public HomeController(NorthwindContext db) => _northwindContext = db;

    }
}