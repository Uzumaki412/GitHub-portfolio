/**
 * Program Name: Supertech_CellPhone.java
 * Purpose: simulate a simple cellphone and its various functions
 * Coder: Chris McLatchie 0722315
 * Date: Apr 7, 2015
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Supertech_CellPhone extends JFrame{
	//class scope variables
	private String phoneNumber = "";
	private boolean message;
	private String textMessage;
	//class scope objects
	private JTextArea textArea;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnFont, btnClear, btnSend, btnText, btnEnd;
	private Font normalFont;
	private Font largeFont;
	
	
	//constructor
	public Supertech_CellPhone(){
		super("Cell Phone");
		this.setSize(225, 500);
		this.setLayout(new GridLayout(2, 1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		normalFont = new Font("SansSerif", Font.PLAIN, 12);
		largeFont = new Font("Serif", Font.BOLD, 18);
		
		
		
		//create top section
		//create objects
		textArea = new JTextArea();
		textArea.setText("Supertech Cell Phone App\n\n----------NO SERVICE----------");
		textArea.setFont(normalFont);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.BLACK);
		//add objects to frame
		this.add(textArea);
		
		//create bottom section
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(5, 3));
		//create objects
		btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");
		btn6 = new JButton("6");
		btn7 = new JButton("7");
		btn8 = new JButton("8");
		btn9 = new JButton("9");
		btn0 = new JButton("0");
		btnFont = new JButton("Font");
		btnClear = new JButton("Clear");
		btnSend = new JButton("Send");
		btnText = new JButton("Text");
		btnEnd = new JButton("End");
		//add objects to panel
		botPanel.add(btn1);
		botPanel.add(btn2);
		botPanel.add(btn3);
		botPanel.add(btn4);
		botPanel.add(btn5);
		botPanel.add(btn6);
		botPanel.add(btn7);
		botPanel.add(btn8);
		botPanel.add(btn9);
		botPanel.add(btn0);
		botPanel.add(btnFont);
		botPanel.add(btnClear);
		botPanel.add(btnSend);
		botPanel.add(btnText);
		botPanel.add(btnEnd);
		//add panel to frame
		this.add(botPanel);
		
		//register action listeners
		btn1.addActionListener(new NumberButtonHandler());
		btn2.addActionListener(new NumberButtonHandler());
		btn3.addActionListener(new NumberButtonHandler());
		btn4.addActionListener(new NumberButtonHandler());
		btn5.addActionListener(new NumberButtonHandler());
		btn6.addActionListener(new NumberButtonHandler());
		btn7.addActionListener(new NumberButtonHandler());
		btn8.addActionListener(new NumberButtonHandler());
		btn9.addActionListener(new NumberButtonHandler());
		btn0.addActionListener(new NumberButtonHandler());
		btnFont.addActionListener(new OtherButtonHandler());
		btnClear.addActionListener(new OtherButtonHandler());
		btnSend.addActionListener(new OtherButtonHandler());
		btnText.addActionListener(new OtherButtonHandler());
		btnEnd.addActionListener(new OtherButtonHandler());
		
		//last line
		this.setVisible(true);
	}
	
	
	
	//inner classes
	//for number buttons
	private class NumberButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//only add if number length is less than 10
			if (phoneNumber.length() < 10)
				phoneNumber += ((JButton)e.getSource()).getText();
			textArea.setText(phoneNumber);
			//special output of number length is 10
			if (phoneNumber.length() == 10)
				textArea.setText(phoneNumber + "\nSend Call?");
		}
	}
	
	//for other buttons
	private class OtherButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//swap fonts
			if (e.getSource() == btnFont){
				if(textArea.getFont() == normalFont)
					textArea.setFont(largeFont);
				else
					textArea.setFont(normalFont);
			}
			//reset program
			else if (e.getSource() == btnClear){
				textArea.setText("Supertech Cell Phone App\n\n----------NO SERVICE----------");
				textArea.setFont(normalFont);
				phoneNumber = "";
				textArea.setEnabled(false);
				textArea.setWrapStyleWord(false);
				textArea.setLineWrap(false);
			}
			//display sending text
			else if (e.getSource() == btnSend){
				textArea.setText("Dialing the number...");
				textArea.setEnabled(false);
				textArea.setWrapStyleWord(false);
				textArea.setLineWrap(false);
			}
			//enable text messaging (unfinished)
			else if (e.getSource() == btnText){
				textArea.setText("");
				textArea.setEnabled(true);
				textArea.setWrapStyleWord(true);
				textArea.requestFocus();
				textArea.setLineWrap(true);
			}
			//display ending text
			else if (e.getSource() == btnEnd){
				textArea.setText("Call ended...");
				textArea.setEnabled(false);
				textArea.setWrapStyleWord(false);
				textArea.setLineWrap(false);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		//anonymous object
		new Supertech_CellPhone();
	}
}
