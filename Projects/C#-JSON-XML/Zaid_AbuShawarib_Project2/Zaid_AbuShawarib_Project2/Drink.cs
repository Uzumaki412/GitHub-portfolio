using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public abstract class Drink
    {
        public IBrewer brewer;

        public Drink(IBrewer brewer)
        {
            this.brewer = brewer;
        }

        public abstract void Prepare();
        public abstract decimal Cost();

        public abstract string Description();

    }
}
