using System;
using System.IO;
using System.Linq;
using NorthwindConsole.Model;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace NorthwindConsole
{
    class Program
    {
        static void Main(string[] args)
        {
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

            var db = new NorthWindConsole_31_TAContext();
            string[] opts = { "categories", "products", "exit" };
            bool running = true;

            while (running) {
                int action = prompt(opts, "pick one");

                // categories
                if (action == 0) {
                    Console.Clear();
                    var query = db.Categories.OrderBy(p => p.CategoryName);
                    string[] categories = new string[query.Count() + 2];
                    categories[0] = "[new]";
                    int c = 0;
                    foreach (var item in query) { categories[c+1] = $"{item.CategoryName} - {item.Description}"; c++; }
                    categories[categories.Length - 1] = "[exit]";
                    int categorySelect = prompt(categories, "select a category");

                    //new category
                    if (categorySelect == 0) {
                        Console.Clear();
                        Categories category = new Categories();
                        Console.Write("enter a name for the category > ");
                        category.CategoryName = Console.ReadLine();
                        Console.Write("enter a description for the category > ");
                        category.Description = Console.ReadLine();

                        ValidationContext context = new ValidationContext(category, null, null);
                        List<ValidationResult> results = new List<ValidationResult>();

                        bool valid = Validator.TryValidateObject(category, context, results, true);
                        if (valid) {
                            if (db.Categories.Any(c => c.CategoryName == category.CategoryName)) {
                                valid = false;
                                results.Add(new ValidationResult("that name already exists", new string[] { "CategoryName" }));
                            }

                            else {
                                db.Categories.Add(category);
                                db.SaveChanges();
                                Console.Write($"added new category \"{category.CategoryName}\"\nenter any key to continue...");
                                Console.ReadKey();
                            }
                        }

                        else {
                            foreach (var result in results) {
                                Console.WriteLine($"error: {result.MemberNames.First()} - {result.ErrorMessage}");
                            }
                        }
                    }

                    // view category
                    else if (categorySelect != categories.Length - 1) {
                        var products = db.Products.Where(p => p.CategoryId == query.ToArray().ElementAt(categorySelect-1).CategoryId).ToArray();

                        string[] productNames = new string[products.Length];
                        for (int i = 0; i < productNames.Length - 1; i++) { productNames[i] = $"{products.ElementAt(i).ProductName} - ${products.ElementAt(i).UnitPrice}"; }
                        prompt(productNames, $"{productNames.Length - 1} products");
                    }
                }

                // products
                else if (action == 1) {
                    string[] filters = { "[all products]", "[discontinued products]", "[active products]", "[new product]", "[exit]" };
                    Products[] products = db.Products.OrderBy(p => p.ProductName).ToArray();
                    bool filtered = true;
                    int filterAction = prompt(filters, "pick one");

                    // all products
                    // (to ensure that it doesn't fall through to the else block)
                    if (filterAction == 0) {}

                    // discontinued products
                    else if (filterAction == 1) {
                        products = db.Products.Where(p => p.Discontinued == true).OrderBy(p => p.ProductName).ToArray();
                    }

                    // active products
                    else if (filterAction == 2) {
                        products = db.Products.Where(p => p.Discontinued == false).OrderBy(p => p.ProductName).ToArray();
                    }

                    // new product
                    else if (filterAction == 3) {
                        Console.Clear();
                        Products product = new Products();
                        Console.Write("enter a name for the product > ");
                        product.ProductName = Console.ReadLine();
                        Console.Write("enter the quantity per unit > ");
                        product.QuantityPerUnit = Console.ReadLine();
                        Console.Write("enter the unit price > ");
                        product.UnitPrice = decimal.Parse(Console.ReadLine());
                        Console.Write("enter number of units in stock > ");
                        product.UnitsInStock = short.Parse(Console.ReadLine());
                        
                        Console.Clear();
                        var suppliers = db.Suppliers.ToArray();
                        string[] supplierNames = new string[suppliers.Length];
                        for (int s = 0; s < supplierNames.Length; s++) { supplierNames[s] = suppliers[s].CompanyName; }
                        product.Supplier = suppliers.ElementAt(prompt(supplierNames, "pick a supplier"));

                        Console.Clear();
                        var categories = db.Categories.ToArray();
                        string[] categoryNames = new string[categories.Length];
                        for (int c = 0; c < categoryNames.Length; c++) { categoryNames[c] = categories[c].CategoryName; }
                        product.Category = categories.ElementAt(prompt(categoryNames, "pick a category"));
                        Console.Clear();

                        db.Products.Add(product);
                        db.SaveChanges();
                        Console.Write($"added new product \"{product.ProductName}\"\nenter any key to continue...");
                        Console.ReadKey();
                        filtered = false;
                    }

                    // exit
                    else { filtered = false; }

                    if (filtered) {
                        string[] productNames = new string[products.Length + 1];
                        productNames[productNames.Length - 1] = "[exit]";
                        for (int i = 0; i < productNames.Length - 1; i++) { productNames[i] = $"{products.ElementAt(i).ProductName} " + (products.ElementAt(i).Discontinued ? "(discontinued)" : "(active)"); }
                        int productPrompt = prompt(productNames, "products");
                        
                        // view
                        if (productPrompt != productNames.Length - 1) {
                            bool propping = true;
                            int id = products.ElementAt(productPrompt).ProductId;

                            while(propping) {
                                Products product = products.First(p => p.ProductId == id);

                                string[,] map = {
                                    { "name", product.ProductName },
                                    { "discontinued", product.Discontinued.ToString() },
                                    { "quantity per unit", product.QuantityPerUnit },
                                    { "unit price", product.UnitPrice.ToString() },
                                    { "units in stock", product.UnitsInStock.ToString() },
                                    { "supplier", db.Suppliers.First(s => s.SupplierId == product.SupplierId).CompanyName },
                                    { "category", db.Categories.First(c => c.CategoryId == product.CategoryId).CategoryName }
                                };

                                string[] props = new string[map.GetLength(0)+1];
                                props[props.Length - 1] = "[exit]";
                                for (int p = 0; p < props.Length - 1; p++) { props[p] = $"{map[p,0]}: {map[p,1]}"; }

                                int propOpt = prompt(props, $"product #{product.ProductId}");
                                
                                Console.Clear();
                                // name
                                if (propOpt == 0) {
                                    Console.Write("enter a new name for the product > ");
                                    products.Where(p => p.ProductId == id).First().ProductName = Console.ReadLine();
                                    db.SaveChanges();
                                }

                                // discontinued
                                else if (propOpt == 1) {
                                    bool to = !product.Discontinued;
                                    products.Where(p => p.ProductId == id).First().Discontinued = to;
                                }

                                // quantity per unit
                                else if (propOpt == 2) {
                                    Console.Write("enter a new quantity per unit for the product > ");
                                    products.Where(p => p.ProductId == id).First().QuantityPerUnit = Console.ReadLine();
                                    db.SaveChanges();
                                }

                                // unit price
                                else if (propOpt == 3) {
                                    Console.Write("enter a new unit price for the product > ");
                                    products.Where(p => p.ProductId == id).First().UnitPrice = decimal.Parse(Console.ReadLine());
                                    db.SaveChanges();
                                }

                                // units in stock
                                else if (propOpt == 4) {
                                    Console.Write("enter new units in stock for the product > ");
                                    products.Where(p => p.ProductId == id).First().UnitsInStock = short.Parse(Console.ReadLine());
                                    db.SaveChanges();
                                }

                                // supplier
                                else if (propOpt == 5) {
                                    Suppliers[] suppliers = db.Suppliers.ToArray();
                                    string[] names = new string[suppliers.Length];
                                    for (int n = 0; n < names.Length; n++) { names[n] = suppliers[n].CompanyName; }
                                    //int index = prompt(names, "select a supplier");
                                    //Suppliers supplier = db.Suppliers.First(s => s.SupplierId == suppliers.ElementAt(index).SupplierId).prod;
                                    products.Where(p => p.ProductId == id).First().Supplier = suppliers.ElementAt(prompt(names, "select a supplier"));
                                    db.SaveChanges();
                                }

                                // category
                                else if (propOpt == 6) {
                                    Categories[] categories = db.Categories.ToArray();
                                    string[] names = new string[categories.Length];
                                    for (int n = 0; n < names.Length; n++) { names[n] = categories[n].CategoryName; }
                                    products.Where(p => p.ProductId == id).First().Category = categories.ElementAt(prompt(names, "select a category"));
                                    db.SaveChanges();
                                }

                                // exit
                                else { propping = false; }
                            }
                        }
                    }
                }

                // exit
                else if (action == 2) {
                    running = false;
                }
            }
        }
    }
}