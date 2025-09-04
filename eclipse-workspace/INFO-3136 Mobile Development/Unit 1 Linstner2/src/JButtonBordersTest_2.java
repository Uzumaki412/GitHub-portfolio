/**
 * Program Name: JButtonBordersTest_2 <br>
 * Purpose: shows creation of JButtons with various borders. <br>
 * Coder: downloaded from https://www.tutorialspoint.com/how-can-we-apply-different-borders-to-jbutton-in-java,
 *         modified by Bill Pulling, <br>
 * Coder: Bill Pulling 
 * Date: July 8, 2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//we'll need this one...
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class JButtonBordersTest_2 extends JFrame
{
  ;
   private JPanel panel;
   public JButtonBordersTest_2()
   {
      setTitle("JButton Borders Examples");
      panel = new JPanel();
      panel.setLayout(new GridLayout(12, 1,10,10));
           
      JButton button1 = new JButton("1: Line border in blue");      
      button1.setBorder(BorderFactory.createLineBorder(Color.blue));
      panel.add(button1);
      
      JButton button2 = new JButton("2: Lowered Bevel Border arg(0) ");
      button2.setBorder(BorderFactory.createBevelBorder(0));
      panel.add(button2);
      
      JButton button3 = new JButton("3: Raised Bevel Border arg(1) with red and blue ");
      button3.setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.blue));
      panel.add(button3);
      
      JButton button4 = new JButton("4: Raised Bevel Border with different colors on inner and outer edges.");
      button4.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.orange, Color.red, Color.blue));
      panel.add(button4);
      
      JButton button5 = new JButton("5: Empty border with no drawing but spacing of 10 pixels on each side.");
      button5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      panel.add(button5);		
      
      JButton button6 = new JButton("6: Etched border using the zero arg constructor");
      button6.setBorder(BorderFactory.createEtchedBorder());
      panel.add(button6);
      
      JButton button7 = new JButton("7: Etched border with black highlight and YELLOW shadow");
      button7.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.YELLOW) );
      panel.add(button7);
      
      JButton button8 = new JButton("8: Etched border (RAISED) with black highlight and YELLOW shadow");
      button8.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.YELLOW) );
      panel.add(button8);
      
      JButton button9 = new JButton("9: Etched border (LOWERED) with black highlight and YELLOW shadow");
      button9.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.YELLOW) );
      panel.add(button9);
      
      JButton button10 = new JButton("10: Titled border with default border style");
      button10.setBorder(BorderFactory.createTitledBorder("Titled Border") );
      panel.add(button10);
      
      JButton button11 = new JButton("11: Etched Titled border with centred Title...");
      button11.setBorder(BorderFactory.createTitledBorder(
      		               BorderFactory.createEtchedBorder(getBackground(), getBackground()), 
      		               "Etched Titled Border",
      		               TitledBorder.CENTER,
      		               TitledBorder.TOP )		  );
      panel.add(button11);
      
      add(panel, BorderLayout.CENTER);
      setSize(600, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   public static void main(String[] args)
   {
      new JButtonBordersTest_2();
   }
}