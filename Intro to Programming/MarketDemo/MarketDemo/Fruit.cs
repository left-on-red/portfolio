using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MarketDemo
{
    class Fruit
    {
        private string type;
        private double price;
        public Fruit(string type, double price)
        {
            this.type = type;
            this.price = price;
        }

        public string getType()
        {
            return this.type;
        }

        public double getPrice()
        {
            return this.price;
        }
    }
}
