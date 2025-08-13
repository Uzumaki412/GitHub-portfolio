using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class SyrupDecorator : DrinkDecorator
    {
        public SyrupDecorator(Drink drink) : base(drink) { }

        public override void Prepare()
        {
            drink.Prepare();
            Console.WriteLine("[Decorator] Adding Syrup...");
        }   

        public override decimal Cost()
        {
            return drink.Cost() + 0.50m; 
        }

        public override string Description()
        {
            return drink.Description() + ", Syrup";
        }
    }
}
