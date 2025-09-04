/**
 * Program Name:Exception_Example_4.java
 * Purpose: shows the effect of placing TRY-CATCH block INSIDE THE LOOP
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class Exception_Example_4
{

	public static void main(String[] args)
	{
		// set up a loop to do integer division using the loop counter as the divisor or 
		// denominator in the division operation.
		
		int numerator = 12;//number to be divided by the loop counter
		
		
			for(int i = 3; i >= -1; i--)
			{
				//moved try block INSIDE the scope of the for loop
				try
				{
					System.out.println("Result of dividing " + numerator + " by " + i + 
						                " is " + (numerator / i) );
				}//end try
				catch(ArithmeticException ex)
				{
					System.out.println("ArithmeticException has been caught, message is: " + ex.getMessage());
					ex.printStackTrace();
				}		
				
			}//end loop
		
		/*
		 * NOTE: if you put the try-catch INSIDE the loop body, then processing STAYS INSIDE the
		 * scope of the loop body, and the loop will go to completion, even if an exception is thrown
		 */
		
		System.out.println("End of program.");

	}	//end main
}//end class