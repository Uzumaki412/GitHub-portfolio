using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class ManualSteamer : IBrewer
    {
        public void Brew(string description)
        {
            Console.WriteLine($"[Manual] Brewing {description}...");
        }
    }
    
}
