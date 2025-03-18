package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import summary.Summary;

/**
 * Command to add a new income entry to the list and update the summary.
 */
public class AddIncomeCommand {
    private final double amount;
    private final String source;
    private final Summary summary;

    /**
     * Creates a new AddIncomeCommand with the specified amount, source, and summary.
     *
     * @param amount the income amount
     * @param source the income source
     * @param summary the summary to update
     */
    public AddIncomeCommand(double amount, String source, Summary summary) {
        this.amount = amount;
        this.source = source;
        this.summary = summary;
    }

    /**
     * Executes the command to add the income entry.
     *
     * @throws BudgetTrackerException if the amount is invalid or the source is empty
     */
    public void execute() throws BudgetTrackerException {
        if (amount <= 0) {
            throw new BudgetTrackerException("Income amount must be a positive number.");
        }
        if (source == null || source.trim().isEmpty()) {
            throw new BudgetTrackerException("Income source cannot be empty.");
        }

        Income income = new Income(amount, source);
        IncomeManager.addIncome(income);
        summary.addIncome(amount);

        System.out.println("Added income: $" + amount + " from " + source);
    }
}
