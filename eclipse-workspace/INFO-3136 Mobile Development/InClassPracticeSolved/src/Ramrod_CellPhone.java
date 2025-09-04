/**
 * Program Name: Ramrod_CellPhone.java
 * Purpose: simulates a cell phone able to make a call or send a text message
 * Coder: Kyle Nafekh and Eric Odette for Sec02 - Student # 0723272
 * Date: Apr 7, 2015
 */

//import GUI elements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ramrod_CellPhone extends JFrame
{
	//class scope
	private JTextArea screen; //screen area
	private JPanel keypad; //keypad area
	private boolean message = false, buttonPress = false; //flags to keep track of texting mode and first button press
	private Font normal, bold; //font objects
	private int buttonCount = 0; //counter to indicate when a full phone number has been entered
	
	//buttons in class scope
	private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonFont, buttonClear, buttonSend, buttonText, buttonEnd;
	
	
	//constructor
	public Ramrod_CellPhone()
	{
		super("Cell Phone"); //courtesy call
		
		//boilerplate code
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(225,500);
		this.setLocationRelativeTo(null);
		
		//set up keypad
		keypad = new JPanel();
		keypad.setLayout(new GridLayout(5, 3));
		
		//construct buttons
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		button0 = new JButton("0");
		buttonFont = new JButton("Font");
		buttonClear = new JButton("Clear");
		buttonSend = new JButton("Send");
		buttonText = new JButton("Text");
		buttonEnd = new JButton("End");
		
		//add listeners
		button1.addActionListener(new numberButtonHandler());
		button2.addActionListener(new numberButtonHandler());
		button3.addActionListener(new numberButtonHandler());
		button4.addActionListener(new numberButtonHandler());
		button5.addActionListener(new numberButtonHandler());
		button6.addActionListener(new numberButtonHandler());
		button7.addActionListener(new numberButtonHandler());
		button8.addActionListener(new numberButtonHandler());
		button9.addActionListener(new numberButtonHandler());
		button0.addActionListener(new numberButtonHandler());
		
		//add more listeners
		buttonFont.addActionListener(new otherButtonHandler());
		buttonClear.addActionListener(new otherButtonHandler());
		buttonSend.addActionListener(new otherButtonHandler());
		buttonText.addActionListener(new otherButtonHandler());
		buttonEnd.addActionListener(new otherButtonHandler());
		
		//add buttons to keypad
		keypad.add(button1);
		keypad.add(button2);
		keypad.add(button3);
		keypad.add(button4);
		keypad.add(button5);
		keypad.add(button6);
		keypad.add(button7);
		keypad.add(button8);
		keypad.add(button9);
		keypad.add(button0);
		keypad.add(buttonFont);
		keypad.add(buttonClear);
		keypad.add(buttonSend);
		keypad.add(buttonText);
		keypad.add(buttonEnd);
		
		//create font objects
		normal = new Font("SansSerif", Font.PLAIN, 12);
		bold = new Font("Serif", Font.BOLD, 18);
		
		//Initialize and setup screen
		screen = new JTextArea(10,15);
		screen.setText("             Ramrod Cell Phone App\n\n---------------- NO SERVICE ----------------");
		screen.setEditable(false);
		screen.setFont(normal);
		
		//add components and set visibility
		this.add(screen);
		this.add(keypad, BorderLayout.SOUTH);
		this.setVisible(true);
	}//end constructor
	
	//inner class - num buttons
	private class numberButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(buttonPress == false) //if first button press
			{
				screen.setText(""); //clear screen
				buttonPress = true;
			}
			
			if(buttonCount < 10) //if user has not yet entered a full phone number
			{
				++buttonCount;//increment counter
				if(e.getSource().equals(button1))
				{
					screen.append("1");
				}
				else if(e.getSource().equals(button2))
				{
					screen.append("2");
				}
				else if(e.getSource().equals(button3))
				{
					screen.append("3");
				}
				else if(e.getSource().equals(button4))
				{
					screen.append("4");
				}
				else if(e.getSource().equals(button5))
				{
					screen.append("5");
				}
				else if(e.getSource().equals(button6))
				{
					screen.append("6");
				}
				else if(e.getSource().equals(button7))
				{
					screen.append("7");
				}
				else if(e.getSource().equals(button8))
				{
					screen.append("8");
				}
				else if(e.getSource().equals(button9))
				{
					screen.append("9");
				}
				else if(e.getSource().equals(button0))
				{
					screen.append("0");
				}
			}
			if(buttonCount == 10)
			{
				screen.append("\nSend Call?");
			}
		}
	}//end inner class
	
	//inner class - other buttons
	private class otherButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(buttonFont))
			{
				//toggle font based on current font
				if(screen.getFont() == bold)
				{
					screen.setFont(normal);
				}
				else if(screen.getFont() == normal)
				{
					screen.setFont(bold);
				}
			}
			else if(e.getSource().equals(buttonClear))
			{
				//return to initial state
				screen.setEditable(false);
				message = false;
				buttonCount = 0;
				screen.setText("\n\n             Ramrod Cell Phone App\n\n---------------- NO SERVICE ----------------");
			}
			else if(e.getSource().equals(buttonSend))
			{
				if(message) //if text mode
				{
					screen.setText("Sending the message...\n\n" + screen.getText());
				}
				else
				{
					screen.setText("Dialing number...");
				}
			}
			else if(e.getSource().equals(buttonText))
			{
				//clear screen and reset variables
				screen.setText("");
				screen.setEditable(true);
				screen.setLineWrap(true);
				screen.requestFocus();
				message = true;
			}
			else if(e.getSource().equals(buttonEnd))
			{
				if(message)//if user has sent a text
				{
					screen.setText("Message sent...\n");
				}
				else //if user was making a call
				{
					screen.setText("Call ended...\n");
				}
				buttonCount = 0; //reset counter
			}
		}
	}//end inner class
	
	public static void main(String[] args)
	{
		new Ramrod_CellPhone();
		
		//Housekeeping
		System.out.println("\nEnd of Program.");
	}
	//end main

}
//end class