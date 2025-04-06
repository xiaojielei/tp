package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summary.Summary;
import expenses.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteIncomeCommandTest {
    private Summary summary;
    private Ui ui;

    @BeforeEach
    void setUp() throws BudgetTrackerException {
        summary = new Summary();
        ui = new Ui(); // Initialize the UI object
        IncomeManager.clearIncomeList();

        // Add some income entries
        IncomeManager.addIncome(new Income(200.0, "Freelance"));
        IncomeManager.addIncome(new Income(50.0, "Gift"));

        // Add income to the summary
        summary.addIncome(200.0);
        summary.addIncome(50.0);
    }

    @Test
    void testDeleteValidIncome() throws BudgetTrackerException {
        DeleteIncomeCommand command = new DeleteIncomeCommand(1, summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);

        // Validate that the income list has the correct size and values after deletion
        assertEquals(1, IncomeManager.getIncomeList().size());
        assertEquals(50.0, IncomeManager.getIncomeList().get(0).getAmount());
        assertEquals(50.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteInvalidIndexThrowsException() {
        // Try to delete an income with an invalid index (greater than the list size)
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(5, summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("Invalid index. Please provide a valid income index between 1 and 2.", exception.getMessage());
    }

    @Test
    void testDeleteEmptyListThrowsException() {
        IncomeManager.clearIncomeList(); // Make the list empty
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(1, summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("There are no income entries to delete.", exception.getMessage());
    }

    @Test
    void testDeleteLastIncomeEntry() throws BudgetTrackerException {
        DeleteIncomeCommand command1 = new DeleteIncomeCommand(2, summary);
        command1.incomeExecute(IncomeManager.getInstance(), ui);

        assertEquals(1, IncomeManager.getIncomeList().size());
        assertEquals("Freelance", IncomeManager.getIncomeList().get(0).getSource());
        assertEquals(200.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteIncomeIndexZeroThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(0, summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("Index must be at least 1.", exception.getMessage());
    }

    @Test
    void testDeleteIncomeNegativeIndexThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(-1, summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("Index must be at least 1.", exception.getMessage());
    }

    @Test
    void testDeleteIncomeWithNullSummaryThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(1, null)
        );
        assertEquals("Summary instance is required.", exception.getMessage());
    }

    @Test
    void testDeleteIncomeResultsInNegativeAvailableBalance() throws BudgetTrackerException {
        summary.addExpense(230.0); // Now balance would go negative if we remove 200.0

        DeleteIncomeCommand command = new DeleteIncomeCommand(1, summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);

        // Income not deleted, list still has 2 entries
        assertEquals(2, IncomeManager.getIncomeList().size());
        assertEquals(250.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteMultipleIncomesSequentially() throws BudgetTrackerException {
        // First delete
        DeleteIncomeCommand command1 = new DeleteIncomeCommand(1, summary);
        command1.incomeExecute(IncomeManager.getInstance(), ui);
        assertEquals(1, IncomeManager.getIncomeList().size());

        // Second delete
        DeleteIncomeCommand command2 = new DeleteIncomeCommand(1, summary);
        command2.incomeExecute(IncomeManager.getInstance(), ui);
        assertEquals(0, IncomeManager.getIncomeList().size());

        // Total income should now be 0
        assertEquals(0.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteIncomeWithSameAmountDifferentSource() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(50.0, "Allowance"));
        summary.addIncome(50.0);

        // Now index 2 = Gift ($50), index 3 = Allowance ($50)
        DeleteIncomeCommand command = new DeleteIncomeCommand(2, summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);

        assertEquals(2, IncomeManager.getIncomeList().size());
        assertEquals("Allowance", IncomeManager.getIncomeList().get(1).getSource());
    }

    @Test
    void testNullUiThrowsException() throws BudgetTrackerException {
        DeleteIncomeCommand command = new DeleteIncomeCommand(1, summary);
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                command.incomeExecute(IncomeManager.getInstance(), null)
        );
        assertEquals("Ui instance cannot be null.", exception.getMessage());
    }

}


