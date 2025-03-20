package commands;

import exceptions.BudgetTrackerException;
import income.IncomeManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import expenses.ExpenseList;
import expenses.Ui;
import summary.Summary;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddExpenseCommandTest {
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
    public void testAddExpense() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);
        double amount = 50.0;
        String description = "Lunch";
        AddExpenseCommand command1 = new AddExpenseCommand(amount, description, summary);

        command1.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain one expense.");
        assertEquals(amount, expenseList.getExpenses().get(0).getAmount(), "Amount should be the same.");
        assertEquals(description, expenseList.getExpenses().get(0).getDescription(), "Description should be the same.");
    }
}
