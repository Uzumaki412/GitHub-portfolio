/**
 * Program Name: Loading_A_JComboBox_Using_Result_Set.java
 * Purpose: shows how to take a result set and convert it to a DefaultComboBoxModel object
 *          that can be passed to a JComboBox constructor. *
 *                    
 *          REVISION: we changed the makeConnection() method from a VOID method so that it now returns
 *          the ResultSet object.
 *          
 *          NOTE: this change caused a problem (NullPointerException was thrown), and it was not working properly...
 *          There is a problem with returning a ResultSet object if the object is closed
 *          in the finally block. We cannot return a closed object. We get a NullPointerException.
 *           
 *          SOLUTION: We moved the try-catch code in the finally block and put it at the end of
 *          the constructor method. Then we can have makeConnection() return the ResultSet object, 
 *          and use it to get the DefaultComboBoxModel. Then we can run the try-catch code that was in the
 *          finally block at the end of the constructor method so that we properly close off the JDBC objects.
 *          
 * Coder: Bill Pulling Sec04
 * Date: July 21, 2025
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;

public class Loading_A_JComboBox_Using_Result_Set2 extends JFrame
{
    //CLASS WIDE SCOPE AREA
	//declare a model variable for the JComboBox that will be created in the BuildComboBoxModel_Utility class.
	//Making it static so that there is just one model object for the entire class.
	private static DefaultComboBoxModel<String> model ;
	
	//standard boilerplate code often seen. Making them static so we have only one of each in the class
	private static Connection myConn = null;
	private static Statement myStmt = null;
	private static ResultSet myRslt = null; 
	
	//constructor	
	 public Loading_A_JComboBox_Using_Result_Set2()	
	 {
		//courtesy call to super
		super("Loading A JComboBox from a ResultSet");		 
		//GUI Boilerplate			
		this.setLayout(new FlowLayout());
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//call the method that makes the connection, sends the query, and gets the model object
		// and assigns it to our static variable called model.
		ResultSet myRslt = Loading_A_JComboBox_Using_Result_Set2.makeConnection();
		
		//now get the model object by calling the method in the utility class
		model = BuildComboBoxModel_Utility.resultSetToDefaultComboBoxModel(myRslt);
		
		//Now that we have the model object from the utility, we can finish the GUI by creating the 
		// JComboBox and passing it the model object returned from the utility
		JLabel label = new JLabel("Drop the list to see authors:");
		this.add(label);
		JComboBox<String> cBox = new JComboBox<String>(model);
		cBox.setEditable(true);
		cBox.setSize(100, 100);
		this.add(cBox);
		
		//last line
		this.setVisible(true);
		
		//now close the connection objects here, since we've used the ResultSet object to get what
		// we wanted...a DefaultComboBoxModel object.
		try
		{
			if(myRslt != null)
			  myRslt.close();
			if(myStmt != null)
			  myStmt.close();
			if(myConn != null)
			  myConn.close();	
		}//end try
		catch(SQLException ex)
		{
			System.out.println("SQLException while closing connection objects: "+ ex.getMessage());
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(myRslt != null)
				  myRslt.close();
				if(myStmt != null)
				  myStmt.close();
				if(myConn != null)
				  myConn.close();	
			}//end try
			catch(SQLException ex)
			{
				System.out.println("SQLException INSIDE finally block: "+ ex.getMessage());
				ex.printStackTrace();
			}
		}
	}//end constructor	 
	 
	/**
	 * Method Name: makeConnection()
	 * Purpose: makes a connection to the database and sends a query. Passes returned ResultSet
	 *          to BuildComboBoxModel_Utility to get a DefaultComboBoxModel object, which is then
	 *          passed to the constructor of for a JComboBox.
	 * Accepts:nothing
	 * Returns: a ResultSet object that will be used to create a DefaultComboBoxModel object
	 * 
	 * NOTE: this method really violates the rule that a method should do just one thing, but do it 
	 *       very well. However, I just wanted to keep all the code in one place to make it a bit easier
	 *       to follow what is going on in the whole process of creating the model object that we use
	 *       to build the JComboBox
	 */
	public static ResultSet  makeConnection()
	{
		try
		{
		//step 1: create the Connection object by calling getConnection() method
		// of DriverManager class
		myConn = DriverManager.getConnection(
					         "jdbc:mysql://localhost:3306/INFO3136_Books?useSSL=false&allowPublicKeyRetrieval=true", 
					                       "root","password");		
		//step 2: create statement object
		myStmt = myConn.createStatement();
		
		//step 3: pass in a query and execute it and catch the returned ResultSet
		myRslt = myStmt.executeQuery("SELECT last_name,first_name FROM Author");
		
		}//end try
		catch(SQLException ex)
		{
			System.out.println("SQL Exception, message is: " + ex.getMessage());
		}
		catch(Exception ex)
		{
			System.out.println("Some other Exception, message is: " + ex.getMessage());
		}
		
		//REMOVE THE FINALLY BLOCK FROM HERE and close the JDBC objects after 
		//we're done using the ResultSet object to get a model object for the JComboBox constructor method
		/*
		finally
		{
			//clean up code if bad things start to happen. This code ALWAYS runs.					
			try
			{
				if(myRslt != null)
				  myRslt.close();
				if(myStmt != null)
				  myStmt.close();
				if(myConn != null)
				  myConn.close();	
			}//end try
			catch(SQLException ex)
			{
				System.out.println("SQLException INSIDE finally block: "+ ex.getMessage());
				ex.printStackTrace();
			}
		}//end finally
		*/
		//Return the ResultSet object to the calling line
		return myRslt;
		
	}//end method
	
	public static void main(String[] args)
	{
		new Loading_A_JComboBox_Using_Result_Set2();
	}//end main
	
}//end class