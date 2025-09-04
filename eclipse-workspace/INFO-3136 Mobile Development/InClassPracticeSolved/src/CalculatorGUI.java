/**
 * Program Name:CalculatorGUI.java
 * Purpose: Layout Managers practice sheet question 1 
 * Coder: Bill Pulling
 * Date: Apr 1, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CalculatorGUI extends JFrame
{
	//constructor
	public CalculatorGUI()
	{
		super("Calculator App");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout() );//use border layout for the frame
		
		//components
		
		JTextField txtfld = new JTextField("User entries would appear here...");
		this.add(txtfld, BorderLayout.NORTH);
		
		//make a sub container to hold the buttons
		JPanel btnPnl = new JPanel();
		btnPnl.setLayout(new GridLayout(4,4,10,10));
		this.add(btnPnl, BorderLayout.CENTER);
		//buttons for panel
		//top row
		JButton btn7 = new JButton("7");
		btnPnl.add(btn7);
		JButton btn8 = new JButton("8");
		btnPnl.add(btn8);
		JButton btn9 = new JButton("9");
		btnPnl.add(btn9);
		JButton btnDivide = new JButton("/");
		btnPnl.add(btnDivide);
		
		//second row
		JButton btn4 = new JButton("4");
		btnPnl.add(btn4);
		JButton btn5 = new JButton("5");
		btnPnl.add(btn5);
		JButton btn6 = new JButton("6");
		btnPnl.add(btn6);
		JButton btnMultiply = new JButton("*");
		btnPnl.add(btnMultiply);
		
		//third row
		JButton btn1 = new JButton("1");
		btnPnl.add(btn1);
		JButton btn2 = new JButton("2");
		btnPnl.add(btn2);
		JButton btn3 = new JButton("3");
		btnPnl.add(btn3);
		JButton btnSubtract = new JButton("-");
		btnPnl.add(btnSubtract);
		
		//bottom row
		JButton btn0 = new JButton("0");
		btnPnl.add(btn0);
		JButton btnPoint = new JButton(".");
		btnPnl.add(btnPoint);
		JButton btnEquals = new JButton("=");
		btnPnl.add(btnEquals);
		JButton btnPlus = new JButton("+");
		btnPnl.add(btnPlus);
		
		this.setVisible(true);
		
	}//end constructor
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
    new CalculatorGUI();
	}//end main
}//end class
