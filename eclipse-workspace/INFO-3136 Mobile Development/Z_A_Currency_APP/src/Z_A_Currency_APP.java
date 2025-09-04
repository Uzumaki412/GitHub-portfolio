/**
 * Program Name: CurrencyConverter.java
 * Purpose: Exhange US currency to Canadian Dollars using a GUI.
 * Author: Zaid Abu Shawarib 1196606
 * Date: Jul 16, 2025
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Z_A_Currency_APP extends JFrame {
	private JTextField usAmount, exchangeRate, cadAmount;
	private JButton calculateBtn, clearBtn;
	Container contentPane;
	
	public Z_A_Currency_APP() {
		super("Zaid's Currency Converter ");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(400,300); 
		this.setLocationRelativeTo(null);
	
		JPanel topPanel = new JPanel();
		JLabel topLbl = new JLabel("Enter your data in the fields below:");
		topPanel.add(topLbl);
		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,2,30,30));
		centerPanel.setBackground(Color.PINK);
		this.add(centerPanel, BorderLayout.CENTER);
		
		
		JLabel firstLbl = new JLabel("US Currnecy Amount:");
		firstLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel secondLbl = new JLabel("Current Exchange Rate:");
		secondLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel resultLbl = new JLabel("Value in Canadian Dollars:");		
		resultLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		usAmount = new JTextField();
		exchangeRate = new JTextField();
		cadAmount = new JTextField();
		
		cadAmount.setEditable(false); // make the result field read-only
		
		//add them to the centerPanel in this order:
		centerPanel.add(firstLbl);
		centerPanel.add(usAmount);
		centerPanel.add(secondLbl);
		centerPanel.add(exchangeRate);
		centerPanel.add(resultLbl);
		centerPanel.add(cadAmount);		

	
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout() );
		btnPanel.setBackground(Color.BLUE);
		this.add(btnPanel, BorderLayout.SOUTH);
		
		//add the buttons to bottom panel
		calculateBtn = new JButton("Calculate");
		clearBtn = new JButton("Clear");

		btnPanel.add(calculateBtn);
		btnPanel.add(clearBtn);

		ButtonListener nanny = new ButtonListener();//calling default constructor
		calculateBtn.addActionListener(nanny);
		clearBtn.addActionListener(nanny);
		
		this.pack();//packs the components into the smallest possible area.
		this.setVisible(true);		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try{
				DecimalFormat currencyFormat = new DecimalFormat("$0.00");
				String usAmountString = usAmount.getText();
				String exchangeRateString = exchangeRate.getText();
				double usAmountNum = Double.parseDouble(usAmountString);
				double exchangeRateNum = Double.parseDouble(exchangeRateString);
				double result = 0;
				
				if(ev.getSource().equals(calculateBtn)){
					result = usAmountNum / exchangeRateNum;
					cadAmount.setText(currencyFormat.format(result));
					}
				else if(ev.getSource().equals(clearBtn)){
					usAmount.setText("");
					exchangeRate.setText("");
					cadAmount.setText("");
					}
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(contentPane, "Please enter valid numeric values for US Amount and Exchange Rate.", "Input Error", JOptionPane.ERROR_MESSAGE);
					usAmount.setText("");
					exchangeRate.setText("");
					cadAmount.setText("");
					}
			}
		}//end ButtonListener class
	
	
	
	public static void main(String[] args) {
		new Z_A_Currency_APP();
	}

}
//end class
