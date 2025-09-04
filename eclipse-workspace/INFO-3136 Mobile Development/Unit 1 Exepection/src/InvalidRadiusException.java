/**
 * Program Name:InvalidRadiusException.java
 * Purpose: A customized subclass of the Exception class that will create an exception object
 *          if a user tries to instantiate a CircleOne object with a negative radius value. 
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class InvalidRadiusException extends Exception
{
	//data member
	private double radius;
	
	//constructor
	InvalidRadiusException(double r)
	{
		//pass the message string to be displayed up to the super class Exception's constructor
		super("InvalidRadiusException, invalid radius value of "+ r + " was entered.");
		this.radius = r;
	}
	//getter for the radius
	public double getRadius()
	{
		return this.radius;
	}
}
//end class