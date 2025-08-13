using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Z_A_Project3
{
    public class CanvasCaretaker
    {
        // TODO: Create stacks or lists to store states for undo/redo 
        private Stack<CanvasMemento> undoStack = new ();
        private Stack<CanvasMemento> redoStack = new ();

        public void SaveState(List<string> state)
        {
            // TODO: Save a deep copy of the canvas state 
            undoStack.Push(new CanvasMemento(state));
            redoStack.Clear();
        }
        public bool CanUndo()
        {
            // TODO: Return true if undo is possible 
            return undoStack.Count > 0;
        }
        public bool CanRedo()
        {
            // TODO: Return true if redo is possible 
            return redoStack.Count > 0;
        }
        public List<string> Undo()
        {
            if (!CanUndo())
                return new List<string>();
            if (undoStack.Count > 0)
            {
                var currentState = undoStack.Peek().Shapes;
                redoStack.Push(new CanvasMemento(currentState));
                undoStack.Pop();
            }
            return undoStack.Count > 0 ? new List<string>(undoStack.Peek().Shapes) : new List<string>();

        }
        public List<string> Redo()
        {
            if (!CanRedo())
                return new List<string>(); 

            var memento = redoStack.Pop(); 
            undoStack.Push(new CanvasMemento(memento.Shapes));
            return new List<string>(memento.Shapes);
        }

    }
}
