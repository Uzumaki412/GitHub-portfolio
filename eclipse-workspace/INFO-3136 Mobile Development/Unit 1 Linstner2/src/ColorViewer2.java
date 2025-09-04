/**
 * Program Name:ColorViewer2.java
 * Purpose: shows use of JSliders and the ChangeListener interface to respond to 
 *           JSlider events.
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */

import javax.swing.*;//the three wise men...usually imported for any GUI
import java.awt.*;   // that is responding to events.
import java.awt.event.*;//not used in this example
import javax.swing.event.*;//need this one as well


public class ColorViewer2 extends JFrame
{
	//CLASS WIDE SCOPE variables
	private JPanel panelOne, colorPanel;
	private JSlider rSlider, gSlider, bSlider;
	private JLabel rLabel, gLabel, bLabel;	
	
	
	//constructor
	public ColorViewer2() 
	{
		//courtesy call to super
		super("Color Viewer Application");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout() );
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		
		//create JPanels
		panelOne = new JPanel();
		panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS) );
		panelOne.setBackground(Color.WHITE);
		this.add(panelOne);//adds panel to JFrame
		
		//second panel to display colors
		colorPanel =new JPanel();//add to frame after components added...
		
		//create JSliders
		rSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		rSlider.setMajorTickSpacing(50);
		rSlider.setMinorTickSpacing(10);
		rSlider.setPaintTicks(true);
		rSlider.setPaintLabels(true);
		rSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		gSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		gSlider.setMajorTickSpacing(50);
		gSlider.setMinorTickSpacing(10);
		gSlider.setPaintTicks(true);
		gSlider.setPaintLabels(true);
		gSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		bSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		bSlider.setMajorTickSpacing(50);
		bSlider.setMinorTickSpacing(10);
		bSlider.setPaintTicks(true);
		bSlider.setPaintLabels(true);
		bSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		//TO DO LATER...register listeners for the sliders...
		SliderListener nanny = new SliderListener();
		rSlider.addChangeListener(nanny);
		gSlider.addChangeListener(nanny);
		bSlider.addChangeListener(nanny);
		
		
		//create some JLabels...NOTE: already declared in class scope
		rLabel = new JLabel("Red: 0");
		rLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		gLabel = new JLabel("Green: 0");
		gLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		bLabel = new JLabel("Blue: 0");
		bLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		//add labels and sliders to the panelOne object
		panelOne.add(rLabel);
		panelOne.add(rSlider);
		//add a small rigid area for spacing.
		panelOne.add(Box.createRigidArea(new Dimension(0,20) ) );
		
		panelOne.add(gLabel);
		panelOne.add(gSlider);
		panelOne.add(Box.createRigidArea(new Dimension(0,20) ) );
		
		panelOne.add(bLabel);
		panelOne.add(bSlider);
		
		//add the color panel to the JFrame and adjust its PREFERRED SIZE
		this.add(colorPanel);
		colorPanel.setPreferredSize(new Dimension(350,350) );
		colorPanel.setBackground(Color.BLACK);
		
		//last line
		this.setVisible(true);
		
	}//end constructor
	
	//INNER CLASS GOES HERE
	
	private class SliderListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent ev)
		{
			//to be completed in class
			//need three local ints to hold the slider values
			int red, green, blue;
			
			//Step 1: read the current value of each slider
			red = rSlider.getValue();
			green = gSlider.getValue();
			blue = bSlider.getValue();
			
			//Step 2: display each slider's value in its corresponding JLabel 
			rLabel.setText("Red: " + red);
			gLabel.setText("Green: " + green);
			bLabel.setText("Blue: " + blue);
			
			//Step 3: create a Color object and assign that color to the colorPanel background color
			colorPanel.setBackground(new Color(red,green,blue) );		//ANONYMOUS COLOR OBJECT here...	
			
			
			
		}//end method
	}//end inner class
	
	public static void main(String [] args)
	{
		new ColorViewer2();
	}//end main	
}//end class





