/**
 * Program Name:JOptioPane_Demo_1.java
 * Purpose: shows the creation of a JOptionPaneInputDialog box inside of a JFrame.
 *          Each of the four types will be displayed by clicking a JButton
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
import java.awt.*; //the original GUI package from Java 1.0
import java.awt.event.*;// for handling event objects
import javax.swing.*;// the BIG GUI UPGRADE from JDK 1.2
public class JOptioPane_Demo_1 extends JFrame
{
	//class wide scope
	JButton confirmDialogBtn,inputDialogBtn,messageDialogBtn,internalDialogBtn,optionDialogBtn;
	//constructor
	Container contentPane;
	JOptioPane_Demo_1()
	{
	super("Demo of JOptionPane Dialog Box Types...");
	//BOILER PLATE CODE...seen in almost every GUI app
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(600, 200);
	this.setLocationRelativeTo(null);
	this.setLayout(new FlowLayout());
	//this.setLayout(new GridLayout(5,1,10,10) );
	
	//four JButtons
	confirmDialogBtn = new JButton("showConfirmDialog");
	this.add(confirmDialogBtn);
	
	inputDialogBtn = new JButton("showInputDialog");
	this.add(inputDialogBtn);
	
	messageDialogBtn = new JButton("showMessageDialog");
	this.add(messageDialogBtn);
	
	internalDialogBtn = new JButton("showInternalMessageDialog");
	this.add(internalDialogBtn);
	optionDialogBtn = new JButton("showOptionDialog");
	this.add(optionDialogBtn);
	
	//REGISTER LISTENERS
	ButtonNanny nanny = new ButtonNanny();
	confirmDialogBtn.addActionListener(nanny);
	inputDialogBtn.addActionListener(nanny);
	messageDialogBtn.addActionListener(nanny);
	internalDialogBtn.addActionListener(nanny);
	optionDialogBtn.addActionListener(nanny);
	
	//last line
	this.setVisible(true);
	}//end constructor

	//put INNER CLASS HERE
	private class ButtonNanny implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent ev)
		{
			// get the button
			if(ev.getSource() == confirmDialogBtn)
			{
				JOptionPane.showConfirmDialog(contentPane, "Confirm your choice");
			}
			else if(ev.getSource() == inputDialogBtn)
			{
				JOptionPane.showInputDialog(contentPane, "Enter your choice here...");
			}
			else if(ev.getSource() == messageDialogBtn)
			{
				JOptionPane.showMessageDialog(contentPane, "This is a message dialog box");
			}
			else if(ev.getSource() == internalDialogBtn)
			{
				JOptionPane.showInternalMessageDialog(contentPane, "Internal Message Dialog box");
			}
			else if(ev.getSource() == optionDialogBtn)
			{
				//do this one yourself...plug in some values for the null parameters according to 
				// the JDK docs.
				JOptionPane.showOptionDialog(contentPane, "Option Dialog Example", "Bill's Option Pane", 1, 1, null,null,null);
			}
			
		}//end actionPerformed
		
	}//end inner class
	
	
	public static void main(String[] args)
	{
		// anonymous object
		new JOptioPane_Demo_1();

	}
	//end main
}
//end class