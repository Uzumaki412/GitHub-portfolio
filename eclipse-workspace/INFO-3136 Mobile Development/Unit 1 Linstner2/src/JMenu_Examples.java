/**
 * Program Name: JMenu_Examples.java
 * Purpose: shows the construction of various types of menus and sub menus and also accelerator keys.
 *          NOTE> all of the menu items are NON-FUNCTIONAL here. We are just showing you how you can
 *          build them. If you want to make them do something, then you need to write an inner class
 *          and add some listeners.
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */
import java.awt.*; //the original GUI package from Java 1.0
import java.awt.event.*;// for handling event objects
import javax.swing.*;// the BIG GUI UPGRADE from JDK 1.2

public class JMenu_Examples extends JFrame
{
	//CLASS WIDE SCOPE AREA
	private JMenuBar menuBar = new JMenuBar();
	
	//menu objects
	private JMenu fileMenu, elementMenu;
	
	//menu items for the file menu
	private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem;
	
	//items for the element menu
	private JRadioButtonMenuItem lineItem, rectangleItem;
	private JCheckBoxMenuItem redItem, blueItem, greenItem;
	
	Font font = new Font("Times New Roman", Font.PLAIN,36);

	//constuctor
	public JMenu_Examples()
	{
		//courtesy call to super and pass up string for title bar
		super("Examples of Various Menu Items and Features");
		
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout() );
		
		//start adding components
		
		JLabel welcomeLbl = new JLabel("Examples of Different Types of Menu Items...");
		welcomeLbl.setFont(font);
		this.add(welcomeLbl);
		
		//SET THE MENUBAR: DO NOT ADD IT TO THE JFRAME contentPane!
		this.setJMenuBar(menuBar);
		
		//create some File and Element menus
		fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		
		elementMenu = new JMenu("Element");
		elementMenu.setFont(font);
		
		//Set the MNEMONICS for each menu so that they can be opened from the keyboard
		fileMenu.setMnemonic('F');
		elementMenu.setMnemonic('E');
		
		//add the menus to the JMenuBar
		menuBar.add(fileMenu);
		menuBar.add(elementMenu);
		
		//construct the fileMenu items
		newItem = fileMenu.add("New");
		newItem.setFont(font);
		openItem = fileMenu.add("Open");
		openItem.setFont(font);
		closeItem = fileMenu.add("Close");
		closeItem.setFont(font);
		
		//add a separator bar for visual grouping
		fileMenu.addSeparator();
		
		//add remaining items
		printItem = fileMenu.add("Print");
		printItem.setFont(font);
		saveItem = fileMenu.add("Save");
		saveItem.setFont(font);
		saveAsItem = fileMenu.add("Save As");
		saveAsItem.setFont(font);
		
		//set some ACCELERATOR KEYS or HOT KEYS for new and open menu items
		newItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		
		//COnstruct the Element menu items
		elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle", true) );
		elementMenu.add(lineItem = new JRadioButtonMenuItem("Line", false) );
		
		//add a SUB-MENU for some color check box items
		JMenu colorMenu = new JMenu("Color");
		elementMenu.add(colorMenu);
		
		
		//create the items for the SUB-MENU
		redItem = new JCheckBoxMenuItem("Red",false);
		colorMenu.add(redItem);
		blueItem = new JCheckBoxMenuItem("Blue", false);
		colorMenu.add(blueItem);
		greenItem = new JCheckBoxMenuItem("Green", false);
		colorMenu.add(greenItem);
		//do one more ACCELERATOR KEY for the redItem
		redItem.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
	
		//last line
		this.setVisible(true);
		
	}//end constructor
	
	
	public static void main(String[] args)
	{
		// anonymous object to start it up
		new JMenu_Examples();
		
	}	//end main
}//end class