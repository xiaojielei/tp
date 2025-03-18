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
        IncomeManager.clearIncomeList();  // Reset income list before each test

        // Add some income entries
        IncomeManager.addIncome(new Income(200.0, "Freelance"));
        IncomeManager.addIncome(new Income(50.0, "Gift"));

        // Add income to the summary
        summary.addIncome(200.0);
        summary.addIncome(50.0);
    }

    @Test
    void testDeleteValidIncome() throws BudgetTrackerException {
        DeleteIncomeCommand command = new DeleteIncomeCommand(1, summary);  // Pass summary
        command.incomeExecute(IncomeManager.getInstance(), ui);  // Use incomeExecute instead of execute

        // Validate that the income list has the correct size and values after deletion
        assertEquals(1, IncomeManager.getIncomeList().size());
        assertEquals(50.0, IncomeManager.getIncomeList().get(0).getAmount());  // Use getter for amount
        assertEquals(50.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteInvalidIndexThrowsException() {
        // Try to delete an income with an invalid index (greater than the list size)
        Exception exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(5, summary).incomeExecute(IncomeManager.getInstance(), ui)  // Use incomeExecute instead of execute
        );
        assertEquals("Invalid index. Please provide a valid income index.", exception.getMessage());
    }
}

