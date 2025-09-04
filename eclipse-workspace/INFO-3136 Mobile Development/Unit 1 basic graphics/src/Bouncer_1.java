/**
 * Program Name: Bouncer_1.java
 * Purpose: a basic animation program that has a 'ball' shape bouncing around inside a box. We make use of a Timer class
 *          object to control the repainting of the ball on the JPanel.
 * Coder: Bill Pulling
 * Date: Jul 8, 2025
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Bouncer_1 extends JPanel
{
  //CLASS WIDE SCOPE AREA
	private final int WIDTH = 500, HEIGHT = 400;//dimensions of the JPanel
	private final int LAG_TIME = 10; //time in milliseconds between repaints...
	private Timer timer; //this object will fire an ActionEvent event every LAG_TIME interval , or 10 milliseconds
	
	private final int IMG_DIM = 50; //used to size the ball shape at 50 pixels
	private int x, y, offsetX, offsetY; //used to position the ball on the JPanel
	
	private Random rand; //used to generate random numbers for the ball's position and offset values, if desired.
	private Color ballColor; //used to set the color of the ball, if desired.
	private final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE};
	//CONSTRUCTOR method
	public Bouncer_1()
	{
		//create the Timer object and register a listener for it
		this.timer = new Timer(LAG_TIME, new BouncerListener() );
		
		//starting position of ball
		this.x = 0;
		this.y = 0; 
		
		//set the size of the offsets, the distance the ball will move on each event.
		this.offsetX = 2; 
		this.offsetY = 2; 
		
		//set the preferred size of the JPane using an ANONYMOUS DIMENSION OBJCET
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT) );
		this.setBackground(Color.BLACK); //set the background color of the JPanel
		this.rand = new Random(); //create a new Random object to generate random numbers
		this.ballColor = Color.RED; //set the initial color of the ball
		//start the timer so that it starts pumping out ActionEvent screaming babies...
		this.timer.start();
		
	}//end constructor
	
	//OVERRIDE the JPanel's paintComponent() method
	public void paintComponent(Graphics g)
	{
		//call super class version to "throw the bucket of paint on the object and give us a new blank drawing surface
		super.paintComponent(g);
		//set brush color
		g.setColor(ballColor);
		//draw the ball shape
		g.fillOval(x, y, IMG_DIM, IMG_DIM);		
		
	}//end method	
	
	private void changeBallColor()
	{
	    ballColor = COLORS[rand.nextInt(COLORS.length)];
	}
	
	//INNER CLASS HERE,
	private class BouncerListener implements ActionListener
	{
		//implement the method to fulfill the contract
		public void actionPerformed(ActionEvent ev)
		{
			//when the Timer fires an ActionEvent, re-calculate the co-ordinates of where ball will be drawn.
			//imcrement the x and y coords by the offset values
			boolean bounced = false; //used to determine if the ball has bounced off a wall
			x += offsetX;
			y += offsetY;
			
			//we need to check to see if the ball is near the top, bottom, or side walls.If it is, we need to multiply the 
			//offset value by -1 to make the ball "go the other way".
			
			if(x <= 0 || x >= WIDTH - IMG_DIM)
			{
				offsetX = offsetX *= -1;
				bounced = true; //set the bounced flag to true
			}
			
			if(y <= 0 || y >= HEIGHT - IMG_DIM)
			{
				offsetY = offsetY *= -1;
				bounced = true; //set the bounced flag to true
			}
			
			if(bounced)
			{
				changeBallColor();
			}
			
			//call repaint(), which calls paintComponent()
			repaint();
		}//end method
	}//end inner class
	
	
	
	public static void main(String[] args)
	{
		// build a JFrame and add an instance of this class to it.
		JFrame frame = new JFrame("Just follow the bouncing ball...");
		//boilerplate code
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes window and marks object as garbage
		frame.setSize(1200,800);//width and height
		frame.setLocationRelativeTo(null);//centre the JFrame 
		frame.setLayout(new FlowLayout() );//using an ANONYMOUS OBJECT here for the layout object
		
		//set background of content pane of JFrame
		frame.getContentPane().setBackground(Color.BLUE);
		
		//create an ANONYMOUS object of our class and add it to the frame
		frame.add( new Bouncer_1() );
		//PACK IT!
		frame.pack();//minimizes the screen footprint of your app.		
		frame.setVisible(true);
	}
	//end main
}
//end class





