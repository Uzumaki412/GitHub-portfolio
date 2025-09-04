/**
 * Program Name: RubberLinesPanel.java
 * Purpose: another example that uses MouseMotionListener. Here we will be drawing lines on a JPanel by dragging
 *          the mouse. When we draw a second line the first line will disappear when we call the repaint() method.
 *          Attribution: originally from Lewis and Loftus, 3rd edition
 * Coder: Bill Pulling for Section 01
 * Date: July 8, 2025
 */
import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;
import javax.swing.event.*;

public class RubberLinesPanel extends JPanel
{
	//CLASS WIDE SCOPE AREA
	private Point point1 = null;
	private Point point2 = null; //the two endpoints of the line to be drawn on the panel

	//constructor
	public RubberLinesPanel()
	{
		//courtesy call
		super();
		//create a MouseMotionLister using the inner class
		LineListener nanny = new LineListener();
		
		//register a listener on the panel()
		this.addMouseListener(nanny);
		this.addMouseMotionListener(nanny);
		
		//boilerplate
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(400,200) );
		
	}//end constructor
	
	//OVERRIDE the paintComponent() method of the JPanel
	public void paintComponent(Graphics g)//'g' is our paintbrush object
	{
		//call super class version of paintComponent
		//NOTE: comment  out the line below to see the effect on your app
		super.paintComponent(g);// here is where we "throw the bucket of paint" onto the canvas to cover what was there.
		
		//set the color
		g.setColor(Color.YELLOW);
		
		//draw the line on the panel as long as point1 and point2 values are not null
		if(point1 != null && point2 != null)
		{
			g.drawLine(point1.x, point1.y, point2.x, point2.y);
		}
		
		
	}// end paintComponent()
	
	
	//INNER CLASS HERE
	private class LineListener implements MouseListener, MouseMotionListener
	{
		@Override
		public void mousePressed(MouseEvent ev)//from MouseListener
		{
			//capture the INITIAL position of the mouse cursor where line starting point will be
			point1 = ev.getPoint();
		}
		@Override
		public void mouseDragged(MouseEvent ev)//from MouseMotionListener
		{
			//get current position of mouse cursor as it is being dragged
			point2 = ev.getPoint();
			//repaint the line as mouse is dragged
			repaint();
		}
    //NOTE: we don't use the rest of these methods, so we just have to do an 'empty implementation' by putting in
		//      the opening and closing curly braces.
		@Override
		public void mouseMoved(MouseEvent e)
		{
			
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			
		}
		
	}//end inner class
	
	
	
	public static void main(String[] args)
	{
		//build the JFrame here and add the JPanel
		JFrame frame = new JFrame("A Simple One Line Drawing App");
		//create the panel
		RubberLinesPanel panel = new RubberLinesPanel();
		
		//boilerplate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//prevents memory leak
		frame.setSize(500,300);//width, height...change to desired dimensions.
		frame.setLocationRelativeTo(null);//automatically centres our frame in the screen
		frame.setLayout(new BorderLayout() );
		
		frame.add(panel, BorderLayout.CENTER);		
		//last line
		frame.setVisible(true);		
	}	//end main
}//end class




