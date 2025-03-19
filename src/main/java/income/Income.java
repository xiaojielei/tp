package income;

public class Income {
    private final double amount;
    private final String source;

    public Income(double amount, String source) {
        this.amount = amount;
        this.source = source;
    }

    // Getter method for amount
    public double getAmount() {
        return amount;
    }

    // Getter method for source
    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Amount: $" + amount + " | Source: " + source;
    }
}

