package commands;

import exceptions.BudgetTrackerException;
import income.IncomeManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import expenses.ExpenseList;
import expenses.Ui;
import summary.Summary;

import javax.swing.text.View;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeleteExpenseCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Summary summary;

    @BeforeEach
    void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        summary = new Summary();

    }

    @Test
    public void testDeleteExpense() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description = "Lunch";
        AddExpenseCommand expense1 = new AddExpenseCommand(50.0, "Lunch", summary);
        AddExpenseCommand expense2 = new AddExpenseCommand(50.0, "Dinner", summary);
        expense1.execute(expenseList, ui);
        expense2.execute(expenseList, ui);

        ViewExpenseCommand view = new ViewExpenseCommand(expenseList);
        view.execute(expenseList, ui);
        DeleteExpenseCommand command2 = new DeleteExpenseCommand(1, summary);
        command2.execute(expenseList, ui);

        view.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain 1 expenses.");

    }
}
