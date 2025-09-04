/**
 * Program Name:Exception_Example_6.java
 * Purpose: Shows how an exception can be RE-THROWN by the catch block that handles it. The re-throw
 *          causes the exception to be thrown back to the calling method. 
 *          REVISION: had the ArrayIndexOutOfBounds catch block RE-THROW the exception back to 
 *          main. In this example, the program will then crash.
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class Exception_Example_6
{

	public static void main(String[] args)
	{
		// create some variables
		int[] intArray = {5,2,7,0,9};
		int numerator = 100;
		
		//loop through array and do division operation, and also have array go OUT OF BOUNDS by using <=
		for(int i = 0; i <= intArray.length; i++)
		{
			
			try
			{
				System.out.println("Dividing " + numerator +" by " + intArray[i]);
				System.out.println("Result is " + (numerator/intArray[i]));
			}
			
			
			catch(ArithmeticException ex)
			{
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				
			}
			catch(ArrayIndexOutOfBoundsException ex)
			{
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				//REVISION HERE: re-throw the exception back to the main. This should crash the program.
				System.out.println("STUB: NOW RE-THROWING THE EXCEPTION BACK TO MAIN...");
				throw(ex);
			}
			//add a CATCH-ALL block, which will catch any exception that is a subclass of Exception
			//NOTE: the most GENERAL or LEAST SPECIFIC exception class (i.e the one that is HIGHEST IN THE 
			// class hierarchy) MUST GO AT THE BOTTOM of the CATCH STACK. Otherwise it will catch
			// any of the subclass exceptions as well as its own exceptions. 
			catch(Exception ex)
			{
				System.out.println("A general exception has been caught, and message is: ");
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
      
			//ADD A finally block just to show how it works...
			finally
			{
				System.out.println("Now in the finally block, WHICH ALWAYS RUNS, NO MATTER IF AN EXCEPTION "+
			                     " IS THROWN OR NOT. THIS CODE WILL RUN ON EVERY ITERATION. ");
			}		
		}//end loop
    System.out.println("\nEnd of program."); 
	}	//end main
}//end class