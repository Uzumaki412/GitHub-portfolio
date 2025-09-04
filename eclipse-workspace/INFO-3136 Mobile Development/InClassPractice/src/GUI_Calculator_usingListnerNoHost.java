/**
 * Program Name: GUI_Calculator.java
 * Purpose: 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 15, 2025
 */

import java.awt.*; //the ORIGINAL Java GUI package library
import java.awt.event.*; //for handling EVENT objects created by users interacting with components
import javax.swing.*; // the GREAT LEAP FORWARD...the big GUI upgrade done in JDK 1.2

public class GUI_Calculator_usingListnerNoHost extends JFrame {
	private int textField = 0; // to store the result of calculations
	private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9,
			button0, buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonEquals, buttonDec;
	private JTextField displayField; // display field to show user input and results
	
	public GUI_Calculator_usingListnerNoHost() {
		super("Calculator App with No Host");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //destroy this object when close button is pressed
		this.setSize(300, 300); //width and height in pixels
		this.setLocationRelativeTo(null); //centres the JFrame on the screen.
		this.setLayout(new BorderLayout());
		
		displayField = new JTextField();
		displayField.setEditable(false); // make the display field read-only
		//displayField.setText("User entries will appear here");
		this.add(displayField, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4 , 10 ,10)); // 4 rows, 4 columns
		
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		button0 = new JButton("0");
		buttonAdd = new JButton("+");
		buttonSubtract = new JButton("-");
		buttonMultiply = new JButton("*");
		buttonDivide = new JButton("/");
		buttonEquals = new JButton("=");
		buttonDec = new JButton(".");
		
		buttonPanel.add(button7);
		buttonPanel.add(button8);
		buttonPanel.add(button9);
		buttonPanel.add(buttonDivide);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(buttonMultiply);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(buttonSubtract);
		buttonPanel.add(button0);
		buttonPanel.add(buttonDec);
		buttonPanel.add(buttonEquals);
		buttonPanel.add(buttonAdd);
	
		
		ButtonListener buttonListener = new ButtonListener(); 
		button7.addActionListener(buttonListener);
		button8.addActionListener(buttonListener);
		button9.addActionListener(buttonListener);
		button4.addActionListener(buttonListener);
		button5.addActionListener(buttonListener);
		button6.addActionListener(buttonListener);
		button1.addActionListener(buttonListener);
		button2.addActionListener(buttonListener);
		button3.addActionListener(buttonListener);
		button0.addActionListener(buttonListener);
		
		
		buttonSubtract.addActionListener(buttonListener);
		buttonMultiply.addActionListener(buttonListener);
		buttonDivide.addActionListener(buttonListener);
		buttonDec.addActionListener(buttonListener);
		buttonEquals.addActionListener(buttonListener);
		buttonAdd.addActionListener(buttonListener);
		
	
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI_Calculator_usingListnerNoHost calculator = new GUI_Calculator_usingListnerNoHost();
	}	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(ev.getActionCommand().equals("1")) {
				displayField.setText(displayField.getText() + "1");
			} else if(ev.getActionCommand().equals("2")) {
				displayField.setText(displayField.getText() + "2");
			} else if(ev.getActionCommand().equals("3")) {
				displayField.setText(displayField.getText() + "3");
			} else if(ev.getActionCommand().equals("4")) {
				displayField.setText(displayField.getText() + "4");
			} else if(ev.getActionCommand().equals("5")) {
				displayField.setText(displayField.getText() + "5");
			} else if(ev.getActionCommand().equals("6")) {
				displayField.setText(displayField.getText() + "6");
			} else if(ev.getActionCommand().equals("7")) {
				displayField.setText(displayField.getText() + "7");
			} else if(ev.getActionCommand().equals("8")) {
				displayField.setText(displayField.getText() + "8");
			} else if(ev.getActionCommand().equals("9")) {
				displayField.setText(displayField.getText() + "9");
			} else if(ev.getActionCommand().equals("0")) {
				displayField.setText(displayField.getText() + "0");
			}
			else if(ev.getActionCommand().equals("+")) {
				displayField.setText(displayField.getText() + "+");
			} else if(ev.getActionCommand().equals("-")) {
				displayField.setText(displayField.getText() + "-");
			} else if(ev.getActionCommand().equals("*")) {
				displayField.setText(displayField.getText() + "*");
			} else if(ev.getActionCommand().equals("/")) {
				displayField.setText(displayField.getText() + "/");
			} else if(ev.getActionCommand().equals("=")) {
				try {
					int result = evaluateExpression(displayField.getText());
					displayField.setText(String.valueOf(result));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error in calculation: " + e.getMessage());
				}
			} else if(ev.getActionCommand().equals(".")) {
				displayField.setText(displayField.getText() + ".");
			}
		}
	}
	
	private int evaluateExpression(String expression) throws Exception {
		int operatorIndex = -1;
		char[] operators = {'+', '-', '*', '/'};
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			for (char op : operators) {
				if (c == op) {
					operatorIndex = i;
					break;
					}
				}
		        if (operatorIndex != -1) break;
		    }
		    if (operatorIndex == -1) {
		        throw new Exception("No operator found");
		    }

		    String left = expression.substring(0, operatorIndex).trim();
		    String right = expression.substring(operatorIndex + 1).trim();
		    String operator = String.valueOf(expression.charAt(operatorIndex));

		    int firstOperand = Integer.parseInt(left);
		    int secondOperand = Integer.parseInt(right);
		    
		switch (operator) {
			case "+":
				return firstOperand + secondOperand;
			case "-":
				return firstOperand - secondOperand;
			case "*":
				return firstOperand * secondOperand;
			case "/":
				if (secondOperand == 0) {
					throw new Exception("Division by zero");
				}
				return firstOperand / secondOperand;
			default:
				throw new Exception("Unknown operator: " + operator);
				}
		}
}
//end class
