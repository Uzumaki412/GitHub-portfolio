using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace ZaidAbuShawarib_Project1
{
    internal class FileLoaderEditer
    {
        public static bool readFile(string filePath, out string content) // Method to read a file and return its content
        {
            try
            {
                content = File.ReadAllText(filePath); // Read the entire content of the file
                return true;
            }
            catch (Exception ex) // Catch any exceptions that occur during file reading
            {
                Console.WriteLine($"Error reading file {filePath}: {ex.Message}");
                content = "";
                return false;
            }
        }

        public static void LoadData() // Method to load data from a JSON file into the Program.courses list
        {

            if (File.Exists(Program.DataFilePath)) // Check if the data file exists
            {
                readFile("JSON_Schema.json", out string jsonSchema); // Read the JSON schema file
                readFile(Program.DataFilePath, out string jsonData); // Read the data file

                try
                {
                    Program.courses = JsonConvert.DeserializeObject<List<Course>>(jsonData) ?? new List<Course>(); // Deserialize the JSON data into a list of Course objects
                }
                catch (JsonException ex) // Catch any JSON parsing exceptions
                {
                    Console.WriteLine("Error parsing JSON data: " + ex.Message);
                }
            }
            else
            {
                // If the data file does not exist, prompt the user to create a new file
                Console.Write($"Grades data file {Program.DataFilePath} not found. Create new file (y/n): "); 
                string? input = Console.ReadLine()?.ToLower();
                if (input == "y")
                {
                    Program.courses = new List<Course>(); // Initialize an empty list of Course objects
                    SaveData();
                }
                else
                {
                    Console.WriteLine("Exiting application.");
                    Environment.Exit(0); // Exit the application if the user does not want to create a new file
                }
            }
        }

        public static void SaveData() // Method to save the current state of Program.courses to the data file
        {
            try
            {
                string json = JsonConvert.SerializeObject(Program.courses); // Serialize the list of Course objects to a JSON string
                File.WriteAllText(Program.DataFilePath, json);// Write the JSON string to the data file
            }
            catch (Exception ex) // Catch any exceptions that occur during file writing
            {
                Console.WriteLine("Error saving data: " + ex.Message);
            }
        }

        public static void AddCourse() // Method to add a new course to the Program.courses list
        {
            Course newCourse; // Initialize a new Course object
            readFile(Program.JSON_Schema_FilePath, out string jsonSchema); // Read the JSON schema file


            while (true)
            {
                Console.Write("Enter course code: ");
                string code = Console.ReadLine()!;

                newCourse = new Course { code = code }; // Create a new Course object with the provided code

                if (!ValidateSchema.validateSchema(newCourse, jsonSchema)) // Validate the new course against the JSON schema
                {
                    Console.WriteLine("ERROR: Invalid course data.");
                    continue;
                }
                break;
            }
            Console.WriteLine("Course added successfully.");
            Program.courses.Add(newCourse); // Add the new course to the Program.courses list
            Console.WriteLine();
            SaveData(); // Save the updated list of courses to the data file
        }

        public static void addEvaluation(Course course) // Method to add a new evaluation to a course
        {
            int outOf = 0; // Variable to store the 'out of' mark
            while (true)
            {
                Console.Write("\nEnter a description: ");
                string? description = Console.ReadLine(); // Read the description of the evaluation

                while (true) // Prompt the user for the 'out of' mark until a valid integer is entered
                {
                    Console.Write("Enter the 'out of' mark: ");
                    string? outOfInput = Console.ReadLine();

                    if (int.TryParse(outOfInput, out outOf)) //try to parse the input as an integer
                    {
                        break; // Exit the loop if a valid integer is entered
                    }
                    Console.WriteLine("Your answer must be integer. Please try again");
                }

                Console.Write("Enter the % weight: ");
                string? weightInput = Console.ReadLine();

                Console.Write("Enter the earned marks or press Enter to skip: ");
                string? earnedMarksInput = Console.ReadLine();

                if (!double.TryParse(weightInput, out double weight)); // try to parse the input as a double

                double earnedMarks = 0;
                if (!string.IsNullOrWhiteSpace(earnedMarksInput)) // check if the input is not empty or whitespace
                {
                    if (!double.TryParse(earnedMarksInput, out earnedMarks)) // try to parse the input as a double
                    {
                        Console.WriteLine("Invalid earned marks.");
                        continue;
                    }
                }

                var evaluation = new Evaluation // Create a new Evaluation object with the provided details
                {
                    description = description!,
                    outOf = outOf,
                    weight = weight,
                    earnedMarks = earnedMarks
                };

                course.evaluations.Add(evaluation); // Add the new evaluation to the course's evaluations list

                if (readFile(Program.JSON_Schema_FilePath, out string jsonSchema)) // Read the JSON schema file
                {
                    if (ValidateSchema.validateSchema(course, jsonSchema)) // Validate the course against the JSON schema
                    {
                        SaveData(); // Save the updated list of courses to the data file
                        Console.WriteLine("Evaluation added successfully.");
                        break;
                    }
                    else
                    {
                        course.evaluations.Remove(evaluation); // Remove the evaluation if validation fails
                        Console.WriteLine("ERROR: Invalid evaluation");
                    }
                }
                else
                {
                    Console.WriteLine("Failed to load schema file.");
                    course.evaluations.Remove(evaluation); // Remove the evaluation if schema file reading fails
                    break;
                }
            }
        }

        public static void editEvaluation(Evaluation ev, Course course) // Method to edit an existing evaluation
        {
            while (true)
            {
                Console.Write($"\nEnter marks earned out of {ev.outOf}, press Enter to leave unassigned: ");
                string? earnedMarksInput = Console.ReadLine();

                if (string.IsNullOrWhiteSpace(earnedMarksInput)) // check if the input is empty or whitespace
                {
                    return;
                }
                if (!double.TryParse(earnedMarksInput, out double earnedMarks)) // try to parse the input as a double
                {
                    Console.WriteLine("Invalid input. Please enter a valid number.");
                    continue;
                }

                double? temp = ev.earnedMarks; // Store the current earned marks temporarily
                ev.earnedMarks = earnedMarks; // Update the earned marks of the evaluation

                if (readFile(Program.JSON_Schema_FilePath, out string jsonSchema)) // Read the JSON schema file
                {
                    if (ValidateSchema.validateSchema(course, jsonSchema)) // Validate the course against the JSON schema
                    {

                        SaveData(); // Save the updated list of courses to the data file
                        Console.WriteLine("Evaluation updated successfully.");
                        return;
                    }
                    else
                    {
                        ev.earnedMarks = temp; // Revert the earned marks if validation fails
                        Console.WriteLine("ERROR: Invalid evaluation data.");
                    }
                }
                else
                {
                    Console.WriteLine("Failed to load schema file.");
                    return;
                }
            }
        }
    }
}
