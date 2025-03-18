package income;

/**
 * Represents an income entry with an amount and a source.
 */
public record Income(double amount, String source) {
    /**
     * Gets the amount of the income.
     *
     * @return the income amount
     */
    public double amount() {
        return amount;
    }

    /**
     * Gets the source of the income.
     *
     * @return the income source
     */
    public String source() {
        return source;
    }
}
