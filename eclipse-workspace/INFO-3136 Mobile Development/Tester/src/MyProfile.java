/**
 * Program Name: MyProfile.java
 * Purpose: shows how to create a top level container called a JFrame
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 2, 2025
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MyProfile extends JFrame{

	public MyProfile() {
		super("JFrame"); // title
		
		//Positioning and Sizing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout()); // Default Layout is BorderLayout
		this.setVisible(true);
		
	}//end constructor
	
	public static void main(String[] args) {
		MyProfile frame = new MyProfile();
		
		
	}//end main
}//end class
