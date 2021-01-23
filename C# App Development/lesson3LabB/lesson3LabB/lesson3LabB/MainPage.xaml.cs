using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace lesson3LabB
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void Btn_Clicked(object sender, EventArgs e)
        {
            string c = country.Text.ToLower();
            int y = bday.Date.Year;
            int a = DateTime.Today.Year - y;

            bool legal = false;
            int req = 21;
            if (c == "china") { legal = true; req = -1; }
            else if (c == "japan" && a >= 20) { legal = true; req = 20; }
            else if ((c == "usa" || c == "united states") && a >= 21) { legal = true; req = 21; }
            else if (c == "iran") { legal = false; req = -2; }

            if (legal) { drinkable.Text = $"you're allowed to drink in {c}"; }
            else { drinkable.Text = $"you're not allowed to drink in {c} until you're {req} which means you need to wait {req - a} more years!"; }

            if (c == "japan") { drinkable.Text = $"you can drink at any age in {c}!"; }
            if (c == "iran") { drinkable.Text = $"you can't drink at any age in iran..."; }
        }
    }
}
