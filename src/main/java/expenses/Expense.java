package expenses;

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

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

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
