using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Z_A_Project3
{
        public class DrawingMediator
        {
            private Canvas canvas;
            private List<User> users;
            public DrawingMediator()
            {
                // TODO: Initialize the canvas 
                canvas = new Canvas();
                users = new List<User>();
            }
            public void RegisterUser(User user)
            {
                // TODO: Register a user and connect them to the mediator
                users.Add(user);
                user.SetMediator(this);
                canvas.AddObserver(user);
            }
            public void RouteCommand(string command)
            {
                // TODO: Parse and dispatch the command to the correct component
                command = command.Trim().ToLower();

                if (command.StartsWith("draw "))
                {
                    string shapeDescription = command.Substring(5);
                    canvas.DrawShape(shapeDescription);
                }
                else if (command == "undo")
                {
                    canvas.Undo();
                }
                else if (command == "redo")
                {
                    canvas.Redo();
                }
            }
        }
}
