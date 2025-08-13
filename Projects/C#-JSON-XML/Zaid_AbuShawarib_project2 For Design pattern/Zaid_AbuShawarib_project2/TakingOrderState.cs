using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public class TakingOrderState : IOrderState
    {
        public void Handle(CoffeeShop shop)
        {
            Console.WriteLine("---- Taking order ----");
            Console.WriteLine("Choose brewer (auto/manual):");
            string brewerInput = Console.ReadLine()!.ToLower();
            IBrewer brewer = brewerInput == "auto" ? new AutomaticMachine() : new ManualSteamer();

            Console.WriteLine("Choose drink (espresso/house):");
            string drinkInput = Console.ReadLine()!.ToLower();
            Drink drink = drinkInput == "espresso" ? new Espresso(brewer) : new HouseBlend(brewer);

            Console.WriteLine("Add condiments? (milk/syrup/whip, comma-separated or none):");
            string condimentsInput = Console.ReadLine()!.ToLower();

            if(!string.IsNullOrEmpty(condimentsInput) && condimentsInput != "none")
            {
                string[] condiments = condimentsInput.Split(',');
                foreach (var condiment in condiments)
                {
                    switch (condiment.Trim())
                    {
                        case "milk":
                            drink = new MilkDecorator(drink);
                            break;
                        case "syrup":
                            drink = new SyrupDecorator(drink);
                            break;
                        case "whip":
                            drink = new WhipDecorator(drink);
                            break;
                        default:
                            Console.WriteLine($"Unknown condiment: {condiment}");
                            break;
                    }
                }
            }
            shop.CurrentDrink = drink;
            shop.setState(new PreparingState());
        }
    }
}
