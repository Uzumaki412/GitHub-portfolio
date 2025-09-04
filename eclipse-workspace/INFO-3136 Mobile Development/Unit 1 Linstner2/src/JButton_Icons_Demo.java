/**
 * Program Name: JButton_Icons_Demo.java <br>
 * Purpose: shows how to put an image on a JButton using the JButton setIcon() method
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JButton_Icons_Demo extends JFrame
{

	//constructor
	JButton_Icons_Demo()
	{
		super("Icons on JButtons");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close the window and mark the object as garbage
		this.setSize(300, 200); //width and height in PIXELS
		this.setLocationRelativeTo(null);//centres the JFrame on your screen
		this.setLayout(new BorderLayout() );
		
		JPanel btnPanel= new JPanel();
		btnPanel.setLayout(new FlowLayout());
		//create JButton, then add the icon
		JButton squareBtn = new JButton();
		
		//create an ImageIcon object and pass it the path to the image file
		//Here we have placed the file in the Eclipse project folder so it can be
		// referenced using the RELATIVE PATH string.
		ImageIcon squareIcon = new ImageIcon("Xsquared.png");
		//now add this icon object to the JButton
		squareBtn.setIcon(squareIcon);
		btnPanel.add(squareBtn);
		//Now we'll do another one, but this time we'll do it as an ANONYMOUS ImageIcon
		// object.
		JButton percentBtn = new JButton();
		//call the JButton setIcon() method and create an ANONYMOUS ImageIcon object with
		// the path to the image file. Here we have it in the Project folder so we will
		// use the RELATIVE PATH to the file.
		percentBtn.setIcon(new ImageIcon("Percentage.png"));
		btnPanel.add(percentBtn);
		this.add(btnPanel);
		this.setVisible(true);
	}//end constructor

	public static void main(String[] args)
	{
		// anonymous object
		new JButton_Icons_Demo();
	}
	//end main
}//End class