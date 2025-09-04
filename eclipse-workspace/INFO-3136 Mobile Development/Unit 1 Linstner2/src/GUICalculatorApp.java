/**
 * Program Name: GUICalculatorApp.java
 * Purpose: First practical application to show the use of JTextFields for input and output.
 *  
 *          This app also shows how we can use separate JPanels set to different layouts to arrange the different
 *          components within our top level JFrame. We will usually add individual components to JPanels and then
 *          add just the JPanels to the JFrame. *          
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
//The Three Wise Men
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICalculatorApp extends JFrame
{
	//CLASS WIDE SCOPE AREA...declare objects whose events will be handled here
	// so that they are visible in the constructor and also down in the inner class
	JButton addBtn, subtractBtn, multiplyBtn, divideBtn, modBtn, clearBtn;
	JTextField firstValueFld,  secondValueFld, resultFld;
	
	//CONSTRUCTOR
	public GUICalculatorApp()
	{
		//call super 
		super(" A Basic GUI Calculator App");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set the top level JFrame container to a BorderLayout so that we will have 5 zones
		this.setLayout(new BorderLayout() );//ANONYMOUS object
		
		this.setSize(1500,1500);//setting this to really big area here, but we will show you how to use the
														//JFrame's pack() method later on to reduce the app's screen footprint to its smallest possible size
		//center it 
		this.setLocationRelativeTo(null);
		
		//create the three JPanels to act as sub containers
		JPanel topPanel = new JPanel();//layout will default to FlowLayout
		//create a JLabel for the top
		JLabel topLbl = new JLabel("Using Multiple JPanels to Hold Components Here");
		topPanel.add(topLbl);
		//add topPanel to the JFrame
		this.add(topPanel, BorderLayout.NORTH);
		
		//create centerPanel to hold JLabels and JTextFields for input
		JPanel centerPanel = new JPanel();//it will default to FlowLayout so we need to set it to GridLayout
		centerPanel.setLayout(new GridLayout(3,2,30,30) );
		centerPanel.setBackground(new Color(255,255,0));
		//add it to the frame
		this.add(centerPanel, BorderLayout.CENTER);
		
		//add components to center panel
		//NOTE: we'll demonstrate how to justify the labels to left, center, or right in the panel
		JLabel firstLbl = new JLabel("Enter First Value Here:");
		firstLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel secondLbl = new JLabel("Enter Second Value Here:");
		secondLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel resultLbl = new JLabel("Your result is:");		
		resultLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//now create the JTextFields that will be used to hold input and display the answer
		firstValueFld = new JTextField();
		secondValueFld = new JTextField();
		resultFld = new JTextField();
		
		//add them to the centerPanel in this order:
		centerPanel.add(firstLbl);
		centerPanel.add(firstValueFld);
		centerPanel.add(secondLbl);
		centerPanel.add(secondValueFld);
		centerPanel.add(resultLbl);
		centerPanel.add(resultFld);		
				
		//do the button panel
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout() );
		btnPanel.setBackground(new Color (0,255,0) );
		this.add(btnPanel, BorderLayout.SOUTH);
		
		//add the buttons to bottom panel
		addBtn = new JButton("Add");
		subtractBtn = new JButton("Subtract");
		multiplyBtn = new JButton("Multiply");
		divideBtn = new JButton("Divide");
		modBtn = new JButton("Mod%");
		clearBtn = new JButton("Clear");
		
		//add them to their container
		btnPanel.add(addBtn);
		btnPanel.add(subtractBtn);
		btnPanel.add(multiplyBtn);
		btnPanel.add(divideBtn);
		btnPanel.add(modBtn);
		btnPanel.add(clearBtn);
		
		//HIRE THE NANNY! Register a listener for the buttons
		ButtonListener nanny = new ButtonListener();//calling default constructor
		addBtn.addActionListener(nanny);
		
		
		//USe the pack() method to reduce the screen footprint to smallest possible area.
		this.pack();//packs the components into the smallest possible area.
		//LAST LINE
		this.setVisible(true);		
	}//end constructor
	
	//PUT INNER CLASS HERE
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			// Step 1: Any value in a JTextField is considered to be a String value. To use it for numeric values,
			//      get the values from the text fields using getText() method and then PARSE the Strings to 
			//         numeric double values using methods of the Double or Integer class. 
			String firstValueString = firstValueFld.getText();
			String secondValueString = secondValueFld.getText();
			//PARSE the String to a double using Double class method parseDouble()
			double firstValue = Double.parseDouble(firstValueString);
			double secondValue = Double.parseDouble(secondValueString);
			double result = 0;
			//determine which button was pushed using the getSource() method
			// of the ActionEvent object. This is different from our earlier JButton examples
			//where we used the getActionCommand() to read the label on the button.
			if(ev.getSource().equals(addBtn) )
			{
				result = firstValue + secondValue;
				//write result to the resultField. NOTE: the setText method expects a String argument, so if you just give it 'result'
				// you will get an error. The quick and dirty solution to this is to CONCATENATE the 'result' variable to an EMPTY STRING object.
				resultFld.setText("" + result);
			}
			//else if()		
			//TRY and FINISH FOR HOMEWORK...Implement the rest of the buttons to do subtract, mutilply, divide, and
			//modulus division, and also implement the clear button.
			
			//NOTE: for the 'Clear' Button, all three text fields should have an empty string assigned to them, and ideally, 
			//      the cursor should be returned to the firstValueField(). DO some research on how to set the focus to a 
			//      particular JTextField. 
		}//end actionPerformed	
  }//end inner class
	
	public static void main(String[] args)
	{
		// create an ANONYMOUS object of the class to get things rolling
		new GUICalculatorApp();
	}//end main
	
}//end class




