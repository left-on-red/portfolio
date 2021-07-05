using Microsoft.AspNetCore.Mvc;
using Northwind.Models;
using System.Linq;
using System;

namespace Northwind.Controllers
{
    public class CustomerController : Controller
    {
        private NorthwindContext _northwindContext;
        public CustomerController(NorthwindContext db) => _northwindContext = db;

        public IActionResult Index() => View(_northwindContext.Customers);

        public IActionResult Register() => View();

        [HttpPost]
        public IActionResult Register(Customer customer) {
            
            bool exists = customer.CompanyName != null;
            bool unique = !_northwindContext.Customers.Any(c => c.CompanyName.Equals(customer.CompanyName));

            if (!unique) { ModelState.AddModelError(string.Empty, "That name is already taken"); }

            if (exists && unique) {
                _northwindContext.AddCustomer(customer);
                return RedirectToAction("Index");
            }

            return View(customer);
        }
    }
}


/*
using Microsoft.AspNetCore.Mvc;
using Northwind.Models;
using System.Linq;
using System;
using System.Globalization;
using System.Collections.Generic;

namespace Northwind.Controllers
{
    public class ProductController : Controller
    {
        // this controller depends on the NorthwindRepository
        private NorthwindContext _northwindContext;
        public ProductController(NorthwindContext db) => _northwindContext = db;
        public IActionResult Category() => View(_northwindContext.Categories.OrderBy(c => c.CategoryName));

        public IActionResult Discount() {
            var discounts = _northwindContext.Discounts.Where(d => d.EndTime.CompareTo(DateTime.Now) > 0).OrderBy(d => d.EndTime.Millisecond);
            var products = _northwindContext.Products.Where(p => discounts.Count(d => d.ProductID == p.ProductId) > 0);

            return View(new Tuple<List<Discount>, List<Product>>(discounts.ToList(), products.ToList()));

            //return View(view);
            // View(_northwindContext.Discounts.Where(d => d.EndTime.CompareTo(DateTime.Now) > 0).OrderBy(d => d.EndTime.Millisecond)
        }
        public IActionResult Index(int id) => View(_northwindContext.Products.Where(p => p.CategoryId == id && p.Discontinued == false).OrderBy(p => p.ProductName));
    }
}

*/