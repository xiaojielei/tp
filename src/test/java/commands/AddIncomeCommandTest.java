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
}





