package commands;

import expenses.Ui;
import exceptions.BudgetTrackerException;
import income.IncomeManager;

/**
 * Abstract base class for all income-related commands.
 */
public abstract class IncomeCommand {

    /**
     * Executes the income-related command.
     *
     * @param incomeList The {@link IncomeManager} instance managing income data.
     * @param ui The {@link Ui} instance responsible for displaying messages to the user.
     * @throws BudgetTrackerException If an error occurs during command execution.
     */
    public abstract void incomeExecute(IncomeManager incomeList, Ui ui) throws BudgetTrackerException;

    /**
     * Checks if the command should terminate the program.
     *
     * @return {@code true} if the command signals an exit, otherwise {@code false}.
     */
    public abstract boolean isExit();
}


