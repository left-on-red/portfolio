using Microsoft.EntityFrameworkCore;
using System.Linq;
using System;
namespace Northwind.Models
{
    public class NorthwindContext : DbContext
    {
        public NorthwindContext(DbContextOptions<NorthwindContext> options) : base(options) { }

        public DbSet<Category> Categories { get; set; }
        public DbSet<Product> Products { get; set; }
        public DbSet<Discount> Discounts { get; set; }
        public DbSet<Customer> Customers { get; set; }
        public DbSet<CartItem> CartItems { get; set; }

        private Random _random = new Random();
        public void AddCustomer(Customer customer)
        {
            Customers.Add(customer);
            SaveChanges();
        }
        public void EditCustomer(Customer customer)
        {
            var customerToUpdate = Customers.FirstOrDefault(c => c.CustomerID == customer.CustomerID);
            customerToUpdate.Address = customer.Address;
            customerToUpdate.City = customer.City;
            customerToUpdate.Region = customer.Region;
            customerToUpdate.PostalCode = customer.PostalCode;
            customerToUpdate.Country = customer.Country;
            customerToUpdate.Phone = customer.Phone;
            customerToUpdate.Fax = customer.Fax;
            SaveChanges();
        }
        public CartItem AddToCart(CartItemJSON cartItemJSON)
        {
            int CustomerId = Customers.FirstOrDefault(c => c.Email == cartItemJSON.email).CustomerID;
            int ProductId = cartItemJSON.id;
            // check for duplicate cart item
            CartItem cartItem = CartItems.FirstOrDefault(ci => ci.ProductId == ProductId && ci.CustomerId == CustomerId);
            if (cartItem == null)
            {
                // this is a new cart item
                cartItem = new CartItem()
                {
                    CustomerId = CustomerId,
                    ProductId = cartItemJSON.id,
                    Quantity = cartItemJSON.qty
                };
                CartItems.Add(cartItem);
            }
            else
            {
                // for duplicate cart item, simply update the quantity
                cartItem.Quantity += cartItemJSON.qty;
            }

            SaveChanges();
            cartItem.Product = Products.Find(cartItem.ProductId);
            return cartItem;
        }

        public Discount CreateDiscount(Discount discount)
        {

            int code;

            // generate Discount code and ensure that there's no duplicates
            do { code = _random.Next(1000, 9999); }
            while (Discounts.FirstOrDefault(d => d.Code == code) != null);
            discount.Code = code;

            Discounts.Add(discount);
            SaveChanges();
            return discount;
        }

        public void EditDiscount(Discount discount)
        {
            Discount toUpdate = Discounts.FirstOrDefault(d => d.DiscountID == discount.DiscountID);

            toUpdate.Code = discount.Code;
            toUpdate.StartTime = discount.StartTime;
            toUpdate.EndTime = discount.EndTime;
            toUpdate.DiscountPercent = discount.DiscountPercent;
            toUpdate.Title = discount.Title;
            toUpdate.Description = discount.Description;

            SaveChanges();
        }
    }
}
