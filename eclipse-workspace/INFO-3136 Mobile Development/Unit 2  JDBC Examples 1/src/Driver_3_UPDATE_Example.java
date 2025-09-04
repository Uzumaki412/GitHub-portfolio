/**
 * Program Name: Driver_3_UPDATE_Example.java
 * Purpose: shows how to run an UPDATE statement to change an existing row in the table.
 * Coder: Bill Pulling for Sec 04
 * Date: July 21, 2025
 */
//The Dynamic Duo
import java.sql.*;
import javax.sql.*;
public class Driver_3_UPDATE_Example
{

	public static void main(String[] args)throws SQLException
	{
		// standard JDBC BOILERPLATE code seen in almost all JDBC apps
		Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRslt = null;
    
    //Use a try catch block to hold our code
    try
    {
    	//Step 1: create a Connection object by calling a static method of the DriverManager class
    	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true",
    			"root", "password");
    	
    	//Step 2: now use the Connection method createStatement to create a Statement object
    	myStmt = myConn.createStatement();
    	
    	//Step 3: REVISION: create a String to hold the UPDATE statement
    	
    	String updateString = "UPDATE employees " +
    	                      "SET email = 'dwilliams@gmail.com' " +
    			                  "WHERE id = 4 " ;
    	//now use your myStmt object to run it, and catch the returned integer value sent back by the DB. 
    	
    	int returnedValue = myStmt.executeUpdate(updateString);
    	
    	
    	//Step 4: output results of operation
    	System.out.println("Update was completed, returned value is " + returnedValue);
    	System.out.println("Run Driver_1 again to see the UPDATED row, or look at it in MySQL Workbench.");
    					
    }
    catch(Exception ex)
    {
    	System.out.println("Exception caught, message is: " + ex.getMessage() );
    	ex.printStackTrace();
    }
    finally
    {
    	//close objects in reverse order of creation
    	if(myRslt != null)
    		myRslt.close();
    	if(myStmt != null)
    		myStmt.close();
    	if(myConn != null)
    		myConn.close();
    }
    System.out.println("\nEnd of program.");
    
    
	}
	//end main
}
//end class
