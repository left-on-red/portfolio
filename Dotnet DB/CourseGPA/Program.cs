using System;

namespace CourseGPA
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] names = new string[7];
            int[] gradePoints = new int[7];
            for (int i = 0; i < 7; i++)
            {
                Console.Write("Enter a course (Y/N) ");
                string resp = Console.ReadLine().ToUpper();
                if (resp != "Y") { break; }
                Console.Write("Course Name: ");
                names[i] = Console.ReadLine();
                Console.Write("Course Grade: ");
                string grade = Console.ReadLine().ToUpper();
                switch(grade) {
                    case "A": gradePoints[i] = 4; break;
                    case "B": gradePoints[i] = 3; break;
                    case "C": gradePoints[i] = 2; break;
                    case "D": gradePoints[i] = 1; break;
                }
            }

            double total = 0;
            for (int i = 0; i < 7; i++) { total += gradePoints[i]; }
            
            double GPA = total / gradePoints.Length;
            Console.WriteLine($"Your GPA is {GPA}");
        }
    }
}
