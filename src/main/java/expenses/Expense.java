package expenses;

/**
 * Represents an expense with an amount and a description.
 */
import exceptions.BudgetTrackerException;

public class Expense {

    public enum Category {
        FOOD, TRANSPORT, BILLS, OTHERS
    }

    private double amount;
    private String description;
    private Category category;

    public Expense(double amount, String description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    /**
     * Retrieves the amount of the expense.
     *
     * @return The expense amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the description of the expense.
     *
     * @return The expense description.
     */
    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return A formatted string containing the expense amount, source and description.
     */
    @Override
    public String toString() {
        return "[" + category + "] " + amount + " from " + description;
    }

    public static Category getCategoryFromInput(String input) throws BudgetTrackerException {
        switch (input.toUpperCase()) {
        case "F":
            return Category.FOOD;
        case "T":
            return Category.TRANSPORT;
        case "B":
            return Category.BILLS;
        case "O":
            return Category.OTHERS;
        default:
            throw new BudgetTrackerException("Invalid category! Use: F (Food), T (Transport), B (Bills), O (Others).");
        }
    }
}
