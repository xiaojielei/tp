package commands;

import expenses.ExpenseList;
import expenses.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        ui.showMessage("Exiting the application. Goodbye!");
    }

    @Override
    public boolean isExit() {
        return true; // Indicates the program should exit
    }
}
