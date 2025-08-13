using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class WhipDecorator : DrinkDecorator
    {
        public WhipDecorator(Drink drink) : base(drink) { }
        public override void Prepare()
        {
            drink.Prepare();
            Console.WriteLine("[Decorator] Adding Whip...");
        }
        public override decimal Cost()
        {
            return drink.Cost() + 0.40m; 
        }
        public override string Description()
        {
            return drink.Description() + ", Whip";
        }
    }
}
