package commands;

import expenses.ExpenseList;
import expenses.Ui;
import expenses.Expense;
import summary.Summary;
import exceptions.BudgetTrackerException;

/**
 * Represents a command to delete an expense from the expense list.
 */
public class DeleteExpenseCommand extends Command {
    private final int expenseNumber;
    private final Summary summary;

    /**
     * Constructs a DeleteExpenseCommand with the specified expense index and summary.
     *
     * @param expenseNumber The index of the expense to be deleted (1-based).
     * @param summary       The summary object to update after deletion.
     */
    public DeleteExpenseCommand(int expenseNumber, Summary summary) {
        this.expenseNumber = expenseNumber;
        this.summary = summary;
    }

    /**
     * Executes the command to delete an expense from the given expense list and updates the summary.
     *
     * @param expenseList The list of expenses from which the expense will be deleted.
     * @param ui          The UI component used to display messages to the user.
     */
    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        try {
            // Check if the expense number is valid
            if (expenseNumber <= 0 || expenseNumber > expenseList.getExpenses().size()) {
                ui.showMessage("Invalid index. Please use an index from the list.");
                return;
            }
            
            // Get the expense amount before deleting it
            Expense expenseToDelete = expenseList.getExpenses().get(expenseNumber - 1);
            double amountToRemove = expenseToDelete.getAmount();
            
            // Delete the expense from the list
            if (expenseList.deleteExpense(expenseNumber)) {
                // Update the summary by removing the expense amount
                summary.removeExpense(amountToRemove);
                ui.showMessage("Deleted expense number " + expenseNumber + ".");
            } else {
                ui.showMessage("Invalid index. Please use an index from the list.");
            }
        } catch (BudgetTrackerException e) {
            ui.showMessage("Error deleting expense: " + e.getMessage());
        }
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
