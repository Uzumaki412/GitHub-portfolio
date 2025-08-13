namespace Z_A_Project3
{
    public class Program
    {
    public static void Main(string[] args)
        {
            DrawingMediator mediator = new DrawingMediator();
            User userA = new User("UserA");
            User userB = new User("UserB");
            mediator.RegisterUser(userA);
            mediator.RegisterUser(userB);
            Console.WriteLine("Welcome to Collaborative Drawing App");
            Console.WriteLine("Commands: draw circle, draw rectangle, draw line, undo, redo, exit"); 
            
            while (true)
            {
                Console.Write("UserA> ");
                string inputA = Console.ReadLine();
                if (inputA == null) inputA = "";
                inputA = inputA.Trim();


                Console.Write("UserB> ");
                string inputB = Console.ReadLine();
                if (inputB == null) inputB = "";
                inputB = inputB.Trim();


                if (inputA == "exit" || inputB == "exit")
                {
                    Console.WriteLine("Goodbye!");
                    break;
                }
                if (!string.IsNullOrEmpty(inputA))
                {
                    if (inputA.StartsWith("draw "))
                    {
                        string shapeDescription = inputA.Substring(5);
                        userA.Drew(shapeDescription);
                    }
                    mediator.RouteCommand(inputA);
                }
                if (!string.IsNullOrEmpty(inputB))
                {
                    if (inputB.StartsWith("draw "))
                    {
                        string shapeDescription = inputB.Substring(5);
                        userB.Drew(shapeDescription);
                    }
                    mediator.RouteCommand(inputB);
                }
            }
        }
    }
}
