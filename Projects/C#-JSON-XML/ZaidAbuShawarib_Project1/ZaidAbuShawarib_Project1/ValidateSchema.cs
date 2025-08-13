using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Schema;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ZaidAbuShawarib_Project1
{
    internal class ValidateSchema
    {
        public static bool validateSchema(Course course, string jsonSchema) // Method to validate a course against a JSON schema
        {
            string jsonData = JsonConvert.SerializeObject(course); // Convert course object to JSON string
            try
            {
                JSchema schema = JSchema.Parse(jsonSchema); // Parse the JSON schema
                JObject courseJson = JObject.Parse(jsonData); // Parse the course JSON string into a JObject
                return courseJson.IsValid(schema); // Validate the course JSON against the schema
            }
            catch (Exception ex) // Catch any exceptions that occur during validation
            {
                Console.WriteLine($"Error validating course: {ex.Message}");
                return false;
            }
        }
    }
}
