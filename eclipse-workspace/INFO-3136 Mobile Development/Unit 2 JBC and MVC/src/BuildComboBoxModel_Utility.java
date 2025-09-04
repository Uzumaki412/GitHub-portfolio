/**
 * Program Name: BuildComboBoxModel_Utility.java
 * Purpose: a utility program to build a DefaultComboBox model object that can then
 *          be passed to the constructor of a JComboBox.
 *          Used in conjunction with the Loading_A_JComboBox_Using_Result_Set versions 1 and 2.
 * Coder: Bill Pulling Sec04
 * Date: July 21, 2025
 */
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

import java.util.Vector; 

public class BuildComboBoxModel_Utility
{
  /**Method Name: resultSetToDefaultComboBoxModel(ResultSet rs) 
   * Purpose: a public class method that takes a ResultSet object from a query and 
   *          converts it into a DefaultComboBoxModel object that can then be passed to 
   *          a JComboBox constructor to create a view of the data.
   * Accepts: A ResultSet object obtained from a JDBC query of a database
   * Returns: A DefaultComboBoxModel<String> object that has been type safetied to String
   */	
	
	public static DefaultComboBoxModel<String> resultSetToDefaultComboBoxModel(ResultSet rs)
	{
		//DATA MEMBER HERE
		DefaultComboBoxModel<String> model = null;
		
		try
		{
			//create the vector to hold the string elements
			Vector<String> names = new Vector<String>();
			
			//loop through result set and add each surname and first name to the vector
			while(rs.next())
			{
				//concatenate the data from the two columns and separate with a comma
				//NOTE: Using the column_name version of the getString() method here rather than column index numbers
				names.add(rs.getString("Last_name") + "," + rs.getString("first_Name"));
			}//end while
			
			//now pass the vector to the constructor for the DefaultComboBoxModel object
			 model = new DefaultComboBoxModel<String>(names); 
			
		}//end try
		catch(Exception ex)
		{
			System.out.println("Exception caught, message is: " + ex.getMessage());
			ex.printStackTrace();
		}
		//IF no exception was caught, then return the model to the calling line
		return model;		
	}//end method
	
}//end class