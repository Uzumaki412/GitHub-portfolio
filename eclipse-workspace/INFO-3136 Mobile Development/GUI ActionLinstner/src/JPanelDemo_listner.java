/**
 * Program Name: MyProfile.java
 * Purpose: shows how the host class object can be assigned to a JFrame and also listen to events
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class JPanelDemo_listner extends JFrame implements ActionListener {

	public JPanelDemo_listner() {
		super("JButton host class as listner"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,100);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout()); 
		
		JButton redBtn = new JButton("Red");
		JButton greenBtn = new JButton("Green");
		JButton blueBtn = new JButton("Blue");
		JButton yellowBtn = new JButton("Yellow");
		JButton blackBtn = new JButton("Black");
		JButton greyBtn = new JButton("Grey");
		
		this.add(redBtn);
		this.add(greenBtn);
		this.add(blueBtn);
		this.add(yellowBtn);
		this.add(blackBtn);
		this.add(greyBtn);
		
		JLabel instructionLabel = new JLabel("Press any button to change the background color");
		this.add(instructionLabel);
		
		// Register the host class as an ActionListener for each button
		// the host class (JFrame) will listen to the button click events
		redBtn.addActionListener(this);
		greenBtn.addActionListener(this);
		blueBtn.addActionListener(this);
		yellowBtn.addActionListener(this);
		blackBtn.addActionListener(this);
		greyBtn.addActionListener(this);
		
		
		this.setVisible(true);
		
		
	}//end constructor
	
	public static void main(String[] args) {
		JPanelDemo_listner frame = new JPanelDemo_listner();
	}//end main


	@Override
	public void actionPerformed(ActionEvent ev) {
		Container contentPane = this.getContentPane(); // Get the content pane of the JFrame
		if(ev.getActionCommand().equals("Red")) {
			contentPane.setBackground(Color.RED);
		} else if(ev.getActionCommand().equals("Green")) {
			contentPane.setBackground(Color.GREEN);
		} else if(ev.getActionCommand().equals("Blue")) {
			contentPane.setBackground(Color.BLUE);
		} else if(ev.getActionCommand().equals("Yellow")) {
			contentPane.setBackground(Color.YELLOW);
		} else if(ev.getActionCommand().equals("Black")) {
			contentPane.setBackground(Color.BLACK);
		} else if(ev.getActionCommand().equals("Grey")) {
			contentPane.setBackground(Color.GRAY);
		}
		else {
			contentPane.setBackground(Color.WHITE); // Default color if no match
		}	
		
	}

}//end class
