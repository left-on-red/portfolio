using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FinalProject
{
    class Data
    {
        public Dictionary<string, string> credentials;
        public Dictionary<string, Customer> customers;

        public Data()
        {
            this.credentials = new Dictionary<string, string>();
            this.customers = new Dictionary<string, Customer>();
        }
    }
}
