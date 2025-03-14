package summary;

import exceptions.BudgetTrackerException;

/**
 * Represents a financial summary, storing total income, expenses, balance, and savings.
 * Provides methods for adding and removing income, expenses, and savings.
 */
public class Summary {
    private double totalIncome;
    private double totalExpense;
    private double totalSavings;

    /**
     * Constructs a new Summary object with all values initialized to 0.
     */
    public Summary() {
        totalIncome = 0;
        totalExpense = 0;
        totalSavings = 0;
    }

    /**
     * Gets the total income.
     *
     * @return The total income.
     */
    public double getTotalIncome() {
        return totalIncome;
    }

    /**
     * Gets the total expenses.
     *
     * @return The total expenses.
     */
    public double getTotalExpense() {
        return totalExpense;
    }

    /**
     * Gets the total balance (calculated as income - expenses).
     *
     * @return The total balance.
     */
    public double getTotalBalance() {
        return totalIncome - totalExpense;
    }
    /**
     * Gets the total savings.
     *
     * @return The total savings.
     */
    public double getTotalSavings() {
        return totalSavings;
    }


    /**
     * Adds income to the total income.
     *
     * @param income The amount of income to add.
     * @throws BudgetTrackerException If the income is negative.
     */
    public void addIncome(double income) throws BudgetTrackerException {
        if (income < 0) {
            throw new BudgetTrackerException("Income cannot be negative.");
        }
        this.totalIncome += income;
    }

    /**
     * Removes income from the total income.
     *
     * @param income The amount of income to remove.
     * @throws BudgetTrackerException If the income is negative or greater than the current total income.
     */
    public void removeIncome(double income) throws BudgetTrackerException {
        if (income < 0) {
            throw new BudgetTrackerException("Income cannot be negative.");
        }
        if (income > this.totalIncome) {
            throw new BudgetTrackerException("Cannot remove more income than the current total income.");
        }
        this.totalIncome -= income;
    }

    /**
     * Adds an expense to the total expenses.
     *
     * @param expense The amount of the expense to add.
     * @throws BudgetTrackerException If the expense is negative.
     */
    public void addExpense(double expense) throws BudgetTrackerException {
        if (expense < 0) {
            throw new BudgetTrackerException("Expense cannot be negative.");
        }
        this.totalExpense += expense;
    }

    /**
     * Removes an expense from the total expenses.
     *
     * @param expense The amount of the expense to remove.
     * @throws BudgetTrackerException If the expense is negative or greater than the current total expenses.
     */
    public void removeExpense(double expense) throws BudgetTrackerException {
        if (expense < 0) {
            throw new BudgetTrackerException("Expense cannot be negative.");
        }
        if (expense > this.totalExpense) {
            throw new BudgetTrackerException("Cannot remove more expense than the current total expenses.");
        }
        this.totalExpense -= expense;
    }

    /**
     * Adds savings to the total savings.
     *
     * @param savings The amount of savings to add.
     * @throws BudgetTrackerException If the savings are negative or greater than available balance.
     */
    public void addSavings(double savings) throws BudgetTrackerException {
        if (savings < 0) {
            throw new BudgetTrackerException("Savings cannot be negative.");
        }
        if (savings > getTotalBalance()) {
            throw new BudgetTrackerException("Savings cannot be greater than available balance.");
        }
        this.totalSavings += savings;
    }

    /**
     * Removes savings from the total savings.
     *
     * @param savings The amount of savings to remove.
     * @throws BudgetTrackerException If the savings are negative or greater than the current total savings.
     */
    public void removeSavings(double savings) throws BudgetTrackerException {
        if (savings < 0) {
            throw new BudgetTrackerException("Savings cannot be negative.");
        }
        if (savings > this.totalSavings) {
            throw new BudgetTrackerException("Cannot remove more savings than the current total savings.");
        }
        this.totalSavings -= savings;
    }
}