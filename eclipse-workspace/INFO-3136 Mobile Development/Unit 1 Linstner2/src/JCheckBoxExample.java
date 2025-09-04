/**
 * Program Name: JCheckBoxExample.java
 * Purpose: show the use of the JCheckBox control as a user input component. 
 *          This will also introduce a new listener interface called ItemListener, which is designed to work with
 *          single selection type of controls. 
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */

import java.awt.*; //the original GUI package from Java 1.0
import java.awt.event.*;// for handling event objects
import javax.swing.*;// the BIG GUI UPGRADE from JDK 1.2
import javax.swing.event.*;

public class JCheckBoxExample extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private JTextField txtFld;
	private JCheckBox boldChkbx, italicChkbx;
	
	//CONSTRUCTOR
	public JCheckBoxExample()
	{
		//courtesy call to super
		super("JCheckBox Demonstration");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout() );
		
		//install components
		txtFld = new JTextField("Watch the font style change here!");
		txtFld.setFont(new Font("Serif", Font.PLAIN, 18) );//Font object is ANONYMOUS here.
		this.add(txtFld);
		
		//create two check boxes
		boldChkbx = new JCheckBox("Bold");
		italicChkbx = new JCheckBox("Italic");
		
		this.add(boldChkbx);
		this.add(italicChkbx);
		
		//REGISTER THE LISTENERS
		//create a Nanny object
		CheckBoxNanny nanny = new CheckBoxNanny();
		//assign the nanny
		boldChkbx.addItemListener(nanny);
		italicChkbx.addItemListener(nanny);
		
		//last line
		this.setVisible(true);
	}//end constructor
	
	//INNER CLASS GOES HERE
	private class CheckBoxNanny implements ItemListener
	{
		//data member
		Font font;
		
		//implement the method of the interface to FULFILL the contract
		public void itemStateChanged(ItemEvent ev)
		{
			//determine which check box was changed
			if(boldChkbx.isSelected() && italicChkbx.isSelected() )
			{
				font = new Font("Serif", Font.BOLD + Font.ITALIC, 18);				
			}
			else if (boldChkbx.isSelected())
			{
				font = new Font("Serif", Font.BOLD, 18);		
			}
			else if(italicChkbx.isSelected())
			{
				font = new Font("Serif", Font.ITALIC, 18);	
			}
			else
			{
				font = new Font("Serif", Font.PLAIN, 18);	
			}
			//SET THE FONT in the JTextField
			txtFld.setFont(font);
			
			
		}//end method
	}//end inner class	
	
	
	public static void main(String[] args)
	{
		// anonymous object
		new JCheckBoxExample();
	}//end main
}
//end class