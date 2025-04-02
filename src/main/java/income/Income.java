package income;

/**
 * Represents an income entry with a specified amount and source.
 */
public class Income {
    private final double amount;
    private final String source;

    /**
     * Creates an Income object with the given amount and source.
     *
     * @param amount The amount of income.
     * @param source The source of the income.
     */
    public Income(double amount, String source) {
        this.amount = amount;
        this.source = source;
    }

    /**
     * Retrieves the income amount.
     *
     * @return The amount of this income entry.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the source of the income.
     *
     * @return The source of this income entry.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns a string representation of the income entry.
     *
     * @return A formatted string displaying the income amount and source.
     */
    @Override
    public String toString() {
        return "$" + amount + " from " + source;
    }
}

