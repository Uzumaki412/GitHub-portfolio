/**
 * Program Name: ShoppingListController.java
 * Purpose: this is the COMBINED Controller and Model class of the app. The controller will 
 *          provide a GUI for user input and will pass the info on to the model object.
 *          This model object will then be sent to the View part of the app in another class. 
 *          NOTE: the host class Jframe object will do double duty as the event listener for 
 *          the JButtons.
 * Coder: BP
 * Date: Jul 15 2025
 */
/**
 * imports...three wise men
 */
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class ShoppingListController extends JFrame implements ActionListener
{
  //CLASS WIDE SCOPE AREA
	private static final long serialVersionUID = 1L;
	
	//listener related components
	private JTextField textItem = new JTextField(20);
	private JButton addBtn = new JButton("Add");
	private JButton removeBtn = new JButton("Remove");
	private JButton viewBtn = new JButton("View");//opens the view part of the app.
	private JButton  closeBtn = new JButton("Close");//closes both windows of the app.
	
	//create a DefaultListModel object to hold the data input by the user
	private DefaultListModel<String> theModel = new DefaultListModel<String>();
	
	//create a reference to the ShoppingListView object so that we can close it from here.
	ShoppingListView viewer;
	
	//CONSTRUCTOR METHOD
	public ShoppingListController() throws HeadlessException
	{
		super("ShoppingList Controller GUI");
		//boilerplate
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//destroy this object when close button is pressed
		this.setSize(350, 300); //width and height in pixels
		this.setLocationRelativeTo(null);//centres the JFrame on the screen.
		this.setLayout(new GridLayout(3,2,10,10));//3 rows, 2 columns
		
		//add components
		this.add(new JLabel("Item Description:", JLabel.RIGHT) );
		this.add(textItem);
		this.add(addBtn);
		this.add(removeBtn);
		this.add(viewBtn);
		this.add(closeBtn);
		
		//register listeners for each button
		addBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		closeBtn.addActionListener(this);
		this.pack();
		
		//LAST LINE!
		this.setVisible(true);		
		
	}//end constructor

	@Override
	public void actionPerformed(ActionEvent ev)
	{
		//use if-else block to determine which button was pushed.
		if(ev.getSource() == addBtn)
		{
			//check to make sure user has actually entered a string in the text field
			if(textItem.getText().length() > 0)
			{
				//add the string to the DefaultListModel
				theModel.addElement(textItem.getText());
			}
		}
		else if(ev.getSource() == removeBtn)
		{
			//check to make sure user has actually entered a string in the text field
			if(textItem.getText().length() > 0)
			{
				//REMOVE the string from the DefaultListModel
				theModel.removeElement(textItem.getText());
			}
		}
		else if(ev.getSource() == viewBtn)
		{
			//start up a viewer object and pass it the DefaultListModel object 
			viewer = new ShoppingListView(theModel);
		}
		else if(ev.getSource() == closeBtn)
		{
			//close both windows by calling the dispose() method
			this.dispose();
			viewer.dispose();
		}
		
		
	}//emnd actionPerformed
	
	public static void main(String[] args)
	{
		// anonymous object
		new ShoppingListController();
	}//end main
}//end class