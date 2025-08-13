using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class PaymentState: IOrderState
    {
        public void Handle(CoffeeShop shop)
        {
            Console.WriteLine("---- Payment ----");
            decimal cost = shop.CurrentDrink!.Cost();
            decimal payment = 0;

            while(payment < cost)
            {
                Console.WriteLine("Enter payment amount: $");
                string input = Console.ReadLine()!;

                if(decimal.TryParse(input, out payment))
                {
                    if(payment < cost)
                    {
                        Console.WriteLine("Insufficient amount. Try again.");
                    }
                }
                else
                {
                    Console.WriteLine("Invalid amount. Try again.");
                    payment = 0;
                }
            }

            Console.WriteLine("Payment accepted.");
            decimal change = payment - cost;
            if (change > 0)
            {
                Console.WriteLine($"Change: ${change:F2}");
            }
            shop.setState(new PickupState());
        }
    }
}
