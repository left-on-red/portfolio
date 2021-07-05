using System;
using Microsoft.AspNetCore.Mvc;
using Northwind.Models;
using System.Linq;
using Microsoft.AspNetCore.Identity;

namespace Northwind.Controllers
{
    public class HomeController : Controller
    {
        // this controller depends on the NorthwindRepository
        private NorthwindContext _northwindContext;
        private UserManager<AppUser> userManager;
        //public HomeController(NorthwindContext db) => _northwindContext = db;
        public HomeController(NorthwindContext db, UserManager<AppUser> usrMgr) {
            _northwindContext = db;
            userManager = usrMgr;
        }

        public ActionResult Index() => View(_northwindContext.Discounts.Where(d => d.StartTime <= DateTime.Now && d.EndTime > DateTime.Now).Take(3));
    }
}
