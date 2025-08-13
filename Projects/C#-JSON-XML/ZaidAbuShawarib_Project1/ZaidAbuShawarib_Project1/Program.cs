using System;
using System.Collections.Generic;
using System.IO;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Schema;
using System.Text.Json.Serialization;

// Zaid Abu Shawarib
// C# Programming - Project 1
// Date: 2025-06-02
// Description: A console application to track grades for multiple courses, allowing users to add, view, edit, and delete courses and their evaluations.


namespace ZaidAbuShawarib_Project1
{
    // Evaluation class represents an evaluation for a course
    public class Evaluation
    {
        public string description { get; set; } = ""; // description of the evaluation
        public double weight { get; set; } // weight of the evaluation in percentage
        public int outOf { get; set; } // total marks for the evaluation
        public double? earnedMarks { get; set; } // marks earned by the student in the evaluation
        public double? percent => (earnedMarks.HasValue && outOf > 0) ? (earnedMarks / outOf) * 100 : 0.0; // calculates the percentage based on earned marks and out of total marks
        public double? courseMarks => (earnedMarks.HasValue && weight > 0 && percent.HasValue) ? Math.Round((percent.Value * weight / 100),1) : 0.0; // calculates the course marks based on the percentage and weight

    }
    // Course class represents a course with multiple evaluations
    public class Course {
        public string code { get; set; } = ""; // course code
        public List<Evaluation> evaluations { get; set; } = new List<Evaluation>(); // list of evaluations for the course
    }
    internal class Program
    {
        public static JSchema? schema; // JSON schema for validation
        public const string DataFilePath = "grades.json"; // path to the JSON file where course data is stored
        public const string JSON_Schema_FilePath = "../../../JSON_Schema.json"; // path to the JSON schema file
        public static List<Course> courses = new(); // list of courses loaded from the JSON file

        static void Main(string[] args)
        {
            FileLoaderEditer.LoadData(); // Load courses from the JSON file

            while (true)
            {
                PrintMainMenu(); // Display the main menu and handle user input
            }
        }
        private static void PrintMainMenu() // Displays the main menu 
        {
            Console.Clear();
            Console.WriteLine("\n                           ~ GRADES TRACKING SYSTEM ~\n");
            Console.WriteLine("+------------------------------------------------------------------------------+");
            Console.WriteLine("|                                Grades Summary                                |");
            Console.WriteLine("+------------------------------------------------------------------------------+");

            if (courses.Count == 0)
            {
                Console.WriteLine("There are currently no saved courses.\n");
            }

            Console.WriteLine(" #. Course       Marks Earned        Out Of         Percent\n");

            for (int i = 0; i < courses.Count; i++) // display each course with its total marks, weight, and percentage
            {
                var course = courses[i]; // get the course at index i

                double? courseMarksTotal = (double?)course.evaluations.Sum(e => e.courseMarks); // calculate total course marks
                double? weightTotal = (double?)course.evaluations.Sum(e => e.weight); // calculate total weight of evaluations
                double? percentTotal = (weightTotal > 0 ) ? 100 *courseMarksTotal / weightTotal : 0; // calculate total percentage based on course marks and weight

                Console.WriteLine($"{i + 1}. {course.code,-12} {courseMarksTotal,10:F1} {weightTotal,16:F1} {percentTotal,15:F1}"); // display course information
            }

            Console.WriteLine();
            Console.WriteLine("--------------------------------------------------------------------------------");
            Console.WriteLine(" Press # from the above list to view/edit/delete a specific course.");
            Console.WriteLine(" Press A to add a new course.");
            Console.WriteLine(" Press X to quit.");
            Console.WriteLine("--------------------------------------------------------------------------------");
            Console.Write("Enter a command: ");
            

            string? input = Console.ReadLine()?.ToLower(); // read user input and convert it to lowercase
            Console.WriteLine();

            if (input == "x")// exit the application
            {
                Environment.Exit(0); // exit the application
            }
            else if (input == "a")
            {
                FileLoaderEditer.AddCourse(); // add a new course
            }
            else if (int.TryParse(input, out int course) && course > 0 && course <= courses.Count) // check if input is a valid course number
            {
                courseMenu(courses[course - 1]); // display the course menu for the selected course
            }
            else
            {
                Console.WriteLine("Invalid input. Please try again.");
            }
            
        }

