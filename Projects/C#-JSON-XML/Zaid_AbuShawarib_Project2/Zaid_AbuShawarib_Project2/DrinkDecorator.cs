using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class DrinkDecorator : Drink
    {
        public Drink drink;

        public DrinkDecorator(Drink drink) : base(drink.brewer)
        {
            this.drink = drink;
        }

        public override decimal Cost()
        {
            return -1;
        }

        public override string Description()
        {
            return  " ";
        }

        public override void Prepare()
        {
        }
    }
}
