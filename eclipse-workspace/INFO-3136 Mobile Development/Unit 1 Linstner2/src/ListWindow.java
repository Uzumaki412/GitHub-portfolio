/**
 * Program Name: ListWindow.java
 * Purpose: shows how to create and populate a JList object so that the user
 *           can select an option from a drop-down list control. 
 *           This object requires us to add a fourth member to the three wise men,
 *           as the JList object event handler resides in the javax.swing.event package
 *           NOTE: we are going to start moving code out of the constructor in this example
 *           and delegate the building of JPanels to individual methods. 
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
//three wise men
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//the fourth member...now they can play euchre...
import javax.swing.event.*;

public class ListWindow extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private JPanel monthPanel, selectedMonthPanel;
	private JList <String> monthList;
	private JTextField selectedMonthTxt;
	private JLabel label;
	
	String[] monthsArray = {
			                     "Jan", "Feb","March", "April", "May","June",
			                     "July", "August", "Sept", "Oct", "Nov", "Dec"
							};
	
	//constructor
	public ListWindow()
	{
		//courtesy call to super
		super("Demo of a JList Component");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//prevents memory leak
		this.setSize(500,300);//width, height...change to desired dimensions.
		this.setLocationRelativeTo(null);//automatically centres our frame in the screen
		this.setLayout(new BorderLayout() );//REVISE TO desired layout manager
		
		//call the methods to build the individual JPanels
		this.buildMonthPanel();
		//now add it to the frame
		this.add(monthPanel, BorderLayout.CENTER);
		
		this.buildSelectedMonthPanel();
		this.add(selectedMonthPanel, BorderLayout.SOUTH);
		
		//Use pack() method to tell layout manager to reduce the screen footprint of the
		// app to the smallest possible size while maintaining readability and usability
		this.pack();
		//last line
		this.setVisible(true);
		
		
	}//end constructor
	
	//methods to build the JPanels
	
	/*
	 * Method Name: buildMonthPanel()
	 * Purpose: self-explanatory
	 * Accepts: NOTHING
	 * Returns: NOTHING! Void method that performs a service and builds a JPanel
	 */
	private void buildMonthPanel()
	{
		monthPanel = new JPanel();
		//create the JList and send it the monthsArray to load it with data
		monthList = new JList<String>(monthsArray);
		
		//use a Dimension object to hold height and width values. 
		Dimension dim = new Dimension(200,220);//sets width and height desired
		monthList.setPreferredSize(dim);
	
		//set the JList to SINGLE SELECTION MODE so only one item can be selected.
		monthList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//UNCOMMENT each line below to see alternat selection modes.
		//monthList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//monthList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//add a border
		monthList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//monthList.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.CYAN));
		//add the Jlist object to the monthPanel
		monthPanel.add(monthList);
		
		//register a listener after you build the INNER CLASS. 
		monthList.addListSelectionListener(new ListListener());//anonymous object of our inner class
		
	}//end buildMonthPanel()
	
	/*
	 * Method Name: buildSelectedMonthPanel()
	 * Purpose: self-explanatory
	 * Accepts: NOTHING
	 * Returns: NOTHING! Void method that performs a service and builds a JPanel
	 */
	private void buildSelectedMonthPanel()
	{
		selectedMonthPanel = new JPanel();
		label = new JLabel("You selected the month of: ");
		selectedMonthTxt = new JTextField(10);//sets width of the JTextField to 10 characters
		//add the objects to the JPanel
		
		selectedMonthPanel.add(label);
		selectedMonthPanel.add(selectedMonthTxt);
		
	}//end method
	
	//INNER CLASS GOES HERE
	private class ListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent ev)
		{
			//to be filled in during class
			//Retrieve the selected string value from the list.
			String selectedItem = monthList.getSelectedValue();
			
			//assign selectedItem to the JTextField
			selectedMonthTxt.setText(selectedItem);			
		}//end method
		
	}//end inner class
	

	public static void main(String[] args)
	{
		// anonymous object
		new ListWindow();

	}
	//end main
}
//end class