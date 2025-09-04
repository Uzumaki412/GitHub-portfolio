/**
 * Program Name: GUI_Calculator.java
 * Purpose: 
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 15, 2025
 */

import java.awt.*; //the ORIGINAL Java GUI package library
import java.awt.event.*; //for handling EVENT objects created by users interacting with components
import javax.swing.*; // the GREAT LEAP FORWARD...the big GUI upgrade done in JDK 1.2

public class GUI_Calculator extends JFrame {
	private int result = 0; // to store the result of calculations
	
	
	public GUI_Calculator() {
		super("Calculator App");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //destroy this object when close button is pressed
		this.setSize(300, 300); //width and height in pixels
		this.setLocationRelativeTo(null); //centres the JFrame on the screen.
		this.setLayout(new BorderLayout());
		
		JTextField displayField = new JTextField();
		displayField.setEditable(false); // make the display field read-only
		// displayField.setText("User entries will appear here");
		this.add(displayField, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4 , 10 ,10)); // 4 rows, 4 columns
		JButton button7 = new JButton("7");
		buttonPanel.add(button7);
		JButton button8 = new JButton("8");
		buttonPanel.add(button8);
		JButton button9 = new JButton("9");
		buttonPanel.add(button9);
		JButton buttonDivide = new JButton("/");
		buttonPanel.add(buttonDivide);
		JButton button4 = new JButton("4");
		buttonPanel.add(button4);
		JButton button5 = new JButton("5");
		buttonPanel.add(button5);
		JButton button6 = new JButton("6");
		buttonPanel.add(button6);
		JButton buttonMultiply = new JButton("*");
		buttonPanel.add(buttonMultiply);
		JButton button1 = new JButton("1");
		buttonPanel.add(button1);
		JButton button2 = new JButton("2");
		buttonPanel.add(button2);
		JButton button3 = new JButton("3");
		buttonPanel.add(button3);
		JButton buttonSubtract = new JButton("-");
		buttonPanel.add(buttonSubtract);
		JButton button0 = new JButton("0");
		buttonPanel.add(button0);
		JButton buttonDec = new JButton(".");
		buttonPanel.add(buttonDec);
		JButton buttonEquals = new JButton("=");
		buttonPanel.add(buttonEquals);
		JButton buttonAdd = new JButton("+");
		buttonPanel.add(buttonAdd);
		
		this.add(buttonPanel, BorderLayout.CENTER);
		
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "7");
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "8");
			}
		});
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "9");
			}
		});
		buttonDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "/");
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "4");
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "5");
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "6");
			}
		});
		buttonMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "*");
			}
		});
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "1");
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "2");
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "3");
			}
		});
		buttonSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "-");
			}
		});
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "0");
			}
		});
		buttonDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + ".");
			}
		});
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayField.setText(displayField.getText() + "+");
			}
		});
		buttonEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String expression = displayField.getText();
				try {
					result = evaluateExpression(expression);
					displayField.setText(String.valueOf(result));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(GUI_Calculator.this, "Invalid expression: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI_Calculator calculator = new GUI_Calculator();
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

		    String left = expression.substring(0,operatorIndex).trim();
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
