package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summary.Summary;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteIncomeCommandTest {
    private Summary summary;

    @BeforeEach
    void setUp() throws BudgetTrackerException {
        summary = new Summary();
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(new Income(200.0, "Freelance"));
        IncomeManager.addIncome(new Income(50.0, "Gift"));
        summary.addIncome(200.0);
        summary.addIncome(50.0);
    }

    @Test
    void testDeleteValidIncome() throws BudgetTrackerException {
        DeleteIncomeCommand command = new DeleteIncomeCommand(1, summary); // Pass summary
        command.execute();

        assertEquals(1, IncomeManager.getIncomeList().size());
        assertEquals(50.0, IncomeManager.getIncomeList().get(0).amount());
        assertEquals(50.0, summary.getTotalIncome());
    }

    @Test
    void testDeleteInvalidIndexThrowsException() {
        Exception exception = assertThrows(BudgetTrackerException.class, () ->
                new DeleteIncomeCommand(5, summary).execute()
        );
        assertEquals("Invalid index. Please provide a valid income index.", exception.getMessage());
    }
}


