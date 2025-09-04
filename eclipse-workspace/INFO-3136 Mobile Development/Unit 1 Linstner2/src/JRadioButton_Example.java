/**
 * Program Name: JRadioButton_Example.java
 * Purpose: shows the use of JRadioButtons to ask the user to select one of several alternatives.
 *          This example shows the use of a ButtonGroup object to group the buttons into a LOGICAL
 *          GROUPING that will enforce MUTUAL EXCLUSIVITY, so that only one button can be selected.
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButton_Example extends JFrame
{
	
	//CLASS WIDE SCOPE AREA for components that will be referenced inside the INNER CLASS.
	private JTextField txtFld;
	private JRadioButton plainBtn, boldBtn, italicBtn, boldItalicBtn;
	private Font plainFont, boldFont, italicFont, boldItalicFont;
	
	//need a ButtonGroup object as the LOGICAL CONTAINER for the buttons to enforce 
	// MUTUAL EXCLUSIVITY
	private ButtonGroup btnGroup;	
	
	//constructor
	public JRadioButton_Example()
	{
		//courtesy call to super
		super("JRadioButton Demonstration");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//object becomes garbage when closed
		this.setSize(600, 100);//width and height of the JFrame in PIXELS
		this.setLocationRelativeTo(null);//centres our JFrame on the screen
		this.setLayout(new FlowLayout());//selects the LayoutManager object to be used.
		
		//create and add components
		txtFld = new JTextField("Watch the font style change here!");
		txtFld.setFont(new Font("Serif",Font.PLAIN,18) );// 18 is the font size in points
		this.add(txtFld);
		
		//create the JRadioButtons
		plainBtn = new JRadioButton("Plain",true);//the 'true' means this button is pre-selected
		                                          //when the GUI opens
		this.add(plainBtn);
		
		boldBtn = new JRadioButton("Bold", false);
		this.add(boldBtn);
		italicBtn = new JRadioButton("Italic", false);
		this.add(italicBtn);
		boldItalicBtn = new JRadioButton("Bold/Italic", false);
		this.add(boldItalicBtn);
		
		//NOW, add these buttons to a LOGICAL GROUPING to enforce MUTUAL EXCLUSIVITY so that only
		// one button can be activated at a time.
		btnGroup = new ButtonGroup(); // this is a LOGICAL CONTAINER, not a VISIBLE one!
		//add the buttons to the group

		btnGroup.add(plainBtn);
		btnGroup.add(boldBtn);
		btnGroup.add(italicBtn);
		btnGroup.add(boldItalicBtn);
	
		//create some Font objects to be used in the inner class
		plainFont = new Font("Serif", Font.PLAIN, 18);
		boldFont = new Font("Serif", Font.BOLD, 18);
		italicFont = new Font("Serif", Font.ITALIC, 18);
		boldItalicFont = new Font("Serif", Font.BOLD + Font.ITALIC, 18);
		
		//REGISTER LISTENER FOR EACH BUTTON...use an ANONYMOUS listener for each one
		plainBtn.addItemListener(new JRadioButtonNanny(plainFont) );
		boldBtn.addItemListener(new JRadioButtonNanny(boldFont) );
		italicBtn.addItemListener(new JRadioButtonNanny(italicFont) );
		boldItalicBtn.addItemListener(new JRadioButtonNanny(boldItalicFont) );	
		
		//last line
		this.setVisible(true);
	}//end constructor

	//INNER CLASS GOES HERE
	
	private class JRadioButtonNanny implements ItemListener
	{
		//data member
		Font font;
		
		//constructor to accept the Font object from the outer class constructor
		public JRadioButtonNanny(Font font)
		{
			this.font = font;
		}
		//override the actionPerformed() method
		public void itemStateChanged(ItemEvent ev)
		{
			//assign the Font object passed in to the txtFld object
			txtFld.setFont(font);
		}//end method
	}//end inner class

	
	public static void main(String[] args)
	{
		// anonymous object
		new JRadioButton_Example();

	}
	//end main
}
//end class