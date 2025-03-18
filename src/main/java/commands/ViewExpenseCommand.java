package commands;

import expenses.ExpenseList;
import expenses.Ui;

public class ViewExpenseCommand extends Command {

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        expenseList.showExpenses();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
