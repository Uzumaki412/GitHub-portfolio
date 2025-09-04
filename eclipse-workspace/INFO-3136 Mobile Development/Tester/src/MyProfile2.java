/**
 * Program Name: MyProfile.java
 * Purpose: 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MyProfile2 extends JFrame{

	public MyProfile2() {
		super("JFrame with buton in borderLayout"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout()); // Default Layout is BorderLayout
		
		//1) create component Object
		JButton btn = new JButton("Button 1");
		JButton btn2 = new JButton("Button 2");
		JButton btn3 = new JButton("Button 3");
		JButton btn4 = new JButton("Button 4");
		JButton btn5 = new JButton("Button 5");
		
		//2) add component
		this.add(btn, BorderLayout.NORTH);
		this.add(btn2, BorderLayout.SOUTH);
		this.add(btn3, BorderLayout.WEST);
		this.add(btn4, BorderLayout.EAST);
		this.add(btn5);

		this.setVisible(true);
		
	}//end constructor
	
	public static void main(String[] args) {
		MyProfile2 frame = new MyProfile2();
		
		
	}//end main
}//end class
