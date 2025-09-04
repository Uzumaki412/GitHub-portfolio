/**
 * Program Name: MouseListener_Example.java
 * Purpose: shows the use of the MouseListener interface to handle
 * the five "interesting" mouse events
 * Coder: From Deitel and Deitel "Java How to Program", 8th edition, 2010, 
 *        modified by Bill Pulling for Sec01
 *     
 * Date: July 8, 2025
 */
import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;

public class MouseListener_Example extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private JLabel[] labelArray;
	Font font = new Font("Serif",Font.PLAIN, 48);
	
	//CONSTRUCTOR
	public  MouseListener_Example()
	{
		//courtesy call
		super("Mouse Listener Example");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//prevents memory leak
		this.setSize(600,600);//width, height...change to desired dimensions.
		this.setLocationRelativeTo(null);//automatically centres our frame in the screen
		this.setLayout(new GridLayout(6,3,10,10) );//
		
		//create labelArray
		labelArray = new JLabel[6];
		labelArray[0] = new JLabel("Mouse Clicked", SwingConstants.CENTER);
		labelArray[1] = new JLabel("Mouse Entered", SwingConstants.CENTER);
		labelArray[2] = new JLabel("Mouse Exited", SwingConstants.CENTER);
		labelArray[3] = new JLabel("Mouse Pressed", SwingConstants.CENTER);
		labelArray[4] = new JLabel("Mouse Released", SwingConstants.CENTER);
		labelArray[5] = new JLabel(" ", SwingConstants.CENTER);
		
		//add the labels to the frame using a loop
		for(int i = 0; i < labelArray.length; i++)
		{
			labelArray[i].setForeground(Color.GRAY);
			labelArray[i].setFont(font);
			this.add(labelArray[i]);
		}//end loop
		
		//REGISTER Listener for the JFrame for mouse events using our
		// inner class MouseHandler object
		
		this.addMouseListener(new MouseHandler());//anonymous object
		
		
		//last line
		this.setVisible(true);
		
	}//end constructor
	
	//INNER CLASS GOES HERE
	private class MouseHandler implements MouseListener
	{
		//NOTE: there are five methods in MouseListener interface. 
		//BY CONTRACT, we have to implement all five
		
		
		@Override
		public void mouseClicked(MouseEvent ev)
		{
		// cycle through the labels and adjust as needed
					for(int i = 0; i < labelArray.length; i++)
					{
						if(i == 0)
						{
							labelArray[i].setForeground(Color.GREEN);
						}
						else
						{
							labelArray[i].setForeground(Color.GRAY);
						}
					}//end for
					
					//print the co-ordinates of the mouse at the time the event 
					//occurred in the blank label at the bottom of the stack.
					labelArray[5].setText("[" + ev.getX() + ", " + ev.getY() + "]");
			
		}//end method

 
		@Override
		public void mouseEntered(MouseEvent ev)
		{
			// cycle through the labels and adjust as needed
				for(int i = 0; i < labelArray.length; i++)
				{
					if(i == 1)
					{
						labelArray[i].setForeground(Color.RED);
					}
					else
					{
						labelArray[i].setForeground(Color.GRAY);
					}
				}//end for
				
				//print the co-ordinates of the mouse at the time the event 
				//occurred in the blank label at the bottom of the stack.
				labelArray[5].setText("[" + ev.getX() + ", " + ev.getY() + "]");
			
		}
		
		@Override
		public void mouseExited(MouseEvent ev)
		{
			// cycle through the labels and adjust as needed
				for(int i = 0; i < labelArray.length; i++)
				{
					if(i == 2)
					{
						labelArray[i].setForeground(Color.BLUE);
					}
					else
					{
						labelArray[i].setForeground(Color.GRAY);
					}
				}//end for
				
				//print the co-ordinates of the mouse at the time the event 
				//occurred in the blank label at the bottom of the stack.
				labelArray[5].setText("[" + ev.getX() + ", " + ev.getY() + "]");
			
		}@Override
		public void mousePressed(MouseEvent ev)
		{
			// cycle through the labels and adjust as needed
				for(int i = 0; i < labelArray.length; i++)
				{
					if(i == 3)
					{
						labelArray[i].setForeground(Color.ORANGE);
					}
					else
					{
						labelArray[i].setForeground(Color.GRAY);
					}
				}//end for
				
				//print the co-ordinates of the mouse at the time the event 
				//occurred in the blank label at the bottom of the stack.
				labelArray[5].setText("[" + ev.getX() + ", " + ev.getY() + "]");
			
		}

		@Override
		public void mouseReleased(MouseEvent ev)
		{
			// cycle through the labels and adjust as needed
				for(int i = 0; i < labelArray.length; i++)
				{
					if(i == 4)
					{
						labelArray[i].setForeground(Color.CYAN);
					}
					else
					{
						labelArray[i].setForeground(Color.GRAY);
					}
				}//end for
				
				//print the co-ordinates of the mouse at the time the event 
				//occurred in the blank label at the bottom of the stack.
				labelArray[5].setText("[" + ev.getX() + ", " + ev.getY() + "]");
		}//end method
		
	}//end inner class
	
	public static void main(String[] args)
	{
		// anonymous object to start things up
		new MouseListener_Example();

	}

}
