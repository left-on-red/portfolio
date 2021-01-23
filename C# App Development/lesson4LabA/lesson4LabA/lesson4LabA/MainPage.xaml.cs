using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace lesson4LabA
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {

        private static string[] codes = new string[] { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "" };
        private static char[] letters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ' };
        
        public MainPage()
        {
            InitializeComponent();
        }

        private void dotPressed(object sender, EventArgs e)
        {
            inputLabel.Text += ".";
        }

        private void dashPressed(object sender, EventArgs e)
        {
            inputLabel.Text += "-";
        }

        private void spacePressed(object sender, EventArgs e)
        {
            char chr = '?';
            for (int i = 0; i < codes.Length; i++) {
                if (codes[i].Equals(inputLabel.Text)) { chr = letters[i]; break; }
            }

            outputLabel.Text += chr;
            inputLabel.Text = "";
        }

        private void backPressed(object sender, EventArgs e)
        {
            if (outputLabel.Text != "") {
                outputLabel.Text = outputLabel.Text.TrimEnd(outputLabel.Text[outputLabel.Text.Length - 1]);
            }
        }
    }
}
