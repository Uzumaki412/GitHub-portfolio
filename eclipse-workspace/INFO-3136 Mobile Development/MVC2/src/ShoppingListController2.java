/**
 * Program Name: ShoppingListController2.java
 * Purpose: this class will act as the controller and get user input to be 
 *          displayed in the ShoppingListView GUI. 
 *          This class does double duty in that it provides the model object
 *          that holds the data. 
 *          ALSO, this class will also act as the LISTENER nanny. 
 *          
 *          REVISION NOTE: this is the controller for VERSION 2 of 
 *          the Shopping List app. T
 * Coder: Bill Pulling based on Tony Haworth's 2012 example
 * Date: Jul 15 2025   
 *       
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShoppingListController2 extends JFrame implements ActionListener
{
	//CLASS WIDE SCOPE AREA
	private static final long serialVersionIUID = 1L;
	
	//listener related components
	private JTextField textItem = new JTextField(20);
	private JButton addBtn = new JButton("Add");
	private JButton removeBtn = new JButton("Remove");
	private JButton viewBtn = new JButton("View");
	private JButton closeBtn = new JButton("Close");
	
	//REVISION> need a few more text fields for input
	
	private JTextField numberText = new JTextField(10);
	private JTextField itemText = new JTextField(20);
	private JTextField sizeText = new JTextField(20);
	private JTextField unitText = new JTextField(20);	
	
	
	//REVISION use the CUSTOMIZED ShoppingList2Model as the model object
	
	private ShoppingListModel2 theModel = new ShoppingListModel2();
	
	//REVSISON: change reference to the ShoppingListView2 class
	//declare a reference here to the ShoppingListView so that we can close the
	// view window when we close the controller window. 
	
	//REVISION July 17, 2018, set this to null 
	ShoppingListView2 viewer = null;
	
	//REVSISON: 
	//CONSTRUCTOR METHOD
	public ShoppingListController2() throws HeadlessException
	{
		//courtesy call to super class
		super("ShoppingList Controller GUI version 2");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//REVISION for extra text fields being added...change rows to 6 from 3
		this.setLayout(new GridLayout(6,2,10,10) );		
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		
		//add a JLabel to go beside textField
		this.add(new JLabel("Item Descriptor",JLabel.RIGHT) );//right justified label
		this.add(textItem);
		
		//REVISION: add the new labels and text fields here
		this.add(new JLabel ("Number: ", JLabel.RIGHT) );
		this.add(numberText);
		
		this.add(new JLabel ("Size: ", JLabel.RIGHT) );
		this.add(sizeText);
		
		this.add(new JLabel ("Units: ", JLabel.RIGHT) );
		this.add(unitText);		
		
		
		//add the buttons
		this.add(addBtn);
		this.add(removeBtn);
		this.add(viewBtn);
		this.add(closeBtn);
		
		//REGISTER LISTENERS...host class is doing double duty as the nanny here
		addBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		closeBtn.addActionListener(this);
		
		//PACK IT!
		this.pack();
		
		//last line
		this.setVisible(true);
		
	}//end constructor

	//FULFILL THE CONTRACT...implement actionPerformed()
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		// do an if-else to respond to each button
		
		if(ev.getSource() == addBtn)
		{
			//add the current item in the text field to the list model object
			if(textItem.getText().length() > 0)//don't want to add empty strings to list
			{
				//local variables
				double number = 0;
				double size = 0;
				try
				{
					number = Double.parseDouble(numberText.getText() );
				}
				catch(NumberFormatException ex)
				{
					System.out.println(ex.getMessage() );
				}//end catch
				
				//add the data to the model				
				theModel.addElement(number,textItem.getText(), sizeText.getText(),unitText.getText());
				//NOTE: when this line is executed, it fires a "list changed" event
			}
		}//end outer if
		
		else if(ev.getSource() == removeBtn)
		{
			if(textItem.getText().length() > 0)//again, look for empty strings
			{
				//REVISED CODE  to remove the row of data from the model
				//loop through each row to check for a match
				for(int i = 0; i < theModel.getSize();i++)
				{
					if(theModel.getElementAt(i).toLowerCase().contains(itemText.getText().toLowerCase()))
					{
						theModel.removeElement(i);
						//break;
						//adjust the loop counter inside the for loop
						--i;
					}					
				}//end for			
			}//end outer if
		}//end else
		
		else if(ev.getSource() == viewBtn)
		{
			//this is the event that opens up the viewer object so we can see
			// what's on the list. 
			
			//REVISION add the if not null conditional to prevent multiple copies of the view window being opened.
			//NOTE: this will prevent multiple views being produced, but if user closes the view using the close button
			// they will not be able to re-open it because the object will still exist in memory.
			if (viewer == null)
			{//NOTE: we need to pass the model object to the constructor
				viewer = new ShoppingListView2(theModel);
			}
			else
			{
				viewer.shoppingList.setModel(theModel);
			}
		}
		
		else if(ev.getSource() == closeBtn)
		{
			//close the controller and the viewer objects
			this.dispose();
			viewer.dispose();
			
		}
		
	}//end actionPerformed()
	
	
	public static void main(String[] args)
	{
		// REVISION: change to version 2anonymous object
		new ShoppingListController2();

	}//end main
}//end class
