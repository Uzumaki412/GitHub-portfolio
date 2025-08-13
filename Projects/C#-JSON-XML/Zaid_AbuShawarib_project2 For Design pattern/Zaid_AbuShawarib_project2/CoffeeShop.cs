using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class CoffeeShop
    {
        public IOrderState state;
        public Drink? CurrentDrink { get; set; } 

        public CoffeeShop()
        {
            state = new TakingOrderState();
        }

        public void setState(IOrderState newState)
        {
            state = newState;
        }

        public void Next()
        {
            state.Handle(this);
        }
    }
}
