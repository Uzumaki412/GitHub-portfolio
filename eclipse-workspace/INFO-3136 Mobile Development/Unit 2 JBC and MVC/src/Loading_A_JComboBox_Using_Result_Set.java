/**
 * Program Name: Loading_A_JComboBox_Using_Result_Set.java
 * Purpose: shows how to take a result set and convert it to a DefaultComboBoxModel object
 *          that can be passed to a JComboBox constructor.
 *          NOTE: This version works just fine. In the second version, Loading_A_JComboBox_Using_Result_Set2.java,
 *          we modified the makeConnection() method from being VOID to actually having it return the  
 *          TableModel object. This caused a few problems. You can read about them in the second version's
 *          comment notes. 
 *          
 * Coder: Bill Pulling 
 * Date: July 21, 2025
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;

public class Loading_A_JComboBox_Using_Result_Set extends JFrame
{
    //CLASS WIDE SCOPE AREA
	//declare a model variable for the JComboBox that will be created in the BuildComboBoxModel_Utility class.
	//Making it static so that there is just one model object for the entire class.
	private static DefaultComboBoxModel<String> model ;
	
	//constructor	
	 public Loading_A_JComboBox_Using_Result_Set()	
	 {
		//courtesy call to super
		super("Loading A JComboBox from a ResultSet");		 
		//GUI Boilerplate			
		this.setLayout(new FlowLayout());
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//call the method that makes the connection, sends the query, and gets the model object
		// and assigns it to our statement
		Loading_A_JComboBox_Using_Result_Set.makeConnection();
		
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

	}//end constructor	 
	 
	/**
	 * Method Name: makeConnection()
	 * Purpose: makes a connection to the database and sends a query. Passes returned ResultSet
	 *          to BuildComboBoxModel_Utility to get a DefaultComboBoxModel object, which is then
	 *          passed to the constructor for a JComboBox.
	 * Accepts:nothing
	 * Returns: NOTHING! Void method.
	 * 
	 * NOTE: this method really violates the rule that a method should do just one thing, but do it 
	 *       very well. However, I just wanted to keep all the code in one place to make it a bit easier
	 *       to follow what is going on in the whole process of creating the model object that we use
	 *       to build the JComboBox
	 */
	public static void makeConnection()
	{
		//MAKE THE CONNECTION and pass it a query
		//standard boilerplate code often seen
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null; 
		 
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
		
		//Step 4: call the method in BuildComboBoxModel_Utility and pass it the 
		//         myRslt object. Catch the returned DefaultComboBoxModel object and
		//         assign it to our static variable called model
		
	   model = BuildComboBoxModel_Utility.resultSetToDefaultComboBoxModel(myRslt);
	  
		}//end try
		catch(SQLException ex)
		{
			System.out.println("SQL Exception, message is: " + ex.getMessage());
		}
		catch(Exception ex)
		{
			System.out.println("Some other Exception, message is: " + ex.getMessage());
		}
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
	}//end method
	
	public static void main(String[] args)
	{
		new Loading_A_JComboBox_Using_Result_Set();
	}//end main
	
}//end class