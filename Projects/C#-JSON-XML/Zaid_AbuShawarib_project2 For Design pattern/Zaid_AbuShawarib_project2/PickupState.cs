using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class PickupState : IOrderState
    {
        public void Handle(CoffeeShop shop)
        {
            Console.WriteLine("---- Ready for Pickup ----");
            Console.WriteLine($"Enjoy your {shop.CurrentDrink!.Description()}!");
            shop.setState(new TakingOrderState());
        }
    }
}
