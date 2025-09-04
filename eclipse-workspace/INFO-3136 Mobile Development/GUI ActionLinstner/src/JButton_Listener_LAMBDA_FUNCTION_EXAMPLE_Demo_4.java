/**
 * Program Name: JButton_Listener_LAMBDA_FUNCTION_EXAMPLE_Demo_4.java <br>
 * Purpose: In this version, we will keep on ANONYMOUS INNER CLASS as a listener and replace the 
 *          other four listeners with LAMBDA FUNCTIONS, which are basically ANONYMOUS METHODS.
 * @author Bill Pulling
 * Date: July 8, 2025
 */
//The Three Wise Men (three imports seen in just about every GUI app done in Java)
import java.awt.*; //the ORIGINAL Java GUI package library
import java.awt.event.*; //for handling EVENT objects created by users interacting with components
import javax.swing.*; // the GREAT LEAP FORWARD...the big GUI upgrade done in JDK 1.2

public class JButton_Listener_LAMBDA_FUNCTION_EXAMPLE_Demo_4 extends JFrame

{
	//CLASS WIDE SCOPE AREA
	JLabel instructLbl; //this will be visible in both constructor method and actionPerformed() method.
	                    //we will be able to change the font color inside actionPerformed().
	
	//constructor
	public JButton_Listener_LAMBDA_FUNCTION_EXAMPLE_Demo_4()
	{
		//courtesy call to super class constructor to pass up title bar text
		super("Demo of Using LAMBDA FUNCTIONS for the Listeners");		
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
		
		//REVISIONS HERE> Keep the redButton as an ANONYMOUS INNER CLASS
	  redBtn.addActionListener(new ActionListener()
  		{
  	     public void actionPerformed(ActionEvent ev)
  	     {
  	    	 Container contentPane = getContentPane();
  	    	 contentPane.setBackground(Color.RED);
  	     } //end actionPerformed() 
  		}// end anonymous inner class		
  	);//end of the addActionListener method parameter list
	  
	  //REVISION: use lambdas for the other four buttons
	  //Stage 1: we'll keep the creation of the contentPane object here, so we will need to keep
	  //         the curly braces, as there is more than one line of code to execute.
	  blueBtn.addActionListener(ev ->
		{
	     {
	    	 Container contentPane = getContentPane();
	    	 contentPane.setBackground(Color.BLUE);
	     } //end actionPerformed() 
		}// end anonymous inner class		
	  );//end of the addActionListener method parameter list
	  
	  //REVISION: we'll take this down to one line of code by just calling for the contentPane object
	  //          as an ANONYMOUS OBJECT
	  greenBtn.addActionListener( ev -> getContentPane().setBackground(Color.GREEN));
	  //for the black button we need to put the curly braces back in because of the second line of code.	
	  blackBtn.addActionListener(ev-> { 
	  	                                getContentPane().setBackground(Color.BLACK); 
	                                    instructLbl.setForeground(Color.WHITE);	                                    
	                                  }
                               );
	  grayBtn.addActionListener(ev-> getContentPane().setBackground(Color.GRAY));
	  
	
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
		new JButton_Listener_LAMBDA_FUNCTION_EXAMPLE_Demo_4();
	}
	//end main


}
//end class