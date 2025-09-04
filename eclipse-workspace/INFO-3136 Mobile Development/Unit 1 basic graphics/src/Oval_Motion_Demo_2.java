/**
 * Program Name: Oval_Motion_Demo_2.java
 * Purpose: shows how to make a drawing appear anywhere on the screen by repainting the screen using the repaint() method
 *          and using random values for the co-ordinates and size of the oval.
 * Coder: Bill Pulling
 * Date: Jul 8, 2025
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oval_Motion_Demo_2 extends JPanel
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
			//generate four random values from 0 to 600
			int random1 = (int)(Math.random() *601);
			int random2 = (int)(Math.random() *601);
			int random3 = (int)(Math.random() *601);
			int random4 = (int)(Math.random() *601);		
			
			//draw an oval
			g.drawOval(random1, random2, random3, random4); //(x and y of upper left corner of boundary box, and width and height of the oval
			
			//call pause method
			pause(100);
			
			//call repaint method to "throw the bucket of paint" and repaint the shape in a new position. 
			//repaint();
			
		
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
		JFrame frame = new JFrame("Poor man's Screensaver...");
		//boilerplate code
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes window and marks object as garbage
		frame.setSize(800,800);//width and height
		frame.setLocationRelativeTo(null);//centre the JFrame 
		frame.setLayout(new BorderLayout() );//using an ANONYMOUS OBJECT here for the layout object
		
		//create an instance of our class
		JPanel drawPanel = new Oval_Motion_Demo_2();
		drawPanel.setBackground(Color.WHITE);
		frame.add(drawPanel);
		//LAST LINE!
		frame.setVisible(true);
	}
	//end main
}
//end class




