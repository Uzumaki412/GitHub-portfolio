/**
 * Program Name: JTable_1.java
 * Purpose: shows what a JTable looks like when you add it directly 
 *          to the contentPane of a JFrame.
 *          REVISION: used the 2-arg constructor that accepts arrays to fill in
 *          some data and display it.
 *          
 *          REVISION #2: in order to see the column names, we actually have to put 
 *                       the JTable inside another sub-container object. In this case
 *                       we have to put the JTable INSIDE a JScrollPane.
 * Coder: Bill Pulling Sec01
 * Date: July 21, 2025
 */
import javax.swing.*;
import java.awt.*;


public class JTable_3 extends JFrame
{

	//CONSTRUCTOR
	public JTable_3()
	{
		//courtesy call
		super("First Example of JTable");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);//centres frame on screen
		this.setLayout(new FlowLayout());//sets the layout manager
		
		//create some arrays to hold data
		//first array holding data will be a 2D array of type Object
		Object[][] dataArray = {
				                     {"Pulling","Bill","CPA",1234567},
				                     {"Haworth","Tony","CPA",7654321}
														};
		//create single dimension array to hold the column names
		String[] colNamesArray = {"Surname", "First Name", "Program","ID#"};
		
		//REVISION:	
		//create JTable using two arg constructor that accepts arrays containing data
		JTable table = new JTable(dataArray,colNamesArray);
		table.setEnabled(false);
		//REVISION #2: add the JTable to a JScrollPane object
		JScrollPane scrollPane = new JScrollPane(table);
		
		//add the JScrollPane to content pane
		this.add(scrollPane);
		
		//last line
		this.setVisible(true);		
		
		//HEY! Where are the columnn names?
		//Answer: They're here now!
		
		
	}//end constructor	
	
	public static void main(String[] args)
	{
		// anonymous object
		new JTable_3();

	}//end main

}
//end class