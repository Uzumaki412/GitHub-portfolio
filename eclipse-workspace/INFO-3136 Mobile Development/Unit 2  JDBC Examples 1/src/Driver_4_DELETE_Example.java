/**
 * Program Name: Driver_4_DELETE_Example.java
 * Purpose: shows how to run a DELETE statement to reomve one or more existing rows in the table.
 * Coder: Bill Pulling for Sec 04
 * Date: July 21, 2025
 */
//The Dynamic Duo
import java.sql.*;
import javax.sql.*;
public class Driver_4_DELETE_Example
{

	public static void main(String[] args)throws SQLException
	{
		// standard JDBC BOILERPLATE code seen in almost all JDBC apps
		Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRslt = null;
    
    //REVISION: separate the components of the connection string into three separate string variables
    String connString = "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true";
    String userName = "root";
    String password = "password";
    
    
    //Use a try catch block to hold our code
    try
    {
    	//Step 1: create a Connection object by calling a static method of the DriverManager class
    	//REVISION:
    	myConn = DriverManager.getConnection(connString, userName, password);
    	
    	//Step 2: now use the Connection method createStatement to create a Statement object
    	myStmt = myConn.createStatement();
    	
    	//Step 3: REVISION: create a String to hold the DELETE statement
    	
    	String deleteString = "DELETE FROM employees " +
    			                  "WHERE last_name = 'Brown' " ;
    	//now use your myStmt object to run it, and catch the returned integer value sent back by the DB. 
    	
    	int returnedValue = myStmt.executeUpdate(deleteString);
    	
    	
    	//Step 4: output results of operation
    	System.out.println("DELETE was completed, returned value is " + returnedValue);
    	System.out.println("Run Driver_1 again to see the table without the deleted rows, or look at it in MySQL Workbench.");
    					
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
