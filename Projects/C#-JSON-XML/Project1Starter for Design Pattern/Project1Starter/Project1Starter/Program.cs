using System;

namespace Project1Starter
{
    class Program
    {
        //implement this method
        static void printCar(IVehicleFactory factory, string bike, string car)
        {
            try
            {
                IBike bikeType = factory.produceBike(bike);
                ICar carType = factory.produceCar(car);

                Console.WriteLine($"Bike: {bikeType.getType() }");
                Console.WriteLine($"Car: {carType.getType()}");
            }catch (ArgumentException e)
            {
                Console.WriteLine($"Error: {e.Message}");
            }
        }
        static void Main(string[] args)
        {
            bool keepRunning = true;
            IVehicleFactory factory;
            while (keepRunning)
            {
                Console.Write("Electric or Regular? ");
                string factoryString = Console.ReadLine()!.ToLower();

                if (factoryString == "electric")
                {
                    factory = ElectricVehicleFactory.getInstance();
                }   
                else if(factoryString == "regular")
                {
                    factory = RegularVehicleFactory.getInstance();
                }
                else
                {
                    Console.WriteLine("\nInvalid factory type. Please enter 'Electric' or 'Regular'.");
                    continue;
                }

                Console.Write("Enter a bike: ");
                string bike = Console.ReadLine()!.ToLower();
                Console.Write("Enter a car: ");
                string car = Console.ReadLine()!.ToLower();

                printCar(factory, bike, car);

                Console.Write("Repeat? Y/N");
                string repeat = Console.ReadLine()!.ToUpper();
                if (repeat == "N")
                {
                    keepRunning = false;
                }
            }
        }
    }
}