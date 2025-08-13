using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Z_A_Project3
{
    public class User : ICanvasObserver
    {
        private string name;
        private DrawingMediator mediator;
        public User(string name)
        {
            this.name = name;

        }
        public void SetMediator(DrawingMediator mediator)
        {
            this.mediator = mediator;
        }
        public void Drew(string shapeDescription)
        {
            var parts = shapeDescription.Split(' ');
            string confirmation = "";

            switch (parts[0].ToLower())
            {
                case "circle":
                    if (parts.Length >= 4)
                        confirmation = $"{name} drew circle at ({parts[1]},{parts[2]}) radius {parts[3]}";
                    break;
                case "rectangle":
                    if (parts.Length >= 5)
                        confirmation = $"{name} drew rectangle at ({parts[1]},{parts[2]}) {parts[3]}x{parts[4]}";
                    break;
                case "line":
                    if (parts.Length >= 5)
                        confirmation = $"{name} drew line from ({parts[1]},{parts[2]}) to ({parts[3]},{parts[4]})";
                    break;
            }

            if (!string.IsNullOrEmpty(confirmation))
                Console.WriteLine(confirmation);

        }
        public void Update(string message)
        {
            if (message.StartsWith("undo") || message.StartsWith("redo"))
            {
                Console.WriteLine($"{name} received update: {message}");
            }
            else
            {
                Console.WriteLine($"{name} received update: {message}");
            }
        }
    }
}
