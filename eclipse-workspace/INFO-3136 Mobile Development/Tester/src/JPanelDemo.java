/**
 * Program Name: MyProfile.java
 * Purpose: shows how to create a top level container called a JFrame
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class JPanelDemo extends JFrame{

	public JPanelDemo() {
		super("JFrame using JPanels"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(2,2)); // 2 rows, 2 columns
		
		//4 JPanels
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		// Set background colors for each panel
		panel1.setBackground(Color.RED);
		panel2.setBackground(Color.GREEN);
		panel3.setBackground(Color.BLUE);
		panel4.setBackground(Color.YELLOW);
		
		// Set the layout of each panel
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new BorderLayout());
		panel3.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS)); // Vertical BoxLayout
		
		// Add buttons to the panels
		panel1.add(new JButton("Button 1"));
		panel1.add(new JButton("Button 2"));
		// Adding buttons to panel2 with BorderLayout
		panel2.add(new JButton("Button 3"), BorderLayout.NORTH);
		panel2.add(new JButton("Button 4"), BorderLayout.SOUTH);
		panel2.add(new JButton("Button 5"), BorderLayout.WEST);
		panel2.add(new JButton("Button 6"), BorderLayout.EAST);
		panel2.add(new JButton("Button 7"), BorderLayout.CENTER);
		// Adding buttons to panel3 with GridLayout
		panel3.add(new JButton("Button 8"));
		panel3.add(new JButton("Button 9"));
		// Adding buttons to panel4 with BoxLayout
		panel4.add(new JButton("Button 10"));
		panel4.add(new JButton("Button 11"));
		panel4.add(new JButton("Button 12"));
		panel4.add(new JButton("Button 13"));
		panel4.add(new JButton("Button 14"));
		panel4.add(new JButton("Button 15"));
		panel4.add(new JButton("Button 16"));
		
		// Add panels to the JFrame
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		
		this.setVisible(true);
		
		
	}//end constructor
	
	public static void main(String[] args) {
		JPanelDemo frame = new JPanelDemo();
		
		
	}//end main
}//end class
