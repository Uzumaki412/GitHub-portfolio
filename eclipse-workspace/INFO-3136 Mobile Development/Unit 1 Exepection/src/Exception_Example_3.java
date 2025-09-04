/**
 * Program Name:Exception_Example_3.java
 * Purpose: shows the effect of placing a loop INSIDE a try-catch block
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class Exception_Example_3
{

	public static void main(String[] args)
	{
		// set up a loop to do integer division using the loop counter as the divisor or 
		// denominator in the division operation.
		
		int numerator = 12;//number to be divided by the loop counter
		
		try
		{
			for(int i = 3; i >= -1; i--)
			{
				System.out.println("Result of dividing " + numerator + " by " + i + 
						                " is " + (numerator / i) );
			}//end loop
		}//end try
		catch(ArithmeticException ex)
		{
			System.out.println("ArithmeticException has been caught, message is: " + ex.getMessage());
			ex.printStackTrace();
		}
		//NOTE: when the exception is thrown, processing passes to the catch block, so we have
		// left the loop body, and we cannot get back into it. So, the last iteration of the loop
		// for the counter value of -1 IS NOT EXECUTED. 
		System.out.println("End of program.");

	}	//end main
}//end class