package expenses;

import commands.Command;
import commands.IncomeCommand;
import exceptions.BudgetTrackerException;
import income.IncomeManager;

/**
 * The BudgetTracker class manages expenses and income commands.
 */
public class BudgetTracker {
    private ExpenseList expenseList;

    /**
     * Constructs a BudgetTracker with an empty expense list.
     */
    public BudgetTracker() {
        expenseList = new ExpenseList();
    }

    /**
     * Executes a general expense-related command.
     *
     * @param command The command to execute.
     * @param ui      The UI component for displaying messages.
     */
    public void executeCommand(Command command, Ui ui) {
        try {
            command.execute(expenseList, ui);
        } catch (BudgetTrackerException e) {
            // Handle the exception by printing the message
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes an income-related command.
     *
     * @param incomeCommand The income command to execute.
     * @param ui            The UI component for displaying messages.
     */
    public void executeincomeCommand(IncomeCommand incomeCommand, Ui ui) {
        try {
            incomeCommand.incomeExecute(new IncomeManager(), ui);
        } catch (BudgetTrackerException e) {
            // Handle the exception by printing the message
            System.out.println("Error: " + e.getMessage());
        }
    }


}
