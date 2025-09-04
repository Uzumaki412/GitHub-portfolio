/**
 * Program Name: ShoppingListView.java
 * Purpose: This is the view part of the ShoppingList app.
 * Coder: BP
 * Date: Jul 15 2025
 */

/**
 * 
 */
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class ShoppingListView extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private static final long serialVersionUID = 1L;
	
	//some code to help us size the frame to 25% of screen width and 50% of screen height
	private static final double FRAME_WIDTH_FACTOR = 0.25; //25% of screen width
	private static final double FRAME_HEIGHT_FACTOR = 0.50;//50% of screen height
	
	//listener related objects
	private JList<String> shoppingList = new JList<String>();
	
	//CONSTRUCTOR: takes one argument, the DefaultListModel object from the controller class
	public ShoppingListView(DefaultListModel<String> model)
	{
		super("Shopping List Viewer");
		//boilerplate
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//destroy this object when close button is pressed
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		this.setSize(
				(int)(this.getToolkit().getScreenSize().width * FRAME_WIDTH_FACTOR ),
				(int)(this.getToolkit().getScreenSize().height * FRAME_HEIGHT_FACTOR )				
				); //width and height in pixels
		
		//add the JList to the frame
		this.add(shoppingList, BorderLayout.CENTER);
		
		//THE MAGIC LINE! We assign the DefaultListModel object that was passed in to the constructor
		// to the JList object. This will create a connection between the two objects. If the DefalultListModel changes
		// then the JList will be updated and the view will change. 
		shoppingList.setModel(model);
		
		
		//LAST LINE
		this.setVisible(true);
		
	}//end constructor	
	
}//end class
















