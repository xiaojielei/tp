package commands;

import expenses.Ui;
import exceptions.BudgetTrackerException;
import income.IncomeManager;

/**
 * Abstract base class for all income-related commands.
 */
public abstract class IncomeCommand {

    /**
     * Executes the command with the given income manager and UI.
     *
     * @param incomeList The manager handling income data.
     * @param ui The UI handler for displaying messages.
     * @throws BudgetTrackerException If an error occurs during execution.
     */
    public abstract void incomeExecute(IncomeManager incomeList, Ui ui) throws BudgetTrackerException;
    public abstract boolean isExit();
}


