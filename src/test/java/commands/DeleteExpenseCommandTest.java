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
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void deleteExpense_validIndex_expectRemoval() throws BudgetTrackerException {
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description1 = "Lunch";
        String description2 = "Dinner";
        Category category = Expense.getCategoryFromInput("F"); // Convert "T" to TRANSPORT

        // Add two expenses
        AddExpenseCommand expense1 = new AddExpenseCommand(amount, description1, category, summary);
        AddExpenseCommand expense2 = new AddExpenseCommand(amount, description2, category, summary);
        expense1.execute(expenseList, ui);
        expense2.execute(expenseList, ui);

        // Ensure both expenses are added
        assertEquals(2, expenseList.getExpenses().size(), "Expense list should contain 2 expenses.");

        // Delete the first expense
        DeleteExpenseCommand command2 = new DeleteExpenseCommand(1, summary);
        command2.execute(expenseList, ui);

        // Ensure one expense remains
        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain 1 expense.");

        // Ensure the correct expense remains
        assertEquals(description2, expenseList.getExpenses().get(0).getDescription(),
                "Remaining expense is 'Dinner'.");
    }
}
