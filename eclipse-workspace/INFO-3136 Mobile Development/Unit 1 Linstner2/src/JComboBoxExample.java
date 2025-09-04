/**
 * Program Name: JComboBox.java
 * Purpose: shows how to create a JComboBox object and populate it from
 *          an array of values. Also shows how the user can enter a value of their own
 *          if none of the presented values are satisfactory.
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */

import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;//not used in this example
import javax.swing.event.*;

public class JComboBoxExample extends JFrame
{
	//CLASS WIDE SCOPE VARIABLES
	private JPanel coffeePanel;
	private JPanel selectedCoffeePanel;
	private JComboBox coffeeBox;//doing it here as a "raw" type. Normally we would <TYPE SAFETY> it 
	private JTextField selectedCoffeeTxt;
	private JLabel label;
	String[]coffeeArray = {
			                    "Regular","Dark Roast","Cappuccino","Espresso","Decaf"
												};
	//constructor
	public JComboBoxExample() 
	{
		//courtesy call
		super("Demo of a JComboBox  Component");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout() );
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		
		//call the methods to build the JPanels
		this.buildCoffeePanel();
		//NOW ADD IT TO THE JFrame
		this.add(coffeePanel, BorderLayout.CENTER);
		this.buildSelectedCoffeePanel();
		this.add(selectedCoffeePanel, BorderLayout.SOUTH);
		this.pack();
		//last line
		this.setVisible(true);		
	}//end constructor
	
	/*
	 * Method Name: buildCoffeePanel()
	 * Purpose: self-explanatory
	 * Accepts: NOTHING
	 * Returns: NOTHING: void method
	 */
	public void buildCoffeePanel()
	{
		coffeePanel = new JPanel();
		//create JList object and pass it the coffeeArray
		coffeeBox = new JComboBox(coffeeArray);
		Dimension dim = new Dimension(200,30);
		coffeeBox.setPreferredSize(dim);
		//set the combo box to editable so user can enter their own choice
		coffeeBox.setEditable(true);
		
		//REGISTER A LISTENER 
		coffeeBox.addActionListener(new ComboBoxListener() );
		coffeePanel.add(coffeeBox);
	}//end method
	/*
	 * Method Name: buildSelectedCoffeePanel()
	 * Purpose: self-explanatory
	 * Accepts: NOTHING
	 * Returns: NOTHING: void method
	 */
	public void buildSelectedCoffeePanel()
	{
		selectedCoffeePanel = new JPanel();
		label = new JLabel("You selected: ");
		selectedCoffeeTxt = new JTextField(30);//sets the width of the field to 30 characters
		//You can make this textField un-editable, so user cannot change what is in there.
		selectedCoffeeTxt.setEditable(false);
		
		//add the objects to the panel
		selectedCoffeePanel.add(label);
		selectedCoffeePanel.add(selectedCoffeeTxt);
	}//end method  
	//INNER CLASS for listener
	private class ComboBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//to be filled in during class
			//NOTE: since we did not TYPE-SAFETY the JComboBox to <String>, then 
			// the method getSelectedValue() returns an Object data type. 
			// THUS, we have do an EXPLICIT CAST of the returned selection to a String type
			String selectedCoffee = (String)coffeeBox.getSelectedItem();
			//write it to the text field
			selectedCoffeeTxt.setText(selectedCoffee);		
		}//end method
	}//end inner class
	
	public static void main(String[] args)
	{
		// anonymous object
		new JComboBoxExample();
	}	//end main
}//end class