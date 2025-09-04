/**
 * Program Name: Exception_Example_2.java
 * Purpose: shows a simple UNCHECKED EXCEPTION called ArithmeticException which 
 *          will be thrown if you try to do an integer division by zero.
 *          
 *          REVISION: here we put the integer division code inside a try catch block
 *          so that we can "catch" the exception when it is thrown and allow the
 *          program to go to completion. 
 * Coder: Bill Pulling for Section 01
 * Date: July 2, 2024
 
 */

public class Exception_Example_2
{

	public static void main(String[] args)
	{
		// variables
		int numOne = 5;
		int numTwo = 0;
		
		double numThree = 5.0;
		double numFour = 0.0;
		
		System.out.println("Result of dividing doubles " + numThree + " by " + numFour +
				                " is " + (numThree/numFour) );
		//REVISION: put a try catch around this code
		try
		{
			System.out.println("\nResult of dividing ints " + numOne + " by " + numTwo +
        " is " + (numOne/numTwo) );
		}
		
		catch(ArithmeticException ex)
		{
			
			System.out.println("ArithmeticException has been caught, message is " +
		                      ex.getMessage() );
			//during development we also do this:
			ex.printStackTrace();//this will print out the stack to 
			                    // show what line caused the exception. 	                     
		}			
		
		System.out.println("End of program. ");
	}
	//end main
}
//end class