        private static void courseMenu(Course course)
        {
            while (true)
            {
                Console.Clear();
                Console.WriteLine("\n                             ~ GRADES TRACKING SYSTEM ~\n");
                Console.WriteLine("+----------------------------------------------------------------------------------+");
                Console.WriteLine($"|                              {course.code} Evaluations                               |");
                Console.WriteLine("+----------------------------------------------------------------------------------+");

                if (course.evaluations.Count == 0)
                {
                    Console.WriteLine($"here are currently no evaluations for {course.code}.\n");
                }
                else
                {
                    Console.WriteLine(" #. Evaluation         Marks Earned     Out Of     Percent  Course Marks  Weight/100\n");
                    for (int i = 0; i < course.evaluations.Count; i++) // iterate through evaluations
                    {
                        var evaluation = course.evaluations[i]; // get the evaluation at index i
                        Console.WriteLine($"{i + 1}. {evaluation.description,-20} {evaluation.earnedMarks,10:F1} {evaluation.outOf,10:F1} {evaluation.percent,10:F1} {evaluation.courseMarks,12:F1} {evaluation.weight,10:F1}"); // display evaluation information
                    }
                }

                Console.WriteLine();
                Console.WriteLine("------------------------------------------------------------------------------------");
                Console.WriteLine(" Press D to delete this course.");
                Console.WriteLine(" Press A to add an evaluation.");
                Console.WriteLine(" Press # from the above list to edit/delete a specific evaluation.");
                Console.WriteLine(" Press X to return to the main menu");
                Console.WriteLine("------------------------------------------------------------------------------------");
                Console.Write("Enter a command: ");

                string input = Console.ReadLine()?.ToLower() ?? ""; // read user input and convert it to lowercase

                if (input == "x") // return to the main menu
                {
                    return;
                }
                else if (input == "d")
                {
                    Console.Write($"Delete {course.code}? (y/n): ");
                    string? confirm = Console.ReadLine()?.ToLower(); // read confirmation input
                    if (confirm != "y") // if the user does not confirm deletion
                    {
                        return;
                    }
                    else
                    {
                        courses.Remove(course); // remove the course from the list
                        FileLoaderEditer.SaveData(); // save the updated course list to the JSON file
                        return;
                    }       
                }
                else if (input == "a")
                {
                    FileLoaderEditer.addEvaluation(course); // add a new evaluation to the course
                }
                else if (int.TryParse(input, out int evIndex) && evIndex > 0 && evIndex <= course.evaluations.Count) // check if input is a valid evaluation number
                {
                    evaluationMenu(course.evaluations[evIndex - 1], course); // display the evaluation menu for the selected evaluation
                }
            }
        }

        private static void evaluationMenu(Evaluation ev, Course course) // displays the evaluation menu for a specific evaluation
        {
            while (true)
            {
                Console.Clear();
                Console.WriteLine($"+------------------------------------------------------------------------------+");
                Console.WriteLine($"|                               {course.code} {ev.description}                              |");
                Console.WriteLine("+------------------------------------------------------------------------------+\n");
                Console.WriteLine($"Marks Earned      Out Of     Percent    Course Marks   Weight/100\n");
                Console.WriteLine($"{ev.earnedMarks,5:F1} {ev.outOf,18:F1} {ev.percent,10:F1} {ev.courseMarks,12:F1} {ev.weight,12:F1}");
                Console.WriteLine("--------------------------------------------------------------------------------");
                Console.WriteLine(" Press D to delete this evaluation.");
                Console.WriteLine(" Press E to edit this evaluation.");
                Console.WriteLine(" Press X to return to the previous menu.");
                Console.WriteLine("--------------------------------------------------------------------------------");
                Console.Write("Enter a command: ");
                string? input = Console.ReadLine()?.ToLower(); // read user input and convert it to lowercase
                if (input == "x") // return to the previous menu
                {
                    return;
                }
                else if (input == "d") 
                {
                    Console.Write($"Delete {course.code}? (y/n): "); // prompt for confirmation before deleting the evaluation
                    string? confirm = Console.ReadLine()?.ToLower(); // read confirmation input
                    if (confirm != "y") // if the user does not confirm deletion
                    {
                        return;
                    }
                    else
                    {
                        course.evaluations.Remove(ev); // remove the evaluation from the course
                        FileLoaderEditer.SaveData(); // save the updated course list to the JSON file
                        return;
                    }
                }
                else if (input == "e") // edit the evaluation
                {
                    FileLoaderEditer.editEvaluation(ev, course); // call the method to edit the evaluation
                }
                else
                {
                    Console.WriteLine("Invalid input. Please try again.");
                }

            }
        }

    }
}
