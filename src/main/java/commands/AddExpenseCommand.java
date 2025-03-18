package commands;
import expenses.Expense;
import expenses.Ui;
import expenses.ExpenseList;

public class AddExpenseCommand extends Command {
    private double amount;
    private String description;

    public AddExpenseCommand(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        Expense newExpense = new Expense(amount, description);
        expenseList.addExpense(newExpense);
        ui.showMessage("Added expense: $" + amount + " | Source: " + description);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
