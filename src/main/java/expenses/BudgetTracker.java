package expenses;

import commands.Command;

public class BudgetTracker {
    private ExpenseList expenseList;

    public BudgetTracker() {
        expenseList = new ExpenseList();
    }

    public void executeCommand(Command command, Ui ui) {
        command.execute(expenseList, ui);
    }


}
