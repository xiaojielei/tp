package commands;

import exceptions.BudgetTrackerException;
import expenses.Expense;
import expenses.ExpenseList;
import expenses.Ui;
import expenses.Expense.Category;
import income.IncomeManager;
import summary.Summary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description = "Lunch";
        Category category = Expense.getCategoryFromInput("F");

        AddExpenseCommand command = new AddExpenseCommand(amount, description, category, summary);
        command.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain one expense.");
        assertEquals(amount, expenseList.getExpenses().get(0).getAmount(), "Amount should be the same.");
        assertEquals(description, expenseList.getExpenses().get(0).getDescription(), "Description should be the same.");
        assertEquals(category, expenseList.getExpenses().get(0).getCategory(), "Category should be TRANSPORT.");
    }
}
