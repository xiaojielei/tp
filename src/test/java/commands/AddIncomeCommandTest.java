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

    @BeforeEach
    void setUp() {
        summary = new Summary();
        ui = new Ui(); // Initialize the UI object
        IncomeManager.clearIncomeList(); // Reset income list before each test
    }

    @Test
    void testAddValidIncome() throws BudgetTrackerException {
        AddIncomeCommand command = new AddIncomeCommand(100.0, "Salary", summary);
        command.incomeExecute(IncomeManager.getInstance(), ui);  // Pass the IncomeManager instance instead of the list

        assertEquals(100.0, summary.getTotalIncome());
        assertFalse(IncomeManager.getIncomeList().isEmpty());
        assertEquals(100.0, IncomeManager.getIncomeList().get(0).getAmount());  // Use getter for amount
    }

    @Test
    void testAddNegativeIncomeThrowsException() {
        Exception exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(-50.0, "Gift", summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("Income amount must be a positive number.", exception.getMessage());
    }

    @Test
    void testAddIncomeWithEmptySourceThrowsException() {
        Exception exception = assertThrows(BudgetTrackerException.class, () ->
                new AddIncomeCommand(50.0, "", summary).incomeExecute(IncomeManager.getInstance(), ui)
        );
        assertEquals("Income source cannot be empty.", exception.getMessage());
    }
}




