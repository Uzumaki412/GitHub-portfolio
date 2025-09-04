/**
 * Program Name: Driver_5_PreparedStatement_Example.java
 * Purpose: shows how to use a PreparedStatment object that has place holders for values that will be entered by user.
 *          It is kind of like a method to run a query that can accept arguments from the user.
 * Date: July 21, 2025
 */
//The Dynamic Duo
import java.sql.*;
import javax.sql.*;
public class Driver_5_PreparedStatement_Example
{

	public static void main(String[] args)throws SQLException
	{
		// standard JDBC BOILERPLATE code seen in almost all JDBC apps
		Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRslt = null;
    //REVISION: add the PreparedStatement object
    PreparedStatement myPrepStmt = null;
    
    
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
    	
    	//Step 2: now use the Connection method prpeareStatement to create a PreparedStatement object, and pass it the 
    	//        SQL code to be run
    	myPrepStmt = myConn.prepareStatement("SELECT * FROM Employees " +
    	                                     "WHERE salary > ? AND "  +
    			                                 " Department = ? "	);
    	
    	//to save time, just hard code some values into the parameters
    	myPrepStmt.setDouble(1, 50000);
    	myPrepStmt.setString(2, "HR");
    	
    	//execute the prepared statement
    	myRslt = myPrepStmt.executeQuery();
    	
    	//Step 3: now call the displayResults() method to output the data.
    	displayResults(myRslt);    					
    }//end try
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
    	if(myPrepStmt != null)
    		myPrepStmt.close();
    	if(myConn != null)
    		myConn.close();
    }
    System.out.println("\nEnd of program.");
    
    
	}	//end main
	
	/**Method Name: displayResults()
	 * Purpose: displays the rows in the CURSOR returned from the query
	 * Accepts: a ResultSet object from a query
	 * Returns: NOTHING! Void method.
	 */
	public static void displayResults(ResultSet rs)throws SQLException
	{
		while(rs.next() )
		{
			//use column index values to retrieve the data
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			double salary = rs.getDouble(6);
			String department = rs.getString(5);
			//use a printf to format the output
			
			System.out.printf("%s, %s, %.2f, %s \n", lastName, firstName, salary, department);
			
		}//end while
	}//end method















}//end class

