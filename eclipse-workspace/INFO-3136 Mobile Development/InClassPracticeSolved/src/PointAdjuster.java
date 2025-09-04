/**
 * Program Name:PointAdjuster.java
 * Purpose:Layout Managers Practice Sheet #1 question 2
 * Coder: Bill Pulling
 * Date: Apr 1, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PointAdjuster extends JFrame
{
	//constructor
	public PointAdjuster()
	{
		super ("Point Adjuster Box");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,150);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,3) );//use Grid layout for the frame to hold the panels
		
		//three JPanels
		JPanel pnlLeft = new JPanel();
		//pnlLeft.setBackground(Color.BLACK);		
		JPanel pnlCenter = new JPanel();
		//pnlCenter.setBackground(Color.RED);
		JPanel pnlRight = new JPanel();
		//pnlRight.setBackground(Color.GREEN);
		
		this.add(pnlLeft);
		this.add(pnlCenter);
		this.add(pnlRight);
		
		//components for left panel
		pnlLeft.setLayout(new GridLayout(2,1));
		JCheckBox chkBox1 = new JCheckBox("Snap to Grid");
		pnlLeft.add(chkBox1);
		JCheckBox chkBox2 = new JCheckBox("Flip points");
		pnlLeft.add(chkBox2);
		
		//components for center panel
		//NOTE: default layout for JPanel is FlowLayout
		pnlCenter.setLayout(new GridLayout(2,2));
		JLabel xLbl = new JLabel("X:");
		pnlCenter.add(xLbl);
		JTextField xFld = new JTextField("25");
		pnlCenter.add(xFld);
		JLabel yLbl = new JLabel("Y:");
		pnlCenter.add(yLbl);
		JTextField yFld = new JTextField("34");
		pnlCenter.add(yFld);
		
		//components for right panel
		//pnlRight.setLayout(new GridLayout(3,1,10,10));
		JButton okBtn = new JButton("OK");
		pnlRight.add(okBtn);
		JButton cancelBtn = new JButton("Cancel");
		pnlRight.add(cancelBtn);
		JButton helpBtn = new JButton("Help");
		pnlRight.add(helpBtn);
		
		//last line
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new PointAdjuster();
	}

}
//end class
