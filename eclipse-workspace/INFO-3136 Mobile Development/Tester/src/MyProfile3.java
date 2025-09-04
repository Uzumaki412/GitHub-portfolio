/**
 * Program Name: MyProfile.java
 * Purpose: 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MyProfile3 extends JFrame{

	public MyProfile3() {
		super("JFrame buttons with Flow Layout"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout()); 
		
		//1) create component Object
		JButton btn = new JButton("Button 1");
		JButton btn2 = new JButton("Button 2");
		JButton btn3 = new JButton("Button 3");
		JButton btn4 = new JButton("Button 4");
		JButton btn5 = new JButton("Button 5");
		JButton btn6 = new JButton("Button 5");
		JButton btn7 = new JButton("Button 5");
		JButton btn8 = new JButton("Button 5");
		JButton btn9 = new JButton("Button 5");
		JButton btn10 = new JButton("Button 5");
		JButton btn11 = new JButton("Button 5");
		JButton btn12 = new JButton("Button 5");
		JButton btn13 = new JButton("Button 5");
		
		
		//2) add component
		this.add(btn);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		this.add(btn6);
		this.add(btn7);
		this.add(btn8);
		this.add(btn9);
		this.add(btn10);
		this.add(btn11);
		this.add(btn12);
		this.add(btn13);
		
		this.setVisible(true);
		
	}//end constructor
	
	public static void main(String[] args) {
		MyProfile3 frame = new MyProfile3();
		
		
	}//end main
}//end class
