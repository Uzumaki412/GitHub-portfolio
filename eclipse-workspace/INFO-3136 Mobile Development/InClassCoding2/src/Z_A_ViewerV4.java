/**
 * Program Name: Z_A_ViewerV4.java
 * Purpose: This is the view part of  Movie Category and Rating Viewer Application. 
 *  Programmer: Zaid Abu Shawairb			
 * Date: July 30, 2025
 */

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class Z_A_ViewerV4 extends JFrame
{
	public JTable table = new JTable();
	
	//constructor
	public Z_A_ViewerV4(TableModel model)
	{
		super("Movies and their Rating");
		
		//boilerplate
		this.setLayout(new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		
		//create the JTable and pass it the TableModel object that is the parameter of this method
		table.setModel(model);
		
		//create a JScrollPane so we can see the column names on the table
		JScrollPane scrollPane = new JScrollPane(table);		
		this.add(scrollPane);
		
		//last line
		this.setVisible(true);
		
	}//end cons
}
