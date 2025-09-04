/**
 * Program Name: WorldCitiesController.java
 * Purpose: This is a controller of WorldCities Application.
 *  				It takes 2 inputs from user(country name, min population)and executes a query.
 * Coder: Bill Pulling
 * REVISION AUG 3/2021: revised the input view
 * 
 * July 21, 2025       
 */

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorldCitiesController extends JFrame
{
	// listener related components
	private JTextField country = new JTextField(20);
	private JTextField minPopulation = new JTextField(20);
	private JButton executeBtn = new JButton("Execute Query");
	
	// standard boilerplate code often seen in JDBC apps
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRslt = null;
	
	// PreparedStatement object and strings for connection
	PreparedStatement myPrepStmt = null;
	String URL = "jdbc:mysql://localhost:3306/world?useSSL=false&allowPublicKeyRetrieval=true";
	String user = "root";
	String password = "password";

	// viewer reference here so we can reference it down in the actionPerformed() method
	static WorldCitiesViewer viewer;
	
	//CONSTRUCTOR
	public WorldCitiesController() throws HeadlessException
	{
		// courtesy call
		super("Bill's World Cities Controller GUI");
		
		// boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		
		// create panels and add components
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,2));
		JPanel panel1 = new JPanel(new GridLayout(2,1,10,10));
		panel1.add(new JLabel("Enter country name below:"));
		panel1.add(country);
		panel1.setBackground(Color.CYAN);
		JPanel panel2 = new JPanel(new GridLayout(2,1,10,10));
		panel2.add(new JLabel("Enter minimum city population below:"));
		panel2.add(minPopulation);
		panel2.setBackground(Color.PINK);
		JPanel panel3 = new JPanel(new FlowLayout());
		panel3.add(executeBtn);
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		
		// REGISTER THE LISTENER
		Listener listener = new Listener();
		executeBtn.addActionListener(listener);
		
		// add panels to the JFrame
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(panel3, BorderLayout.SOUTH);
		//this.pack();
		//last line
		this.setVisible(true);
	}
	
	// inner class ActionListener
	private class Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			// When "Execute Query" button is clicked
			if(ev.getActionCommand().equals("Execute Query")) 
			{
				// Both country and min population is typed
				if(country.getText().length() > 0 && minPopulation.getText().length() > 0)
				{
					try
					{
						// create a Connection object by passing in the appropriate database URL, username, 
						// and password as String argument
						myConn = DriverManager.getConnection(URL, user, password);
						
						// create a PreparedStatement object 
						myPrepStmt = myConn.prepareStatement("SELECT city.name, city.district, city.population"
											+ " FROM city INNER JOIN country ON city.CountryCode = Country.code"
											+ " WHERE country.name = ? AND city.population > ?");
						
						// assign the parameters here
						myPrepStmt.setString(1, country.getText());
						myPrepStmt.setInt(2, Integer.parseInt(minPopulation.getText()));
						
						// RUN the query and assign the returned ResultSet object to myRslt
						myRslt = myPrepStmt.executeQuery();
						
						// call the DbUtils method resultSetToTableModel() and catch the returned TableModel object
						TableModel model = DbUtils.resultSetToTableModel(myRslt);
						
						// pass the TableModel object to the JTableView (only 1 view should be opened in time)
						if(viewer == null)
							//if this is run for the first time, the reference to viewer is null, so create it.
							viewer = new WorldCitiesViewer(model);//we create it here
						//else if(viewer.isVisible() == false)
							//viewer.repaint();
						else //just update the model used by the viewer
							 viewer.table.setModel(model);
						
					}
					catch(SQLException ex)
					{
						System.out.println("SQLException caught, message is: " + ex.getMessage());
						ex.printStackTrace();
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Number format exception, message is " + ex.getMessage());
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Enter numeric values for min population");
					}
					catch(Exception ex)
					{
						System.out.println("Exception message is " + ex.getMessage());
						ex.printStackTrace();
					}
					finally
					{
						try
							{
								// closing
								if(myRslt != null)
									myRslt.close();
								if(myStmt != null)
									myStmt.close();
								if(myConn != null)
									myConn.close();
							}//end try
						catch(SQLException ex)
						{
							ex.printStackTrace();
						}
					}//end finally
				}
				else
				{
					// If country is not typed
					if(country.getText().length() <= 0)
					{
						JOptionPane.showMessageDialog(null, "Please input country.");
						country.requestFocus();
					}
					// If min population is not typed
					else if(minPopulation.getText().length() <= 0)
					{
						JOptionPane.showMessageDialog(null, "Please input min population.");
						minPopulation.requestFocus();
					}
				}
			}
			
		} // end of actionPerformed
	} // end of Listener

	public static void main(String[] args)
	{
		new WorldCitiesController();
	}

}
