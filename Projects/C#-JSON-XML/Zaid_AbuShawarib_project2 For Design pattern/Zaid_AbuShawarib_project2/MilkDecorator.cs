using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    internal class MilkDecorator : DrinkDecorator
    {
        public MilkDecorator(Drink drink) : base(drink) {  }

        public override void Prepare()
        {
            drink.Prepare();
            Console.WriteLine("[Decorator] Adding Milk...");
        }

        public override decimal Cost()
        {
            return drink.Cost() + 0.30m; 
        }

        public override string Description()
        {
            return drink.Description() + ", Milk";
        }
    }
}
