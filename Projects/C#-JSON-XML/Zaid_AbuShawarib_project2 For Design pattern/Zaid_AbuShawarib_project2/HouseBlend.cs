using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class HouseBlend : Drink
    {
        public HouseBlend(IBrewer brewer) : base(brewer) { }

        public override void Prepare()
        {
            brewer.Brew(Description());
        }

        public override decimal Cost()
        {
            return 1.50m;
        }

        public override string Description()
        {
            return "House Blend Coffee";
        }
    }
}
