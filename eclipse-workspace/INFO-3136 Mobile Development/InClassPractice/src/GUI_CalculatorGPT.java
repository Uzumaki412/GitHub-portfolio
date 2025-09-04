import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_CalculatorGPT extends JFrame {
    private JTextField displayField;
    private final String[] buttonLabels = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    };

    public GUI_CalculatorGPT() {
        super("Calculator App GPT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));

        ButtonListener listener = new ButtonListener();

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(listener);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String cmd = ev.getActionCommand();

            if ("0123456789.+-*/".contains(cmd)) {
                displayField.setText(displayField.getText() + cmd);
            } else if ("=".equals(cmd)) {
                try {
                    int result = evaluateExpression(displayField.getText());
                    displayField.setText(String.valueOf(result));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
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

        if (operatorIndex == -1) throw new Exception("No operator found");

        String left = expression.substring(0, operatorIndex).trim();
        String right = expression.substring(operatorIndex + 1).trim();
        char op = expression.charAt(operatorIndex);

        int a = Integer.parseInt(left);
        int b = Integer.parseInt(right);

        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new Exception("Division by zero");
                return a / b;
            default: throw new Exception("Unknown operator");
        }
    }

    public static void main(String[] args) {
        new GUI_CalculatorGPT();
    }
}
