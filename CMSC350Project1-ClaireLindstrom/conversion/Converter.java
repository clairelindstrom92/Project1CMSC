// Claire Lindstrom CMSC350: Contains converter class which has conversion methods for postfix and prefix and the reverse
package conversion;

import exceptions.SyntaxError; // import exceptions.SyntaxError; to catch SyntaxError and display message
import java.util.Stack; // import java.util.Stack; to use Stack

public class Converter {

    /**
     * This method takes a prefix expression and turns it into its postfix
     * equivalent.
     * 
     * @param prefixExpression - The prefix math expression we're starting with.
     * @return The converted postfix expression.
     * @throws SyntaxError if the conversion encounters an error.
     */
    public static String convertPrefixToPostfix(String prefixExpression) throws SyntaxError {
        // this stack to reverse the order of tokens.
        Stack<String> tokenReversalStack = new Stack<>();
        // This stack builds postfix expression.
        Stack<String> intermediateResultsStack = new Stack<>();

        // Splitting the expression into individual tokens.
        String[] tokens = prefixExpression.split("\\s+");

        // Pushing tokens to the reversal stack, but skipping spaces.
        for (String currentToken : tokens) {
            if (!currentToken.equals(" ")) {
                tokenReversalStack.push(currentToken);
            }
        }

        // conversion process.
        while (!tokenReversalStack.isEmpty()) {
            String extractedToken = tokenReversalStack.pop();

            // If it's an operand, pushes it to the results stack.
            if (isOperand(extractedToken)) {
                intermediateResultsStack.push(extractedToken);
            } else {
                // If it's an operator, we need two operands to work with.
                if (intermediateResultsStack.size() < 2) {
                    throw new SyntaxError("Incorrect format.");
                }
                // Pop two operands, combine them with the operator, and push back to the stack.
                String firstOperand = intermediateResultsStack.pop();
                String secondOperand = intermediateResultsStack.pop();
                String combinedExpression = firstOperand + " " + secondOperand + " " + extractedToken;
                intermediateResultsStack.push(combinedExpression);
            }
        }
        // If there's more than one item left in the stack throw error
        if (intermediateResultsStack.size() != 1) {
            throw new SyntaxError("Looks like the expression's format is off.");
        }

        // pop the final postfix expression and return it.
        return intermediateResultsStack.pop();

    }

    /**
     * this method takes a postfix expression and turning it into prefix.
     * 
     * @param postfixExpression - the postfix math expression we're starting with
     * @return the new and shiny prefix expression
     * @throws SyntaxError if things don't go as planned during the conversion
     */
    public static String convertPostfixToPrefix(String postfixExpression) throws SyntaxError {
        // This stack will build the prefix expression.
        Stack<String> intermediateResultsStack = new Stack<>();

        // Splitting the expression into individual tokens.
        String[] tokens = postfixExpression.split("\\s+");

        // Looping through tokens to start the conversion.
        for (String currentToken : tokens) {
            // If it's an operand, push it to the stack.
            if (isOperand(currentToken)) {
                intermediateResultsStack.push(currentToken);
            } else {
                // If it's an operator, we need two operands to work with.
                if (intermediateResultsStack.size() < 2) {
                    throw new SyntaxError("Looks like the expression's format is off.");
                }
                // Pop two operands, combine them with the operator in prefix style, and push
                // back to the stack.
                String firstOperand = intermediateResultsStack.pop();
                String secondOperand = intermediateResultsStack.pop();
                String combinedExpression = currentToken + " " + firstOperand + " " + secondOperand;
                intermediateResultsStack.push(combinedExpression);
            }
        }

        // If there's more than one item left in the stack, throw error
        if (intermediateResultsStack.size() != 1) {
            throw new SyntaxError("Looks like the expression's format is off.");
        }

        // pop the final prefix expression and return it.
        return intermediateResultsStack.pop();
    }

    /**
     * Quick check: is the token an operand or an operator?
     * 
     * @param token - the token we're checking
     * @return true if it's an operand, false otherwise
     */
    public static boolean isOperand(String token) {
        // If it's one of these operators, it's not an operand.
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

}
