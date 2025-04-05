package summary;

import exceptions.BudgetTrackerException;
import alerts.FinancialObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a financial summary, storing total income, expenses, and savings.
 * Provides methods for adding and removing income, expenses, and savings.
 */
public class Summary {
    private double totalIncome;
    private double totalExpense;
    private double totalSavings;
    private List<FinancialObserver> observers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Summary.class.getName());

    /**
     * Constructs a new Summary object with all values initialized to 0.
     */
    public Summary() {
        totalIncome = 0;
        totalExpense = 0;
        totalSavings = 0;
        logger.log(Level.INFO, "Summary object initialized.");

        assert this.totalIncome == 0.0 : "Initial income should be zero";
        assert this.totalExpense == 0.0 : "Initial expense should be zero";
        assert this.totalSavings == 0.0 : "Initial savings should be zero";
    }

    /**
     * Registers an observer to be notified of financial changes.
     * 
     * @param observer The observer to register
     */
    public void registerObserver(FinancialObserver observer) {
        assert observer != null : "Cannot register a null observer";
        if (observer != null) {
            observers.add(observer);
            logger.log(Level.INFO, "Registered observer: " + observer.getClass().getName());
        } else {
            logger.log(Level.WARNING, "Attempted to register a null observer.");
        }
    }

    /**
     * Removes an observer from notification list.
     * 
     * @param observer The observer to remove
     */
    public void removeObserver(FinancialObserver observer) {
        assert observer != null : "Cannot remove a null observer";
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers about changes in financial data.
     */
    private void notifyObservers() {
        if (observers.isEmpty()) {
            logger.log(Level.WARNING, "notifyObservers called but no observers are registered.");
            return;
        }
        logger.log(Level.FINE, "Notifying " + observers.size() + " observers.");
        assert getAvailableFunds() >= 0 : "Available funds cannot be negative";
        double availableFunds = getAvailableFunds();
        for (FinancialObserver observer : observers) {
            try {
                observer.update(availableFunds, totalIncome, totalExpense, totalSavings);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception occurred while notifying observer: " 
                                        + observer.getClass().getName(), e);

            }
        }
    }

    /**
     * Gets the total income.
     *
     * @return The total income.
     */
    public double getTotalIncome() {
        assert this.totalIncome >= 0 : "Total income should never be negative";
        return totalIncome;
    }

    /**
     * Gets the total expenses.
     *
     * @return The total expenses.
     */
    public double getTotalExpense() {
        assert this.totalExpense >= 0 : "Total expense should never be negative";
        return totalExpense;
    }

    /**
     * Gets the available funds (calculated as income - expenses).
     * This represents the amount available for spending or saving.
     *
     * @return The available funds.
     */
    public double getAvailableFunds() {
        assert this.totalIncome - this.totalExpense >= 0 : "Calculated available funds are negative";
        return totalIncome - totalExpense;
    }

    /**
     * Gets the total savings.
     *
     * @return The total savings.
     */
    public double getTotalSavings() {
        assert this.totalSavings >= 0 : "Total savings should never be negative";
        return totalSavings;
    }

    /**
     * Adds income to the total income.
     *
     * @param income The amount of income to add.
     * @throws BudgetTrackerException If the income is non-positive.
     */
    public void addIncome(double income) throws BudgetTrackerException {
        if (income <= 0) {
            logger.log(Level.WARNING, "Attempted to add non-positive income: " + income);
            throw new BudgetTrackerException("Income must be positive.");
        }
        double oldIncome = this.totalIncome;
        this.totalIncome += income;
        logger.log(Level.INFO, "Total income updated to: " + totalIncome);

        assert this.totalIncome == oldIncome + income : "Income was not added correctly";
        
        notifyObservers();
    }

    /**
     * Removes income from the total income.
     *
     * @param income The amount of income to remove.
     * @throws BudgetTrackerException If the income is negative or greater than the current total income.
     */
    public void removeIncome(double income) throws BudgetTrackerException {
        if (income < 0) {
            logger.log(Level.WARNING, "Attempted to remove negative income: " + income);
            throw new BudgetTrackerException("Income must be positive.");
        }
        if (income > this.totalIncome) {
            logger.log(Level.WARNING, "Attempted to remove more income (" + income + ") than available (" + this.totalIncome + ").");
            throw new BudgetTrackerException("Cannot remove more income than the current total income.");
        }

        double availableBalance = getAvailableFunds();
        if (availableBalance - income < 0) {
            logger.log(Level.WARNING, "Attempted to remove more income (" + income + ") than available (" + availableBalance + ").");
            throw new BudgetTrackerException("Cannot remove this income "
                    + "as it would result in negative available funds. "
                    + "Current expenses: " + totalExpense + ", Available balance after removal would be: " 
                    + (availableBalance - income));
        }
        
        double oldIncome = this.totalIncome;
        this.totalIncome -= income;
        logger.log(Level.INFO, "Total income updated to: " + totalIncome);
        
        assert this.totalIncome == oldIncome - income : "Income was not removed correctly";
        assert this.totalIncome >= 0 : "Total income should never be negative after removal";
        
        notifyObservers();
    }

    /**
     * Adds an expense to the total expenses.
     *
     * @param expense The amount of the expense to add.
     * @throws BudgetTrackerException If the expense is negative or would result in a negative balance.
     */
    public void addExpense(double expense) throws BudgetTrackerException {
        if (expense <= 0) {
            logger.log(Level.WARNING, "Attempted to add non-positive expense: " + expense);
            throw new BudgetTrackerException("Expense must be positive.");
        }
        
        // Check if adding this expense would result in a negative balance
        double availableBalance = getAvailableFunds();
        if (expense > availableBalance) {
            logger.log(Level.WARNING, "Attempted to add expense (" + expense + ") that exceeds available funds (" + availableBalance + ").");
            throw new BudgetTrackerException("Cannot add this expense as it would exceed your available funds. "
                    + "Available balance: " + availableBalance);
        }
        double oldExpense = this.totalExpense;
        this.totalExpense += expense;
        logger.log(Level.INFO, "Total expenses updated to: " + totalExpense);

        assert this.totalExpense == oldExpense + expense : "Expense was not added correctly";
        assert this.totalExpense >= 0 : "Total expense should remain non-negative after addition";
        
        notifyObservers();
    }

    /**
     * Removes an expense from the total expenses.
     *
     * @param expense The amount of the expense to remove.
     * @throws BudgetTrackerException If the expense is negative or greater than the current total expenses.
     */
    public void removeExpense(double expense) throws BudgetTrackerException {
        if (expense <= 0) {
            logger.log(Level.WARNING, "Attempted to remove non-positive expense: " + expense);
            throw new BudgetTrackerException("Expense must be positive.");
        }
        if (expense > this.totalExpense) {
            logger.log(Level.WARNING, "Attempted to remove more expense (" + expense + ") than available (" + this.totalExpense + ").");
            throw new BudgetTrackerException("Cannot remove more expense than the current total expenses.");
        }
        double oldExpense = this.totalExpense;
        this.totalExpense -= expense;
        logger.log(Level.INFO, "Total expenses updated to: " + totalExpense);

        assert this.totalExpense == oldExpense - expense : "Expense was not removed correctly";
        assert this.totalExpense >= 0 : "Total expense should never be negative after removal";
        
        notifyObservers();
    }

    /**
     * Adds savings to the total savings.
     *
     * @param savings The amount of savings to add.
     * @throws BudgetTrackerException If the savings are negative.
     */
    public void addSavings(double savings) throws BudgetTrackerException {
        if (savings <= 0) {
            logger.log(Level.WARNING, "Attempted to add non-positive savings: " + savings);
            throw new BudgetTrackerException("Savings must be positive.");
        }

        double oldSavings = this.totalSavings;
        this.totalSavings += savings;
        logger.log(Level.INFO, "Total savings updated to: " + totalSavings);
 
        assert this.totalSavings == oldSavings + savings : "Savings were not added correctly";
        assert this.totalSavings >= 0 : "Total savings should remain non-negative after addition";
  
        notifyObservers();
    }

    /**
     * Removes savings from the total savings.
     *
     * @param savings The amount of savings to remove.
     * @throws BudgetTrackerException If the savings are negative or greater than the current total savings.
     */
    public void removeSavings(double savings) throws BudgetTrackerException {
        if (savings <= 0) {
            logger.log(Level.WARNING, "Attempted to remove non-positive savings: " + savings);
            throw new BudgetTrackerException("Savings must be positive.");
        }
        if (savings > this.totalSavings) {
            logger.log(Level.WARNING, "Attempted to remove more savings (" + savings + ") than available (" + this.totalSavings + ").");
            throw new BudgetTrackerException("Cannot remove more savings than the current total savings.");
        }
        double oldSavings = this.totalSavings;
        this.totalSavings -= savings;
        logger.log(Level.INFO, "Total savings updated to: " + totalSavings);
        
        assert this.totalSavings == oldSavings - savings : "Savings was not removed correctly";
        assert this.totalSavings >= 0 : "Total savings should never be negative after removal";
        
        notifyObservers();
    }
}
