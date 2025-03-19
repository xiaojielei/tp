package commands;

import expenses.ExpenseList;
import expenses.Ui;
import expenses.Expense;
import summary.Summary;
import exceptions.BudgetTrackerException;

public class DeleteExpenseCommand extends Command {
    private final int expenseNumber;
    private final Summary summary;

    public DeleteExpenseCommand(int expenseNumber, Summary summary) {
        this.expenseNumber = expenseNumber;
        this.summary = summary;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        try {
            // Check if the expense number is valid
            if (expenseNumber <= 0 || expenseNumber > expenseList.getExpenses().size()) {
                ui.showMessage("There is no such expense number.");
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
                ui.showMessage("There is no such expense number.");
            }
        } catch (BudgetTrackerException e) {
            ui.showMessage("Error deleting expense: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
