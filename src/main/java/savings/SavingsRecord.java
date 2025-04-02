package savings;

/**
 * Represents a single savings record with an amount and an optional goal.
 */
public class SavingsRecord {
    double amount;
    String goal;

    /**
     * Constructs a SavingsRecord with a specified amount.
     * @param amount The savings amount.
     */
    public SavingsRecord(double amount) {
        this.amount = amount;
        this.goal = " ";
    }

    public double getAmount() {
        return amount;
    }

    public String getGoal() {
        return goal;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the goal for this savings record.
     * @param goal The goal description.
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "[" + goal + "] " + amount;
    }
}
