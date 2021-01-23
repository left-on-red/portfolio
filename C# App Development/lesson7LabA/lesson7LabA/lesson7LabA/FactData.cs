using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace lesson7LabA
{
    public class FactData
    {
        public FactData() { }
        public static IEnumerable<FactData> All { private set; get; }
        public string TheFact { get; set; }
        public string ShortFact { get; set; }

        static FactData()
        {
            List<FactData> all = new List<FactData>();
            all.Add(new FactData()
            {
                TheFact = "Dogs noses are wet",
                ShortFact = "Dog Noses"
            });

            all.Add(new FactData()
            {
                TheFact = "Three dogs survived the Titanic sinking",
                ShortFact = "Titanic"
            });

            all.Add(new FactData()
            {
                TheFact = "A dog can be used in court",
                ShortFact = "Legal not Beagle"
            });

            All = all;
        }
    }
}
