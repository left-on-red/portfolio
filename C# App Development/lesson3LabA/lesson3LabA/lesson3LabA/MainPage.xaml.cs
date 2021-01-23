using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace lesson3LabA
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
            int weight = Int32.Parse(Inp_weight.Text) * 2;
            Lbl_BMI.Text = $"Your BMI is {weight}.";
        }
    }
}
