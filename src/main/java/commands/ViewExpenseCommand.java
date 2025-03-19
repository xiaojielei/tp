package commands;

import expenses.ExpenseList;
import expenses.Ui;

/**
 * Represents a command to view all recorded expenses in the expense list.
 */
public class ViewExpenseCommand extends Command {
    private ExpenseList expenseList;

    /**
     * Constructs a ViewExpenseCommand with the specified expense list.
     *
     * @param expenseList The list of expenses to be displayed.
     */
    public ViewExpenseCommand(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    /**
     * Executes the command to display all expenses in the given expense list.
     *
     * @param expenseList The list of expenses to be shown.
     * @param ui          The UI component used to display messages to the user.
     */
    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        expenseList.showExpenses();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return {@code false} as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
