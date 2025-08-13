namespace Zaid_AbuShawarib_project2
{
    public class Program
    {
        static void Main(string[] args)
        {
            CoffeeShop shop = new CoffeeShop();
            shop.Next(); // Taking order
            shop.Next(); // Preparing
            shop.Next(); // Payment
            shop.Next(); // Pickup
        }
    }
}
