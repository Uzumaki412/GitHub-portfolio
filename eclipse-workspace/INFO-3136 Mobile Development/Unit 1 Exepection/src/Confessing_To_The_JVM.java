/**
 * Program Name:Confessing_To_The_JVM.java
 * Purpose: Shows how to "CONFESS" or "CLAIM" a CHECKED EXCEPTION TYPE to the JVM so that it will allow
 *          your code to compile. CHECKED EXCEPTIONS are checked at COMPILE TIME (T!). 
 *          We CONFESS or CLAIM an exception by using the 'throws' keyword on the method declaration
 *          of the method that contains the code that could cause a problem.
 * @author  Bill Pulling 
 * Date: July 2, 2024
 */
import java.io.*;
public class Confessing_To_The_JVM
{
  //NOTE: we need a throws clause on the main method as shown below
	public static void main(String[] args) throws IOException
	{
		// create a File class object
		File fileOne = new File("C:\\temp56");// using the doubled backslash so Java does not think it is a \t
		
		//next line of code can cause an IOException. To deal with this, we will CONFESS to the JVM
		// that something in the main method could throw this type of exception. 
		System.out.println("The CANONICAL PATH of fileOne is: " +
		                     fileOne.getCanonicalPath() );
		System.out.println("Return value of call to fileOne.canWrite() is: " + fileOne.canWrite());
		
		//try to create a FileWriter object to send data to the disk file. In order for this to work
		// a file must have been created on the disk. At this point, no file exists, so the next line
		// should throw an IOException
		
		FileWriter writerOne = new FileWriter(fileOne);
		
		System.out.println("End of program.");
		//housekeeping
		writerOne.close();
		
		
	}	//end main
}//end class