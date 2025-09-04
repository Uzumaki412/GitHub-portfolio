/**
 * Program Name: WorldCitiesViewer.java
 * Purpose: This is a view of WorldCities Application. 
 * Simply display result set of query. This is the VIEW portion of the
 * MVC set up
 * Coder: Bill P.
 * Date: July 21, 2025
 */

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class WorldCitiesViewer extends JFrame
{
	//data member for the class. Create an empty JTable here.
	public JTable table = new JTable();
	
	//constructor takes a TableModel object as an argument. This
	// will be passed in from the Controller part. 
	public WorldCitiesViewer(TableModel model)
	{
		super("Cities of the World");
		
		//boilerplate
		this.setLayout(new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		
		//Pass to the empty JTable the TableModel object that is the parameter of this method
		// by using the setModel() method.
		table.setModel(model);
		
		//create a JScrollPane so we can see the column names on the table
		JScrollPane scrollPane = new JScrollPane(table);		
		this.add(scrollPane);
		
		//last line
		this.setVisible(true);
		
	}//end cons
}
