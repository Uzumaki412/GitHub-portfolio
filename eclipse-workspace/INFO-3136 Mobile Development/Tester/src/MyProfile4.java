/**
 * Program Name: MyProfile.java
 * Purpose: shows how to create a top level container called a JFrame
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MyProfile4 extends JFrame{

	public MyProfile4() {
		super("JFrame buttons with gridLayour"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(2,5,10,10)); // 2 rows, 5 columns, horizontal and vertical gaps of 10 pixels
		
		//1) create component Object
		JButton btn1 = new JButton("Button 1");
		JButton btn2 = new JButton("Button 2");
		JButton btn3 = new JButton("Button 3");
		JButton btn4 = new JButton("Button 4");
		JButton btn5 = new JButton("Button 5");
		JButton btn6 = new JButton("Button 6");
		JButton btn7 = new JButton("Button 7");
		JButton btn8 = new JButton("Button 8");
		JButton btn9 = new JButton("Button 9");
		JButton btn10 = new JButton("Button 10");
		
		
		//2) add component
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		this.add(btn6);
		this.add(btn7);
		this.add(btn8);
		this.add(btn9);
		this.add(btn10);
		
		this.setVisible(true);
	}//end constructor
	
	public static void main(String[] args) {
		MyProfile4 frame = new MyProfile4();
		
		
	}//end main
}//end class
