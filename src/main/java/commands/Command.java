package commands;
import expenses.ExpenseList;
import expenses.Ui;
/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes the command with the given expense list and UI.
     *
     * @param expenseList The list of expenses to modify.
     * @param ui The UI handler for displaying messages.
     */
    public abstract void execute(ExpenseList expenseList, Ui ui);

    /**
     * Determines if this command should exit the application.
     *
     * @return True if this command exits the application, false otherwise.
     */
    public abstract boolean isExit();
}
