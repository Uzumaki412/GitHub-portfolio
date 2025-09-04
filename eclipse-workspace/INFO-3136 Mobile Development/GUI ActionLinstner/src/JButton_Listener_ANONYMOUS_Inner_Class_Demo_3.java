/**
 * Program Name: JButton_Listener_ANONYMOUS_Inner_Class_Demo_3.java <br>
 * Purpose: In this version, we will use ANONYMOUS INNER CLASSES when we assign listeners to 
 *          each of the JButtons. One drawback with this approach is that we have to repeat the
 *          code for each individual button, so in effect, we are creating five separate
 *          listener objects instead of just one listener that could listen for all of the buttons. 
 * @author Bill Pulling
 * Date: July 8, 2025
 * 
 */
//The Three Wise Men (three imports seen in just about every GUI app done in Java)
import java.awt.*; //the ORIGINAL Java GUI package library
import java.awt.event.*; //for handling EVENT objects created by users interacting with components
import javax.swing.*; // the GREAT LEAP FORWARD...the big GUI upgrade done in JDK 1.2

public class JButton_Listener_ANONYMOUS_Inner_Class_Demo_3 extends JFrame

{
	//CLASS WIDE SCOPE AREA
	JLabel instructLbl; //this will be visible in both constructor method and actionPerformed() method.
	                    //we will be able to change the font color inside actionPerformed().
	
	//constructor
	public JButton_Listener_ANONYMOUS_Inner_Class_Demo_3()
	{
		//courtesy call to super class constructor to pass up title bar text
		super("Demo of Using an ANONYMOUS INNER Class Object as the Listener");		
		//BOILER PLATE CODE..this is standard code seen in almost all JFrame sub classes to set things up
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//destroy this object when close button is pressed
		this.setSize(600, 100); //width and height in pixels
		this.setLocationRelativeTo(null);//centres the JFrame on the screen.
		this.setLayout(new FlowLayout());
		
		//create 5 JButtons with different color labels so that we can change the background color
		JButton redBtn = new JButton("Red");
		JButton blueBtn = new JButton("Blue");
		JButton greenBtn = new JButton("Green");
		JButton blackBtn = new JButton("Black");
		JButton grayBtn = new JButton("Gray");
		
		//add these to the JFrame
		this.add(redBtn);
		this.add(blueBtn);
		this.add(greenBtn);
		this.add(blackBtn);
		this.add(grayBtn);
		
		//add an instruction label. Use the one declared in CLASS WIDE SCOPE
		instructLbl = new JLabel("To change the background color, press a button.");
		this.add(instructLbl);
		
		//VERY IMPORTANT STEP IN EVENT HANDLING! You must REGISTER A LISTENER for each JBUtton. In other
		// words, we have to ASSIGN A NANNY TO EACH CHILD EVENT that may be created. 
		
		//REVISIONS HERE> COMMENT OUT our nanny object creation
		//MaryPoppins nanny  = new MaryPoppins();
	  redBtn.addActionListener(new ActionListener()
  		{
  	     public void actionPerformed(ActionEvent ev)
  	     {
  	    	 Container contentPane = getContentPane();
  	    	 contentPane.setBackground(Color.RED);
  	     } //end actionPerformed() 
  		}// end anonymous inner class		
  	);//end of the addActionListener method parameter list
	  
	  //RINSE AND REPEAT for each of the other buttons.
	  blueBtn.addActionListener(new ActionListener()
		{
	     public void actionPerformed(ActionEvent ev)
	     {
	    	 Container contentPane = getContentPane();
	    	 contentPane.setBackground(Color.BLUE);
	     } //end actionPerformed() 
		}// end anonymous inner class		
	  );//end of the addActionListener method parameter list
	  
	  greenBtn.addActionListener(new ActionListener()
		{
	     public void actionPerformed(ActionEvent ev)
	     {
	    	 Container contentPane = getContentPane();
	    	 contentPane.setBackground(Color.GREEN);
	     } //end actionPerformed() 
		}// end anonymous inner class		
	  );//end of the addActionListener method parameter list
	  
	  blackBtn.addActionListener(new ActionListener()
		{
	     public void actionPerformed(ActionEvent ev)
	     {
	    	 Container contentPane = getContentPane();
	    	 contentPane.setBackground(Color.BLACK);
				 instructLbl.setForeground(Color.WHITE);
	     } //end actionPerformed() 
		}// end anonymous inner class		
	  );//end of the addActionListener method parameter list
	  
	  grayBtn.addActionListener(new ActionListener()
		{
	     public void actionPerformed(ActionEvent ev)
	     {
	    	 Container contentPane = getContentPane();
	    	 contentPane.setBackground(Color.GRAY);
	     } //end actionPerformed() 
		}// end anonymous inner class		
	  );//end of the addActionListener method parameter list	
		
		//LAST LINE!
		this.setVisible(true);
	}//end constructor

	/**
	 * This method will: generate the JFrame using an ANONYMOUS OBJECT and start the program. 
	 * @param args
	 */
	public static void main(String[] args)
	{
     //ANONYMOUS OBJECT
		new JButton_Listener_ANONYMOUS_Inner_Class_Demo_3();
	}
	//end main


}
//end class