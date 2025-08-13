using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Z_A_Project3
{
    public class Canvas
    {
        private List<string> shapes; 
        private List<ICanvasObserver> observers;
        private CanvasCaretaker? canvasCaretaker;

        public Canvas()
        {
            // TODO: Initialize lists 
            shapes = new List<string>();
            observers = new List<ICanvasObserver>();
            canvasCaretaker = new CanvasCaretaker();

        }
        public void AddObserver(ICanvasObserver observer)
        {
            // TODO: Add observer 
            observers.Add(observer);
        }
        public void NotifyObservers(string message)
        {
            // TODO: Notify all observers 
            foreach (var observer in observers)
            {
                observer.Update(message);
            }   
        }
        public void DrawShape(string shape)
        {
            // TODO: Add shape and notify observers 
           
            string formatedInput = formatMessage(shape);
            shapes.Add(shape);
            canvasCaretaker!.SaveState(shapes);
            NotifyObservers(formatedInput);
        }
        public void Undo()
        {
            var prev = canvasCaretaker!.Undo();
            if (prev.Count != shapes.Count)
            {
                string removed = shapes.Except(prev).FirstOrDefault() ?? "last shape";
                shapes = prev;
                Console.WriteLine($"Last action undone: {removed}");
                NotifyObservers($"undo {removed}");
            }
        
            return;
        }
        public void Redo()
        {
            var next = canvasCaretaker!.Redo();
            if (next.Count != shapes.Count)
            {
                string added = next.Except(shapes).FirstOrDefault() ?? "last shape";
                shapes = next;
                Console.WriteLine($"Last action redone: {added}");
                NotifyObservers($"redo {added}");
            }
            return;
        }

        private string formatMessage(string shape)
        {
            var parts = shape.Split(' ');

            switch (parts[0].ToLower())
            {
                case "circle":
                    return $"circle at ({parts[1]},{parts[2]}) radius {parts[3]}";
                case "rectangle":
                    return $"rectangle at ({parts[1]},{parts[2]}) {parts[3]}x{parts[4]}";
                case "line":
                    return $"line from ({parts[1]},{parts[2]}) to ({parts[3]},{parts[4]})";
                default:
                    return shape;
            }
        }
    }
}
