/**
 * Program Name:Exception_Example_1.java
 * Purpose: shows a simple UNCHECKED EXCEPTION called ArithmeticException which will be thrown
 *          if the code does a division by zero using integers.
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class Exception_Example_1
{

	public static void main(String[] args)
	{
		// variables
		int numOne = 5;
		int numTwo = 0;
		
		double numThree = 5.0;
		double numFour = 0.0;
		
		//do the division using the doubles first
		System.out.println("Result of dividing " + numThree + " by " + numFour + " is " + (numThree/numFour) );
		
		System.out.println("Result of dividing " + numOne + " by " + numTwo + " is " + (numOne/numTwo) );
		
		
		System.out.println("End of program.");
		
		
	}
	//end main
}
//end class