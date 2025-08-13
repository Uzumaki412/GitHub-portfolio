using System;
namespace Project1Starter
{
	public interface IVehicleFactory
	{
		IBike produceBike(string type);
		ICar produceCar(string type);
	}
}

