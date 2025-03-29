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
    public void addExpense_withFood_expectExpenseAddedFood() throws BudgetTrackerException {
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
        assertEquals(category, expenseList.getExpenses().get(0).getCategory(), "Category should be FOOD.");
    }

    @Test
    public void addExpense_withTransport_expectExpenseAddedTransport() throws BudgetTrackerException {
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description = "MRT";
        Category category = Expense.getCategoryFromInput("T");

        AddExpenseCommand command = new AddExpenseCommand(amount, description, category, summary);
        command.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain one expense.");
        assertEquals(amount, expenseList.getExpenses().get(0).getAmount(), "Amount should be the same.");
        assertEquals(description, expenseList.getExpenses().get(0).getDescription(), "Description should be the same.");
        assertEquals(category, expenseList.getExpenses().get(0).getCategory(), "Category should be TRANSPORT.");
    }

    @Test
    public void addExpense_withBills_expectExpenseAddedBills() throws BudgetTrackerException {
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description = "Hostel bill";
        Category category = Expense.getCategoryFromInput("B");

        AddExpenseCommand command = new AddExpenseCommand(amount, description, category, summary);
        command.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain one expense.");
        assertEquals(amount, expenseList.getExpenses().get(0).getAmount(), "Amount should be the same.");
        assertEquals(description, expenseList.getExpenses().get(0).getDescription(), "Description should be the same.");
        assertEquals(category, expenseList.getExpenses().get(0).getCategory(), "Category should be BILLS.");
    }

    @Test
    public void addExpense_withOthers_expectExpenseAddedOthers() throws BudgetTrackerException {
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        double amount = 50.0;
        String description = "Shopping";
        Category category = Expense.getCategoryFromInput("O");

        AddExpenseCommand command = new AddExpenseCommand(amount, description, category, summary);
        command.execute(expenseList, ui);

        assertEquals(1, expenseList.getExpenses().size(), "Expense list should contain one expense.");
        assertEquals(amount, expenseList.getExpenses().get(0).getAmount(), "Amount should be the same.");
        assertEquals(description, expenseList.getExpenses().get(0).getDescription(), "Description should be the same.");
        assertEquals(category, expenseList.getExpenses().get(0).getCategory(), "Category should be OTHERS.");
    }

    @Test
    void addExpense_wrongCategory_expectException() throws BudgetTrackerException{
        AddIncomeCommand addIncome = new AddIncomeCommand(300.0, "salary", summary);
        addIncome.incomeExecute(IncomeManager.getInstance(), ui);

        assertThrows(BudgetTrackerException.class, () -> {
            Category category = Expense.getCategoryFromInput("M"); // This should trigger the exception
            AddExpenseCommand command = new AddExpenseCommand(50.0, "Shopping", category, summary);
            command.execute(expenseList, ui);
        });
    }

    @Test
    public void addExpense_noIncome_expectException() throws BudgetTrackerException{
        assertThrows(BudgetTrackerException.class, () -> {
            Category category = Expense.getCategoryFromInput("M"); // This should trigger the exception
            AddExpenseCommand command = new AddExpenseCommand(50.0, "Shopping", category, summary);
            command.execute(expenseList, ui);
        });
        assertEquals(0, expenseList.getExpenses().size(), "Expense list should contain no expenses.");
    }
}
