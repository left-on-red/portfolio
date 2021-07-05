using Microsoft.AspNetCore.Mvc;
using Northwind.Models;
using System.Linq;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;

namespace Northwind.Controllers
{
    public class DiscountController : Controller
    {
        // this controller depends on the NorthwindRepository & the UserManager
        private NorthwindContext _northwindContext;
        public DiscountController(NorthwindContext db) { _northwindContext = db; }
        [Authorize(Roles = "employee")]
        public IActionResult Create() {
            ViewBag.Products = _northwindContext.Products;
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken, Authorize(Roles = "employee")]
        public IActionResult Create(Discount model)
        {
            if (ModelState.IsValid)
            {
                if (model.StartTime == null) { ModelState.AddModelError("", "Start Time is Required"); }
                if (model.EndTime == null) { ModelState.AddModelError("", "End Time is Required"); }

                if (ModelState.IsValid) {
                    Discount discount = _northwindContext.CreateDiscount(model);
                    return RedirectToAction("Detail", new { id = discount.DiscountID });
                }

                else { return View(); }
            }

            else { return View(); }
        }

        [Authorize(Roles = "employee")]
        public IActionResult Edit(int id) {
            Discount discount = _northwindContext.Discounts.FirstOrDefault(d => d.DiscountID == id);
            ViewBag.Products = _northwindContext.Products;
            if (discount != null) { return View(discount); }
            else { return RedirectToAction("Index"); }
        }

        [HttpPost]
        [ValidateAntiForgeryToken, Authorize(Roles = "employee")]
        public IActionResult Edit(Discount model) {
            _northwindContext.EditDiscount(model);
            return RedirectToAction("Detail", new { id = model.DiscountID });
        }



        public IActionResult Index() => View(_northwindContext.Discounts);

        public IActionResult Detail(int id) {
            Discount discount = _northwindContext.Discounts.FirstOrDefault(d => d.DiscountID == id);
            Product product = _northwindContext.Products.FirstOrDefault(p => p.ProductId == discount.ProductID);
            if (discount != null) { return View(new DiscountProductPair{ discount = discount, product = product }); }
            else { return RedirectToAction("Index"); }
        }

        // [ValidateAntiForgeryToken, Authorize(Roles = "employee")]
        [HttpPost, Route("Discount/Delete/{id}")]
        // returns specific product
        public void Delete(int id) {
             Discount discount = _northwindContext.Discounts.FirstOrDefault(d => d.DiscountID == id);
            _northwindContext.Discounts.Remove(discount);
            _northwindContext.SaveChanges();
        }
    }
}
