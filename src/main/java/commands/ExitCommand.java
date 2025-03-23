package commands;

import expenses.ExpenseList;
import expenses.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param expenseList The list of expenses (not used in this command).
     * @param ui          The UI component used to display messages to the user.
     */
    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        ui.showMessage("Exiting the application. Goodbye!");
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return {@code true} as this command causes the program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
