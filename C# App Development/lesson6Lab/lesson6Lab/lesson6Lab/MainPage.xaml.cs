using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace lesson6Lab
{
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {

        string[] images = { "js.png", "cs.jpg", "java.png", "swift.jpg" };
        string[] labels = { "JavaScript!", "C#!", "Java!", "Swift!" };
        int index = 0;

        private void setItem()
        {
            languageImage.Source = images[index];
            languageLabel.Text = labels[index];
        }

        public MainPage()
        {
            InitializeComponent();
        }

        private void SwipedLeft(object sender, SwipedEventArgs e)
        {
            index -= 1;
            if (index < 0) { index = images.Length - 1; }
            setItem();
        }

        private void SwipedRight(object sender, SwipedEventArgs e)
        {
            index += 1;
            if (index > images.Length - 1) { index = 0; }
            setItem();
        }
    }
}
