/**
 * Program Name: Oval_Motion_Demo_1.java
 * Purpose: shows how to make a drawing move by repainting the screen using the repaint() method.
 * Coder: Bill Pulling
 * Date: Jul 8, 2025
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oval_Motion_Demo_1 extends JPanel
{
	//CLASS WIDE SCOPE AREA
	int xCoord = 0;
	int yCoord = 0;
	
	
  //override the paint() method of the JPanel class and add our code there.
	public void paint(Graphics g)
	{
		super.paint(g);//call super class version to start with a blank canvas...
		
			//set the color
			g.setColor(Color.RED);
			//draw an oval
			g.drawOval(xCoord, yCoord, 50,50); //(x and y of upper left corner of boundary box, and width and height of the oval
			
			//increment the coordinates
			xCoord += 5;
			yCoord += 5;
			
			//call pause method
			pause(50);
			
			//call repaint method to "throw the bucket of paint" and repaint the shape in a new position. 
			repaint();
			
		
	}//end paint
	
	/**
	 * Method Name: pause()
	 * Purpose: requests that the event dispatch thread pause for a specified number of milliseconds.
	 * Accepts: an int that is the time in milliseconds for the pause.
	 * Returns: NOTHING! Void method.
	 */
	public void pause(int interval)
	{
		try
		{
			Thread.sleep(interval);
		}
		catch(InterruptedException ex)
		{
			System.out.println("InterruptedException was caught, " + ex.getMessage() );
			ex.printStackTrace();
		}
	}//end pause
	
	
	public static void main(String[] args)
	{
		// build a JFrame and add an instance of this class to it.
		JFrame frame = new JFrame("Drawing some ovals repeatedly on a JPanel");
		//boilerplate code
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes window and marks object as garbage
		frame.setSize(400,400);//width and height
		frame.setLocationRelativeTo(null);//centre the JFrame 
		frame.setLayout(new BorderLayout() );//using an ANONYMOUS OBJECT here for the layout object
		
		//create an instance of our class
		JPanel drawPanel = new Oval_Motion_Demo_1();
		drawPanel.setBackground(Color.WHITE);
		frame.add(drawPanel);
		//LAST LINE!
		frame.setVisible(true);
	}
	//end main
}
//end class




