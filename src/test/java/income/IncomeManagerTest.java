package income;

import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IncomeManagerTest {
    @Test
    void testAddIncomeSuccessfully() {
        Income income = new Income(300.0, "Part-time Job");
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(income);
        assertEquals(1, IncomeManager.getIncomeList().size());
        assertEquals("Part-time Job", IncomeManager.getIncomeList().get(0).getSource());
    }

    @Test
    void testDeleteIncomeSuccessfully() throws BudgetTrackerException {
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(new Income(100.0, "Allowance"));
        IncomeManager.deleteIncome(0);
        assertTrue(IncomeManager.getIncomeList().isEmpty());
    }

    @Test
    void testDeleteIncomeWithInvalidIndexThrowsException() {
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(new Income(100.0, "Salary"));
        BudgetTrackerException ex = assertThrows(BudgetTrackerException.class, () ->
                IncomeManager.deleteIncome(1)
        );
        assertTrue(ex.getMessage().contains("Invalid index: 2. Index must be between 1 and 1"));
    }


    @Test
    void testDeleteFromEmptyListThrowsException() {
        IncomeManager.clearIncomeList();
        BudgetTrackerException ex = assertThrows(BudgetTrackerException.class, () ->
                IncomeManager.deleteIncome(0)
        );
        assertEquals("Cannot delete from an empty income list.", ex.getMessage());
    }

    @Test
    void testClearIncomeList() {
        IncomeManager.addIncome(new Income(50.0, "Gift"));
        IncomeManager.clearIncomeList();
        assertEquals(0, IncomeManager.getIncomeList().size());
    }

    @Test
    void testGetIncomeListReturnsCorrectList() {
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(new Income(100.0, "Tutoring"));
        List<Income> list = IncomeManager.getIncomeList();
        assertEquals(1, list.size());
    }
}
