/**
 * Program Name: KeyListenerLoginFormExample.java
 * Purpose:  example that shows how keyboard events are processed using the KeyListener interface.
 * In this example only numeric input is accepted in the text field. Any other keyboard characters 
 * are "consumed" and will not show up. 
 * Coder: From JavaCodeGeeks.com, url is: //https://examples.javacodegeeks.com/a-complete-keylistener-java-example/
 * Date: July 8, 2024 //From JavaCodeGeeks.com

*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
public class KeyListenerLoginFormExample
{
    public static void main(String args[])
    {
        JPanel inputPanel = new JPanel();
        JFrame frame = new JFrame("Login Form");
        Container pane = frame.getContentPane();
        JLabel userNameInvalidLabel = new JLabel();
        JLabel userNameLabel = new JLabel("User Name");
        JTextField userNameTextField = new JTextField( 15);
        KeyListener listener = new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent event)
            {
                validateUserName(event);
            }
 
            @Override
            public void keyReleased(KeyEvent event) 
            {
 
            }
 
            @Override
            public void keyTyped(KeyEvent event) 
            {
                validateUserName(event);
            }
            private void validateUserName(KeyEvent event)
            {
                int num = event.getKeyChar();
                if(!Character.isDigit(event.getKeyChar())) 
                {
                    event.consume();//just eat it if it is not a digit.
                }
                if(event.getKeyChar()==KeyEvent.VK_ENTER) 
                {
                    userNameInvalidLabel.setText("Your username is valid!!");
                    userNameTextField.setText("");
                    event.consume();
                }
                else
                {
                    userNameInvalidLabel.setText("");;
                }
            }
        };
        userNameTextField.addKeyListener(listener);
        inputPanel.add(userNameLabel);
        inputPanel.add(userNameTextField);
        inputPanel.add(userNameInvalidLabel);
        pane.add(inputPanel, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,200);
        frame.setVisible(true);
 
    }
}