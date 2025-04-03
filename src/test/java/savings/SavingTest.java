package savings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import exceptions.BudgetTrackerException;
import summary.Summary;

class SavingTest {
    private Saving saving;
    private SavingCommandHandler commandHandler;

    @BeforeEach
    void setUp() {
        Summary summary = new Summary();
        saving = new Saving(summary);
        commandHandler = new SavingCommandHandler(saving);
    }

    @Test
    void deleteSavings_validIndex_expectSavingRecordDeleted() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);
        saving.deleteSavings(1);
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void deleteSavings_invalidIndex_expectNoDeletion() {
        saving.deleteSavings(1);
        assertEquals(0, saving.getSavingsRecords().size());
    }

    @Test
    void deleteSavings_negativeIndex_expectNoDeletion() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.deleteSavings(-1);
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void deleteSavings_lastIndex_expectCorrectDeletion() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);
        saving.deleteSavings(2);
        assertEquals(1, saving.getSavingsRecords().size());
        assertEquals(100, saving.getSavingsRecords().get(0).getAmount());
    }

    @Test
    void transferSavings_validIndicesAndAmount_expectAmountTransferred() throws BudgetTrackerException {
        saving.addSavings(500);
        saving.addSavings(300);
        saving.transferSavings(1, 2, 200);
        assertEquals(300, saving.getSavingsRecords().get(0).getAmount());
        assertEquals(500, saving.getSavingsRecords().get(1).getAmount());
    }

    @Test
    void transferSavings_invalidIndex_expectException() {
        assertThrows(IllegalArgumentException.class, () -> saving.transferSavings(1, 2, 100));
    }

    @Test
    void transferSavings_insufficientFunds_expectException() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);
        assertThrows(IllegalArgumentException.class, () -> saving.transferSavings(1, 2, 500));
    }

    @Test
    void transferSavings_sameIndex_expectException() throws BudgetTrackerException {
        saving.addSavings(300);
        assertThrows(IllegalArgumentException.class, () -> saving.transferSavings(1, 1, 100));
    }

    @Test
    void addSavings_validAmount_expectSavingRecordAdded() throws BudgetTrackerException {
        saving.addSavings(150);
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void addSavings_zeroAmount_expectAssertionError() {
        assertThrows(AssertionError.class, () -> saving.addSavings(0));
    }

    @Test
    void addSavings_negativeAmount_expectAssertionError() {
        assertThrows(AssertionError.class, () -> saving.addSavings(-50));
    }

    @Test
    void addSavings_multipleEntries_expectAllAdded() throws BudgetTrackerException {
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
    void setSavingsGoal_invalidAmount_expectNoChange() {
        saving.setSavingsGoal(500, "Invalid goal");
        assertEquals(0, saving.getSavingsRecords().size());
    }

    @Test
    void setSavingsGoal_existingGoalUpdated_expectCorrectUpdate() throws BudgetTrackerException {
        saving.addSavings(400);
        saving.setSavingsGoal(400, "Old Goal");
        saving.setSavingsGoal(400, "New Goal");
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void setSavingsGoal_multipleSavings_expectCorrectGoalAssigned() throws BudgetTrackerException {
        saving.addSavings(500);
        saving.addSavings(600);
        saving.setSavingsGoal(600, "Vacation");
        assertEquals("Vacation", saving.getSavingsRecords().get(1).getGoal());
    }

    @Test
    void deleteSavingsGoal_validIndex_expectGoalDeleted() throws BudgetTrackerException {
        saving.addSavings(250);
        saving.setSavingsGoal(250, "Travel Fund");
        saving.deleteSavingsGoal(0);
        assertEquals(" ", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void deleteSavingsGoal_invalidIndex_expectNoChange() {
        saving.deleteSavingsGoal(0);
        assertEquals(0, saving.getSavingsRecords().size());
    }

    @Test
    void deleteSavingsGoal_existingGoalDeleted_expectGoalRemoved() throws BudgetTrackerException {
        saving.addSavings(300);
        saving.setSavingsGoal(300, "New Car");
        saving.deleteSavingsGoal(0);
        assertEquals(" ", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void deleteSavingsGoal_multipleSavings_expectCorrectGoalDeleted() throws BudgetTrackerException {
        saving.addSavings(100);
        saving.addSavings(200);
        saving.setSavingsGoal(200, "House");
        saving.deleteSavingsGoal(1);
        assertEquals(" ", saving.getSavingsRecords().get(1).getGoal());
    }

    @Test
    void processSavingCommand_addSavingsValidAmount_expectSavingRecordAdded() {
        commandHandler.processSavingCommand("add savings 300");
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void processSavingCommand_deleteSavingsValidIndex_expectSavingRecordDeleted() throws BudgetTrackerException {
        saving.addSavings(500);
        commandHandler.processSavingCommand("delete savings 1");
        assertEquals(0, saving.getSavingsRecords().size());
    }

    @Test
    void processSavingCommand_invalidCommand_expectUnknownCommandMessage() {
        commandHandler.processSavingCommand("invalid command");
    }

    @Test
    void processSavingCommand_transferSavingsValidIndices_expectAmountTransferred() throws BudgetTrackerException {
        saving.addSavings(500);
        saving.addSavings(200);
        commandHandler.processSavingCommand("transfer savings 1 2 100");
        assertEquals(400, saving.getSavingsRecords().get(0).getAmount());
        assertEquals(300, saving.getSavingsRecords().get(1).getAmount());
    }

    @Test
    void processSavingCommand_viewSavings_expectOutput() throws BudgetTrackerException {
        saving.addSavings(100);
        commandHandler.processSavingCommand("view savings");
    }

    @Test
    void processSavingCommand_exitSavings_expectExitMessage() {
        commandHandler.processSavingCommand("exit savings");
    }

    @Test
    void processSavingCommand_setSavingsGoalValid_expectGoalSet() throws BudgetTrackerException {
        saving.addSavings(300);
        commandHandler.processSavingCommand("savings goal set 300 / Buy a laptop");
        assertEquals("Buy a laptop", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_updateSavingsGoalValid_expectGoalUpdated() throws BudgetTrackerException {
        saving.addSavings(400);
        commandHandler.processSavingCommand("savings goal set 400 / Old Goal");
        commandHandler.processSavingCommand("savings goal update 1 400 / New Goal");
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }
}


