package savings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import exceptions.BudgetTrackerException;
import summary.Summary;
import java.util.List;

class SavingTest {
    private Saving saving;
    private Summary summary;

    @BeforeEach
    void setUp() {
        summary = new Summary();
        saving = new Saving(summary);
    }

    @Test
    void deleteSavings_validIndex_expectSavingRecordDeleted() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);

        assertEquals(2, saving.getSavingsRecords().size());

        saving.deleteSavings(1); // 删除第一条记录
        assertEquals(1, saving.getSavingsRecords().size());
        assertEquals(200, saving.getSavingsRecords().get(0).getAmount());
    }

    @Test
    void addSavings_validAmount_expectSavingRecordAdded() throws BudgetTrackerException {
        saving.addSavings(150);
        List<SavingsRecord> records = saving.getSavingsRecords();
        assertEquals(1, records.size());
        assertEquals(150, records.get(0).getAmount());
    }

    @Test
    void viewSavings_savingsExist_expectCorrectNumberOfSavings() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);
        assertEquals(2, saving.getSavingsRecords().size());
    }

    @Test
    void setSavingsGoal_validAmount_expectGoalSet() throws BudgetTrackerException {
        saving.addSavings(300);
        saving.setSavingsGoal(300, "Buy a laptop");
        assertEquals("Buy a laptop", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void updateSavingsGoal_validIndexAndAmount_expectGoalUpdated() throws BudgetTrackerException {
        saving.addSavings(400);
        saving.updateSavingsGoal(0, 500, "New Goal");
        assertEquals(500, saving.getSavingsRecords().get(0).getAmount());
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void deleteSavingsGoal_validIndex_expectGoalDeleted() throws BudgetTrackerException {
        saving.addSavings(250);
        saving.setSavingsGoal(250, "Travel Fund");
        saving.deleteSavingsGoal(0);
        assertEquals(" ", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void transferSavings_validIndicesAndAmount_expectAmountTransferred() throws BudgetTrackerException {
        saving.addSavings(500);
        saving.addSavings(300);

        assertEquals(500, saving.getSavingsRecords().get(0).getAmount());
        assertEquals(300, saving.getSavingsRecords().get(1).getAmount());

        saving.transferSavings(1, 2, 200);

        assertEquals(300, saving.getSavingsRecords().get(0).getAmount());
        assertEquals(500, saving.getSavingsRecords().get(1).getAmount());
    }
}

