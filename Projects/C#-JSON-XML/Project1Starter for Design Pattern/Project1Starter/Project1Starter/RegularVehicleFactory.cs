using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project1Starter
{
    internal class RegularVehicleFactory : IVehicleFactory
    {
        private static RegularVehicleFactory instance;
        private RegularVehicleFactory() { }
        public static RegularVehicleFactory getInstance()
        {
            if (instance == null)
            {
                instance = new RegularVehicleFactory();
            }
            return instance;
        }

        public ICar produceCar(string type)
        {
            switch (type.ToLower())
            {
                case "sedan":
                    return new Sedan();
                case "truck":
                    return new Truck();
                default:
                    throw new ArgumentException("Unknown car type: " + type);
            }
        }

        public IBike produceBike(string type)
        {
            switch (type.ToLower())
            {
               case "sportsbike":
                    return new SportsBike();
                case "dirtbike":
                    return new DirtBike();
                default:
                    throw new ArgumentException("Unknown bike type: " + type);
            }
        }

    }
}
