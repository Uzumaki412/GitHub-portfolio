/**
 * Program Name:CircleOne.java
 * Purpose:  a class to create CircleOne objects. This class will throw an exception of the
 *          InvalidRadiusException class if the coder tries to create an object with a negative
 *          radius value.
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class CircleOne
{
	//data member
	private double radius;
	
	//constructor header is where we specify what exception could be thrown
	CircleOne(double r)throws InvalidRadiusException
	{
		if(r <= 0)
		{
			//instantiate the exception
			throw new InvalidRadiusException(r); 
		}
		else
		{
			this.radius = r;
		}
	}//end constructor

	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius)throws InvalidRadiusException
	{
		//MODIFY so that if user passes in a negative value, the exception will be thrown
		if(radius <= 0)
		{
			//instantiate the exception
			throw new InvalidRadiusException(radius); 
		}
		else
		{
			this.radius = radius;
		}
	}
	
		
}//end class