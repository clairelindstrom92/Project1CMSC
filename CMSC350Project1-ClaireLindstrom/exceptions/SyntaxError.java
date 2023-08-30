package exceptions;

// Our custom exception class for handling conversion errors.
public class SyntaxError extends Exception {

    // Default constructor: uses a generic error message.
    public SyntaxError() {
        super("Oops! Something went wrong with the expression conversion.");
    }

    // Constructor that accepts a custom error message.
    public SyntaxError(String message) {
        super(message);
    }
}
