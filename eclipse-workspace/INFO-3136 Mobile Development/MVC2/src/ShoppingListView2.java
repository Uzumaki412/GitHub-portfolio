/**
 * Program Name: ShoppingListView2.java
 * Purpose: another example of use of MVC. In this app we will
 *          separate view class from the model class
 * Coder: Bill Pulling for Sec02, adapted from Tony Haworth's 2012 example
 * 
 *       REVISION NOTE: this is the viewer for VERSION 2 of Shopping List app, uses the Oreo model or modified MVC
 *         
 * Date: Jul 15 2025 
 */
//three wise men
import javax.swing.*;
import javax.swing.event.ListDataListener;

import java.awt.*;
import java.awt.event.*;

public class ShoppingListView2 extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	//for sizing purposes to size the JFrame
	private static final double FRAME_WIDTH_FACTOR = 0.25;
	private static final double FRAME_HEIGHT_FACTOR = 0.5;
	
	//listener related object needs to be here in CLASS SCOPE AREA
	//REVSISON: July 17, 2018...made it public so it visible over in controller class
	public JList<String> shoppingList = new JList<String>();
	
	//constructor method..accepts a ListModel object
	public ShoppingListView2(ListModel<String> model) throws HeadlessException
	{
		super("Shopping List Viewer Version 2");
		//boilerplate		
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);			
			this.setLayout(new BorderLayout() );			
			//Variation: using the current dimensions of your screen resolution
			this.setSize(					
					(int)(this.getToolkit().getScreenSize().width * FRAME_WIDTH_FACTOR), 
					(int)(this.getToolkit().getScreenSize().height * FRAME_HEIGHT_FACTOR)					
					);
			
			this.setLocationRelativeTo(null);
			
			//add the JList component to the frame
			this.add(shoppingList, BorderLayout.CENTER);
			
			//the magic line...assign the MODEL object to the shopping list			
			shoppingList.setModel(model);//this connects the model and the view.
			// Now if the model changes, the view will be updated here when the processEvent() method
			// in the ShopppingListModel2 class is run. 
			
			//the last line
			this.setVisible(true);
	}//end constructor
	
	
	
}//end class
