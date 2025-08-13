using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Z_A_Project3
{  
    public class CanvasMemento
    {
        public List<string> Shapes { get; set; }

        public CanvasMemento(List<string> shapes)
        {
            Shapes = new List<string>(shapes);
        }
    }
}
