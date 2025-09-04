/**
 * Program Name: JButton_Listener_Demo_5_With_Menu.java
 * Purpose: shows how to use a NESTED INNER CLASS to create a listener object that can then
 *          be registered as a listener for one or more source objects.
 *          
 *          REVISION: added a JMenuBar, a JMenu, and several JMenuItems
 *          to provide the user with an alternate way of changing the color.
 *           
 * Coder: Bill Pulling for Section 01
 * Date: July 8, 2025
 */
import java.awt.*; //the original GUI package from Java 1.0
import java.awt.event.*;// for handling event objects
import javax.swing.*;// the BIG GUI UPGRADE from JDK 1.2

public class JButton_Listener_Demo_5_With_Menu extends JFrame 
{
	//CLASS WIDE SCOPE AREA
	Container thisContentPane;
	
	//declare and create the menu components up here in class wide scope
	private JMenuBar menuBar = new JMenuBar();
	private JMenu colorMenu = new JMenu("Color");
	//five JMenuItems, one for each color
	private JMenuItem redItem = new JMenuItem("Red");
	private JMenuItem blueItem = new JMenuItem("Blue");
	private JMenuItem greenItem = new JMenuItem("Green");
	private JMenuItem blackItem = new JMenuItem("Black");
	private JMenuItem grayItem = new JMenuItem("Gray");
	
	
	//constructor
	public JButton_Listener_Demo_5_With_Menu()
	{
		super("Demo of using an INNER class WITH A MENU OPTION.");
		//BOILER PLATE CODE...seen in almost every GUI app
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout() );
		
		//create five JButtons with different color labels to change the background color of the content pane
		JButton redBtn = new JButton("Red");
		JButton blueBtn = new JButton("Blue");
		JButton greenBtn = new JButton("Green");
		JButton blackBtn = new JButton("Black");
		JButton greyBtn = new JButton("Gray");
		
		//add them to the container
		this.add(redBtn);
		this.add(blueBtn);
		this.add(greenBtn);
		this.add(blackBtn);
		this.add(greyBtn);
		
		//menuBar.setBackground(Color.CYAN);
		//REVISION: Add the menu items to the menu object
		colorMenu.add(redItem);
		colorMenu.add(blueItem);
		colorMenu.add(greenItem);
		colorMenu.add(blackItem);
		colorMenu.add(grayItem);
		
		//REVISION: add the JMenu object to the JMenuBar object
		menuBar.add(colorMenu);
		
		//NOW, this is the step that rookies screw up...
		//WE DO NOT ADD the JMenuBar to the contentPane. Uncomment line below to see what
		// it looks like if you add a JMenuBar to the contentPane instead of the JLayeredPane
	  //this.add(menuBar);
		// WE CALL the setJMenuBar() method to get it onto the JLayeredPane of the JFrame
		this.setJMenuBar(menuBar);		
		
		//CREATE a LISTENER OBJECT from the inner class here
		MaryPoppins nanny = new MaryPoppins();//calling the default constructor here....
		
		//REGISTER the listener or NANNY for each button.
		//The host class object is doing double duty as the listener here, so we can use 'this'.
		redBtn.addActionListener(nanny);
		blueBtn.addActionListener(nanny);
		greenBtn.addActionListener(nanny);
		blackBtn.addActionListener(nanny);
		greyBtn.addActionListener(nanny);
		
		//REVISION: register the nanny object as the listener for the JMenuItems as well
		redItem.addActionListener(nanny);
		blueItem.addActionListener(nanny);
		greenItem.addActionListener(nanny);
		blackItem.addActionListener(nanny);
		grayItem.addActionListener(nanny);
		
		
		//LAST LINE
		this.setVisible(true);
		
	}//end constructor
	

	public static void main(String[] args)
	{
		// create anonymous object to start things up
		new JButton_Listener_Demo_5_With_Menu();
		
	}	//end main

	//CREATE NESTED INNER CLASS HERE
	private class MaryPoppins implements ActionListener
	{
	
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			//here is where we put the code we want to run when a button is pushed.
			// We want to change the background color of the JFrame to whatever color is on the button label.
			//Step 1: get a reference to the contentPane of our JFrame
			
			//NOTE: we have to remove the 'this' reference here because this is in a different scope
			//REVISION : remove Container from this declaration BECAUSE we declared up
			// in class wide scope
			 thisContentPane = getContentPane();
			
			//Step 2: figure out which button was pushed and then execute appropriate code
			if(ev.getActionCommand().equals("Red") )
			{
				thisContentPane.setBackground(Color.RED);
			}
			else if(ev.getActionCommand().equals("Blue"))
			{
				thisContentPane.setBackground(Color.BLUE);
			}
			else if(ev.getActionCommand().equals("Green"))
			{
				thisContentPane.setBackground(Color.GREEN);
			}
			else if(ev.getActionCommand().equals("Black"))
			{
				thisContentPane.setBackground(Color.BLACK);
			}
			else if(ev.getActionCommand().equals("Gray"))
			{
				//default gray of a JFrame is pretty close to (240,240,240)
				//We will create an ANONYMOUS Color object using these values. 
				thisContentPane.setBackground(new Color(240,240,240));
			}
			
		}//end actionPerformed()	
		
	}//end inner class
	
}//end class