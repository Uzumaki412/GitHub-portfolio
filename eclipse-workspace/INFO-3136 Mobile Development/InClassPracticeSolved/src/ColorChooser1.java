/**
 * Program Name:ColorChooser1.java
 * Purpose: Layout Managers Practice Sheet #1 question 3
 * Coder: Bill Pulling
 * Date: Apr 2, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ColorChooser1 extends JFrame
{
	//constructor
	public ColorChooser1()
	{
		super("Color Chooser App");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,150);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout() );	
		
		//components
		//place in NORTH
		JTextField colorFld = new JTextField("RED");
		this.add(colorFld, BorderLayout.NORTH);
		
		//place in CENTER
		JPanel centerPnl = new JPanel();
		centerPnl.setLayout(new FlowLayout() );
		this.add(centerPnl, BorderLayout.CENTER);
		
		JRadioButton backButton = new JRadioButton("Background", true);
		centerPnl.add(backButton);
		JRadioButton foregroundButton = new JRadioButton("Foreground");
		centerPnl.add(foregroundButton);
		
		//place in SOUTH
		JPanel southPnl = new JPanel();
		this.add(southPnl, BorderLayout.SOUTH);
		JButton okBtn = new JButton("OK");
		southPnl.add(okBtn);
		JButton exchangeBtn = new JButton("Exchange");
		southPnl.add(exchangeBtn);
		JButton cancelBtn = new JButton("Cancel");
		southPnl.add(cancelBtn);
		
		
		
		this.setVisible(true);
		
	}//end constructor
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new ColorChooser1();

	}

}
//end class
