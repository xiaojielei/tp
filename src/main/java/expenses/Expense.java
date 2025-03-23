package expenses;

/**
 * Represents an expense with an amount and a description.
 */
public class Expense {
    private double amount;
    private String description;

    /**
     * Constructs an Expense with the specified amount and description.
     *
     * @param amount      The monetary value of the expense.
     * @param description The description or source of the expense.
     */
    public Expense(double amount, String description) {
        this.amount = amount;
        this.description = description;
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

    /**
     * Returns a string representation of the expense.
     *
     * @return A string containing the expense amount and source.
     */
    @Override
    public String toString() {
        return "Amount: $" + amount + " | Source: " + description;
    }
}
