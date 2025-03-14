package exceptions;

/**
 * Exception thrown when invalid data is encountered in the Budget Tracker application.
 */
public class BudgetTrackerException extends Exception {
    /**
     * Constructs a new InvalidDataException with the specified detail message.
     *
     * @param message the detail message
     */
    public BudgetTrackerException(String message) {
        super(message);
    }
}
