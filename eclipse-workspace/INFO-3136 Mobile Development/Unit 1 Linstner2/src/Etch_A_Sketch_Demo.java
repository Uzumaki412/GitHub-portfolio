/**
 * Program Name: Etch_A_Sketch_Demo.java
 * Purpose: shows the use of a KeyListener to respond to presses of the four arrow keys.
 *         Mimics the old Etch-A-Sketch drawing toy from the 60's
 * Coder: Original attribution has been lost, but modified by Bill Pulling
 * Date: July 5, 2025
 * 
 */
import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;//not used in this example
import javax.swing.event.*;

public class Etch_A_Sketch_Demo extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private int x, y , previousX, previousY;//co-ordinates for drawing on JPanel
	private boolean firstTimeFlag = true; //just used at start-up to position cursor in mid screen
	private JPanel drawingPanel;
	
	//constructor
	public Etch_A_Sketch_Demo()
	{
		//courtesy call
		super("Etch-A-Sketch Demo: draw a picture using the ARROW keys");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout() );
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		
		//build the JPanel
		drawingPanel = new JPanel();
		drawingPanel.setLayout(new BorderLayout() );
		drawingPanel.addKeyListener(new KeyListenerNanny() );
		//set the focus to the JPanel
		drawingPanel.setFocusable(true);
		
		this.add(drawingPanel);		
		//last line
		this.setVisible(true);
	}//end constructor
	
	//INNER CLASS GOES HERE
	private class KeyListenerNanny extends KeyAdapter
	{
		public void keyPressed( KeyEvent ev)
		{
			//figure out which arrow key was pressed...use a switch statement
			switch(ev.getKeyCode())
			{
			case KeyEvent.VK_DOWN:
				previousY = y;
				previousX = x;
				y += 5;
				break;
				
			case KeyEvent.VK_UP:
				previousY = y;
				previousX = x;
				y -= 5;
				break;
				
			case KeyEvent.VK_LEFT:
				previousY = y;
				previousX = x;
				x -= 5;
				break;
				
			case KeyEvent.VK_RIGHT:
				previousY = y;
				previousX = x;
				x += 5;
				break;
			}//end switch
			
			// get a paintbrush!
			Graphics g = drawingPanel.getGraphics();
			
			//if just at start-up, centre the cursor and flip the flag to false
			if(firstTimeFlag)
			{
				x = previousX = drawingPanel.getWidth()/2;
				y = previousY = drawingPanel.getHeight()/2; //sets cursor at position (300,300)
				firstTimeFlag = false; 
			}
			
			//draw the line based on key inputs
			g.drawLine(previousX, previousY, x, y);			
		}//end keyPressed () method
		
	}//end inner class

	public static void main(String[] args)
	{
		// anonymous object to get it goin'
		new Etch_A_Sketch_Demo();
	}//end main

}//end  class
