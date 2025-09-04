/**
 * Program Name:CircleOneTester.java
 * Purpose: tests the methods of the CircleOne class
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class CircleOneTester
{

	public static void main(String[] args) throws InvalidRadiusException
	{
		// first, create a LEGAL CircleOne object
		CircleOne circleA = new CircleOne(5.0);
		System.out.println("Radius of circleA object is " + circleA.getRadius() );
		System.out.println("Now changing the radius to 8.5...");
		circleA.setRadius(8.5);
		System.out.println("Radius of circleA object is NOW " + circleA.getRadius() );
		
		//now try setting radius to a negative value...We can do this UNTIL we add some 
		// code to the setter method to prevent it.
	  /*
		System.out.println("Now attempting to change the radius to -3.5...");
		circleA.setRadius(-3.5);
		System.out.println("Radius of circleA object is NOW " + circleA.getRadius() );
		*/
		
		
		//try to create an ILLEGAL CircleOne object
		System.out.println("\nNow trying to instantiate a CircleOne object with a radius of -4.5..");
		CircleOne circleB = new CircleOne(-4.5);
		System.out.println("Radius of circleB object is " + circleB.getRadius() );
		
		System.out.println("END OF PROGRAM.");

	}
	//end main
}
//end class