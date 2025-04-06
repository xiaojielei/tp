package commands;

import exceptions.BudgetTrackerException;
import income.IncomeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summary.Summary;
import expenses.Ui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddIncomeCommandTest {
    private Summary summary;
    private Ui ui;
    private IncomeManager incomeManager;

    @BeforeEach
    void setUp() {
        summary = new Summary();
        ui = new Ui();
        incomeManager = IncomeManager.getInstance();
        IncomeManager.clearIncomeList(); // Reset income list before each test
    }

    @Test
    void testAddValidIncome() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        command.incomeExecute(incomeManager, ui);

        assertEquals(100.0, summary.getTotalIncome());
        assertFalse(IncomeManager.getIncomeList().isEmpty());
        assertEquals(100.0, IncomeManager.getIncomeList().get(0).getAmount());
        assertEquals("Salary", IncomeManager.getIncomeList().get(0).getSource());
    }

    @Test
    void testAddNegativeIncomeThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(-50.0, "Gift", summary)
        );
        assertEquals("Income amount must be greater than zero.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithEmptySourceThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(50.0, "", summary)
        );
        assertEquals("Income source cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithWhitespaceSourceThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(50.0, "   ", summary)
        );
        assertEquals("Income source cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithDecimalAmount() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(123.45, "Freelance", summary);
        command.incomeExecute(incomeManager, ui);

        assertEquals(123.45, summary.getTotalIncome());
        assertEquals("Freelance", IncomeManager.getIncomeList().get(0).getSource());
    }

    @Test
    void testAddMultipleValidIncomeEntries() throws BudgetTrackerException {
        AddIncomeCommand command1 = new AddIncomeCommand(100.0, "Job", summary);
        AddIncomeCommand command2 = new AddIncomeCommand(200.0, "Part-time", summary);

        command1.incomeExecute(incomeManager, ui);
        command2.incomeExecute(incomeManager, ui);

        assertEquals(2, IncomeManager.getIncomeList().size());
        assertEquals(300.0, summary.getTotalIncome());
    }

    @Test
    void testAddIncomeZeroAmountThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(0.0, "Allowance", summary)
        );
        assertEquals("Income amount must be greater than zero.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithNullSourceThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(100.0, null, summary)
        );
        assertEquals("Income source cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithLongSourceName() throws BudgetTrackerException {
        String longSource = "Freelance Work from Online Platforms Including Writing and Editing";
        AddIncomeCommand command = new AddIncomeCommand(250.0, longSource, summary);
        command.incomeExecute(incomeManager, ui);

        assertEquals(250.0, summary.getTotalIncome());
        assertEquals(longSource, IncomeManager.getIncomeList().get(0).getSource());
    }

    @Test
    void testAddIncomeWithLeadingAndTrailingSpacesInSource() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(90.0, "  Bonus  ", summary);
        command.incomeExecute(incomeManager, ui);

        assertEquals("  Bonus  ", IncomeManager.getIncomeList().get(0).getSource());
    }

    @Test
    void testAddIncomeWithNullSummaryThrowsException() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(50.0, "Gift", null)
        );
        assertEquals("Summary instance is required.", exception.getMessage());
    }

    @Test
    void testIncomeExecuteWithNullUiThrowsException() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                command.incomeExecute(incomeManager, null)
        );
        assertEquals("Ui instance cannot be null.", exception.getMessage());
    }

    @Test
    void testIncomeExecuteWithNullIncomeManagerThrowsException() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                command.incomeExecute(null, ui)
        );
        assertEquals("IncomeManager cannot be null.", exception.getMessage());
    }

    @Test
    void testSummaryReflectsCorrectIncomeAfterMultipleAdditions() throws BudgetTrackerException {
        AddIncomeCommand c1 = new AddIncomeCommand(100, "A", summary);
        AddIncomeCommand c2 = new AddIncomeCommand(150.50, "B", summary);
        AddIncomeCommand c3 = new AddIncomeCommand(49.50, "C", summary);

        c1.incomeExecute(incomeManager, ui);
        c2.incomeExecute(incomeManager, ui);
        c3.incomeExecute(incomeManager, ui);

        assertEquals(300.0, summary.getTotalIncome(), 0.001); // Floating point tolerance
    }
}





