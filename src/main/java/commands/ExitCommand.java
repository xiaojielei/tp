package commands;

import expenses.ExpenseList;
import expenses.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
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
        return true; // Indicates the program should exit
    }
}
