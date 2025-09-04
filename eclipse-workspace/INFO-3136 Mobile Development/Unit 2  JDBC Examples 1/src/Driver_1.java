/**
 * Program Name: Driver_1.java
 * Purpose: shows steps involved in making a connection to a back-end database such as MySQL using the 
 *         appropriate JDBC driver package to make the connection.
 * Coder: Bill Pulling for Sec 04
 * Date: July 21, 2025
 */
//The Dynamic Duo
import java.sql.*;
import javax.sql.*;
public class Driver_1
{

	public static void main(String[] args)throws SQLException
	{
		// standard JDBC BOILERPLATE code seen in almost all JDBC apps
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
    			// end of standard JDBC BOILERPLATE code
    //Use a try catch block to hold our code
    try
    {
    	//Step 1: create a Connection object by calling a static method of the DriverManager class
    	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true",
    			"root", "password");
    	
    	//Step 2: now use the Connection method createStatement to create a Statement object
    	myStmt = myConn.createStatement();
    	
    	//Step 3: use myStmt to send in an SQL query to database and catch the returned ResultSet object
    	myRslt = myStmt.executeQuery("SELECT * FROM employees");
    	
    	//Step 4: process the ResultSet object using a while loop
    	while(myRslt.next() )
    	{
    		System.out.println(myRslt.getString("Last_Name") +
    				               ", " + myRslt.getString("first_name") +
    				               ", "  + myRslt.getString("email")	
    				); //close for System.out.println
    	}//end while
    					
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
