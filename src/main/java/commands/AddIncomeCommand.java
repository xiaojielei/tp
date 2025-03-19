package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import summary.Summary;
import expenses.Ui;

/**
 * Command to add a new income entry to the list and update the summary.
 */
public class AddIncomeCommand extends IncomeCommand {
    private final double amount;
    private final String source;
    private final Summary summary;

    /**
     * Creates a new AddIncomeCommand with the specified amount, source, summary, and ui.
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
     * Executes the command to add the income entry and updates the summary.
     *
     * @throws BudgetTrackerException if the amount is invalid or the source is empty
     */
    @Override
    public void incomeExecute(IncomeManager incomeManager, Ui ui) throws BudgetTrackerException {
        if (amount <= 0) {
            throw new BudgetTrackerException("Income amount must be a positive number.");
        }
        if (source == null || source.trim().isEmpty()) {
            throw new BudgetTrackerException("Income source cannot be empty.");
        }

        Income income = new Income(amount, source);
        IncomeManager.addIncome(income);  // Add to the income manager's list
        summary.addIncome(amount);  // Update the summary with the new income

        ui.showMessage("Added income: $" + amount + " from " + source); // Display success message using Ui
    }
    @Override
    public boolean isExit() {
        return false; // AddIncomeCommand does not exit the program
    }
}



