package commands;
import expenses.Expense;
import expenses.Ui;
import expenses.ExpenseList;
import summary.Summary;
import exceptions.BudgetTrackerException;

public class AddExpenseCommand extends Command {
    private double amount;
    private String description;
    private Summary summary;

    public AddExpenseCommand(double amount, String description, Summary summary) {
        this.amount = amount;
        this.description = description;
        this.summary = summary;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui) {
        try {
            Expense newExpense = new Expense(amount, description);
            expenseList.addExpense(newExpense);
            
            // Update the summary with the new expense
            summary.addExpense(amount);
            
            ui.showMessage("Added expense: $" + amount + " | Source: " + description);
        } catch (BudgetTrackerException e) {
            ui.showMessage("Error adding expense: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
