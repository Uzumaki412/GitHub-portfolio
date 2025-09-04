/**
 * Program Name: Oval_Demo_2.java
 * Purpose: shows how to use the paint() method and its Graphics object 'g' to render some oval shapes. 
 *          REVISION HERE: added a loop in the paint() method
 * Coder: Bill Pulling
 * Date: Jul 8, 2025
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oval_Demo_2 extends JPanel
{
  //override the paint() method of the JPanel class and add our code there.
	public void paint(Graphics g)
	{
		super.paint(g);//call super class version to start with a blank canvas...
		for(int i = 0; i<400; i+=10)
		{
			//set the color
			g.setColor(Color.RED);
			//draw an oval
			g.drawOval(20, i, 100 + i, 150); //(x and y of upper left corner of boundary box, and width and height of the oval
			
			g.setColor(Color.BLUE);
			g.drawOval(i,100,100,150);
			
			g.setColor(Color.GREEN);
			g.fillOval(200,200, 120, 60);
		}//end for
	}//end paint
	
	public static void main(String[] args)
	{
		// build a JFrame and add an instance of this class to it.
		JFrame frame = new JFrame("Drawing some ovals on a JPanel");
		//boilerplate code
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes window and marks object as garbage
		frame.setSize(400,400);//width and height
		frame.setLocationRelativeTo(null);//centre the JFrame 
		frame.setLayout(new BorderLayout() );//using an ANONYMOUS OBJECT here for the layout object
		
		//create an instance of our class
		JPanel drawPanel = new Oval_Demo_2();
		drawPanel.setBackground(Color.WHITE);
		frame.add(drawPanel);
		//LAST LINE!
		frame.setVisible(true);
	}
	//end main
}
//end class




