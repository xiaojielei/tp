package expenses;

import commands.Command;
import commands.IncomeCommand;
import exceptions.BudgetTrackerException;
import income.IncomeManager;

public class BudgetTracker {
    private ExpenseList expenseList;

    public BudgetTracker() {
        expenseList = new ExpenseList();
    }

    public void executeCommand(Command command, Ui ui) {
        try {
            command.execute(expenseList, ui);
        } catch (BudgetTrackerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void executeincomeCommand(IncomeCommand incomeCommand, Ui ui) {
        try {
            incomeCommand.incomeExecute(new IncomeManager(), ui);
        } catch (BudgetTrackerException e) {
            // Handle the exception, for example, by printing the message
            System.out.println("Error: " + e.getMessage());
        }
    }


}
