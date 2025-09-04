/**
 * Program Name: JTable_1.java
 * Purpose: shows what a JTable looks like when you add it directly 
 *          to the contentPane of a JFrame.
 * Coder: Bill Pulling Sec01
 * Date: July 21, 2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTable_1 extends JFrame
{

	//CONSTRUCTOR
	public JTable_1()
	{
		//courtesy call
		super("First Example of JTable");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);//centres frame on screen
		this.setLayout(new FlowLayout());//sets the layout manager
		
		//create JTable using two arg constructor for rows, columns
		JTable table = new JTable(5,5);
	
		//add directly to content pane
		this.add(table);
		
		//last line
		this.setVisible(true);		
		
		
	}//end constructor	
	
	public static void main(String[] args)
	{
		// anonymous object
		new JTable_1();

	}//end main
}//end class