using System;

namespace Week14Assignment
{
    class Car
    {
        private string make;
        private string model;
        private string color;
        private double mileage;
        private double tankSize;
        private double gasLevel;
        private double gasMileage;

        public Car(string inputMake, string inputModel, string inputColor, double inputTankSize, double inputGasMileage)
        {
            this.make = inputMake;
            this.model = inputModel;
            this.color = inputColor;
            this.tankSize = inputTankSize;
            this.gasMileage = inputGasMileage;

            this.mileage = 0.0;

            // gives the newly constructed car a full tank
            this.gasLevel = inputTankSize;
        }

        public void refuel()
        {
            this.gasLevel = this.tankSize;
        }

        public void drive(double miles)
        {
            double gasNeeded = miles / this.gasMileage;
            if (this.gasLevel >= gasNeeded)
            {
                this.mileage += miles;
                this.gasLevel -= gasNeeded;
            }

            else { throw new Exception("not enough gas!"); }
        }

        override public string ToString()
        {
            string toReturn =
                $"-----------------------\n" +
                $"make: {this.make}\n" +
                $"model: {this.model}\n" +
                $"color: {this.color}\n" +
                $"mileage: {this.mileage}\n" +
                $"tank size: {this.tankSize}\n" +
                $"gas level: {this.gasLevel}\n" +
                $"gas mileage: {this.gasMileage}\n" +
                $"-----------------------";

            return toReturn;
        }
    }
}
