using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project1Starter
{
    internal class ElectricVehicleFactory : IVehicleFactory
    {
        private static ElectricVehicleFactory instance;
        private ElectricVehicleFactory() { }
        public static ElectricVehicleFactory getInstance()
        {
            if (instance == null)
            {
                instance = new ElectricVehicleFactory();
            }
            return instance;
        }
        public ICar produceCar(string type)
        {
            switch (type.ToLower())
            {
                case "esedan":
                    return new ESedan();
                case "etruck":
                    return new ETruck();
                default:
                    throw new ArgumentException("Unknown car type: " + type);
            }
        }

        public IBike produceBike(string type)
        {
            switch (type.ToLower())
            {
                case "esportsbike":
                    return new ESportsBike();
                case "edirtbike":
                    return new EDirtbike();
                default:
                    throw new ArgumentException("Unknown bike type: " + type);
            }
        }
    }
}
