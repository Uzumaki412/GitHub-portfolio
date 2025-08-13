using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class PreparingState: IOrderState
    {
        public void Handle(CoffeeShop shop)
        {
            Console.WriteLine("---- Preparing drink ----");
            shop.CurrentDrink!.Prepare();
            Console.WriteLine($"Cost: ${shop.CurrentDrink!.Cost():F2}");
            shop.setState(new PaymentState());
        }
    }
}
