/**
 * Program Name: PaintPanel.java
 * Purpose: shows how to respond to mouse events such as click and drag type of operations.
 * Coder: Bill Pulling for Section 01
 * Date: July 8, 2025
 */
import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;

public class PaintPanel extends JPanel
{
   //CLASS WIDE SCOPE AREA
	private Point[] pointsArray = new Point[10000];//arbitrarily set to 10,000 to give us lots of drawing capability
	private int pointCounter = 0;
	
	//constructor
	public PaintPanel()
	{
		//courtesy call to super
		super();
		//register a listener as an ANONYMOUS INNER CLASS
		this.addMouseMotionListener(new MouseMotionAdapter()
				{
			    //OVER-RIDE the mouseDragged() method
			    public void mouseDragged(MouseEvent ev)
			    {
			    	//check that we have not yet reached our array limit of 10,000 Points
			    	if(pointCounter < pointsArray.length)
			    	{
			    		pointsArray[pointCounter] = ev.getPoint();
			    		pointCounter++;
			    		
			    		//call the repaint() method to repaint on the contentPane all of the 
			    		// points currently in the pointArray
			    		repaint();			    		
			    	}		    	
			    }//end mouseDragged()
			
				}//end anonymous inner class			
				);//closing bracket for call to addMouseMotionListener
		
	}//end constructor
	
	/**
	 * Method Name: paintComponent()
	 * Purpose: repaints the current array of Point objects onto the contentPane of the
	 *          JPanel. Each Point object will be represented by a dot on the screen that
	 *          has dimensions of 5 pixels high and 5 pixels wide.
	 * Accepts: an object of the Graphics class that will act as our "paint brush".
	 * Returns: nothing. Void method.
	 */
	public void paintComponent(Graphics g) //'g' is our paint brush.
	{
		//clear the contentPane of previous dots by "throwing a bucket of paint" to 
		// cover up the previous dot collection
		super.paintComponent(g);
		//can change the color of the figure to any color you want. 
		g.setColor(Color.BLACK);
		//loop through the pointsArray and at each point, draw a new dot
		for(int i = 0; i < pointCounter; i++)
		{
			//use the paint brush object 'g' to call the fillOval() method, which produces a 
			// filled circle or oval object depending on what args you pass in.
			//g.fillOval(pointsArray[i].x, pointsArray[i].y, 15, 15);
			g.fillRect(pointsArray[i].x, pointsArray[i].y, 15, 15);
			//repaint it
			this.repaint();		
		}//end for
		
	}//end paintComponent()	
	
	public static void main(String[] args)
	{
		// build JFrame here and create an object of the PaintPanel class and add it to the frame.
		JFrame frame = new JFrame("A Simple Painting Program");
		//boilerplate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout() );
		frame.setSize(700,700);
		frame.setLocationRelativeTo(null);
		
		//create the JPanel and add it to frame
		PaintPanel panel = new PaintPanel();
		frame.add(panel);//will default to the CENTER zone
		
		//add an instruction label for the bottom zone
		JLabel instrLbl = new JLabel("Hold down the left mouse button and drag to draw...");
		frame.add(instrLbl, BorderLayout.SOUTH);
		
		//last line
		frame.setVisible(true);
		
	}	//end main
}//end class









