/**
 * Program Name:Exception_Example_7.java
 * Purpose: another example of RE-THROWING an exception.
 *          In this REVISION, we have added an OUTER TRY block, and an OUTER CATCH-ALL BLOCK to 
 *          catch any exception that is not in our primary catch blocks or to catch an exception
 *          that gets RE-THROWN.
 *          
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */

public class Exception_Example_7
{

	public static void main(String[] args)
	{
		// create some variables
		int[] intArray = {5,2,7,0,9};
		int numerator = 100;
		
		//loop through array and do division operation, and also have array go OUT OF BOUNDS.
		for(int i = 0; i <= intArray.length; i++)
		{
			//OUTER try added here
			try
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
					//REVISION HERE: re-throw the exception back to the ENCLOSING TRY BLOCK.
					System.out.println("STUB: NOW RE-THROWING THE EXCEPTION BACK TO OUTER TRY BLOCK...");
					
					throw(ex);
				}
				
			}//end outer try
			
			//GENERAL CATCH BLOCK MOVED to the OUTER TRY
			catch(Exception ex)
			{
				System.out.println("A general or re-thrown exception has been caught, and message is: ");
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
		
    System.out.println("End of program.");
    
	}
	//end main
}
//end class