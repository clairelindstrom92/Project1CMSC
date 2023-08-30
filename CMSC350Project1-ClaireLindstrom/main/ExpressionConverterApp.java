// Claire Lindstrom CMSC350: Contains main method which has GUI

package main;

import javax.swing.*; //imports swing components
import java.awt.*; // abstract window toolkit
import java.awt.event.ActionEvent; //event class for handling clicks
import java.awt.event.ActionListener; //event class for handling clicks
import conversion.Converter;
import exceptions.SyntaxError;

public class ExpressionConverterApp {

    private JFrame mainFrame; // window/frame
    private JTextField userInputField; // user input field
    private JButton prefixToPostfixButton; // triggers prefix to postfix conversion
    private JButton postfixToPrefixButton; // triggers postfix to prefix conversion
    private JLabel resultLabel; // displays results of conversion

    public ExpressionConverterApp() {
        // Setting up the main window
        mainFrame = new JFrame("Prefix and Postfix Converter");
        mainFrame.setSize(400, 250);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        userInputField = new JTextField(20);
        prefixToPostfixButton = new JButton("Prefix to Postfix");
        postfixToPrefixButton = new JButton("Postfix to Prefix");
        resultLabel = new JLabel("Result");

        // Adding components to the frame
        mainFrame.add(new JLabel("Drop your expression here:"));
        mainFrame.add(userInputField);
        mainFrame.add(prefixToPostfixButton);
        mainFrame.add(postfixToPrefixButton);
        mainFrame.add(resultLabel);

        // Logic for Prefix to Postfix button
        prefixToPostfixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String userExpression = userInputField.getText().trim();
                    String postfixMagic = Converter.convertPrefixToPostfix(userExpression);
                    resultLabel.setText("Postfix: " + postfixMagic);
                } catch (SyntaxError ex) {
                    resultLabel.setText(ex.getMessage());
                }
            }
        });

        // Logic for Postfix to Prefix button
        postfixToPrefixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String userExpression = userInputField.getText().trim();
                    String prefixMagic = Converter.convertPostfixToPrefix(userExpression);
                    resultLabel.setText("Prefix: " + prefixMagic);
                } catch (SyntaxError ex) {
                    resultLabel.setText(ex.getMessage());
                }
            }
        });

        // makes the GUI Visible
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Keeps the GUI creation thread-safe.
        SwingUtilities.invokeLater(() -> new ExpressionConverterApp());
    }
}
