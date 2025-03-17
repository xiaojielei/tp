package commands;

import expenses.ExpenseList;
import expenses.Ui;

public class DeleteExpenseCommand extends Command {
    private int expenseNumber;

    public DeleteExpenseCommand(int expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        if (expenseList.deleteExpense(expenseNumber)) {
            ui.showMessage("Deleted expense number " + expenseNumber + ".");
        } else {
            ui.showMessage("There is no such expense number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
