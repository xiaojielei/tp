package savings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exceptions.BudgetTrackerException;
import summary.Summary;

import static org.junit.jupiter.api.Assertions.*;

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
        saving.addSavings(100, "emergency fund");
        saving.addSavings(200, "vacation");
        saving.deleteSavings(1);
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void deleteSavings_invalidIndex_expectThrowsException() throws BudgetTrackerException {
        saving.addSavings(100.00, "vacation");
        assertThrows(BudgetTrackerException.class, () -> saving.deleteSavings(0));
    }

    @Test
    void deleteSavings_negativeIndex_expectThrowsException() throws BudgetTrackerException {
        saving.addSavings(100, "emergency fund");
        assertThrows(BudgetTrackerException.class, () -> saving.deleteSavings(-1));
    }

    @Test
    void deleteSavings_lastIndex_expectCorrectDeletion() throws BudgetTrackerException {
        saving.addSavings(100, "emergency fund");
        saving.addSavings(200, "vacation");
        saving.deleteSavings(2);
        assertEquals(1, saving.getSavingsRecords().size());
        assertEquals(100, saving.getSavingsRecords().get(0).getAmount());
    }

    @Test
    void transferSavings_validIndicesAndAmount_expectAmountTransferred() throws BudgetTrackerException {
        saving.addSavings(500, "emergency fund");
        saving.addSavings(300, "vacation");
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
        saving.addSavings(100, "emergency fund");
        saving.addSavings(200, "vacation");
        assertThrows(IllegalArgumentException.class, () -> saving.transferSavings(1, 2, 500));
    }

    @Test
    void transferSavings_sameIndex_expectException() throws BudgetTrackerException {
        saving.addSavings(300, "vacation");
        assertThrows(IllegalArgumentException.class, () -> saving.transferSavings(1, 1, 100));
    }

    @Test
    void addSavings_validAmount_expectSavingRecordAdded() throws BudgetTrackerException {
        saving.addSavings(150, "vacation");
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void addSavings_zeroAmount_expectAssertionError() {
        assertThrows(AssertionError.class, () -> saving.addSavings(0, "vacation"));
    }

    @Test
    void addSavings_negativeAmount_expectAssertionError() {
        assertThrows(AssertionError.class, () -> saving.addSavings(-50, "vacation"));
    }

    @Test
    void addSavings_multipleEntries_expectAllAdded() throws BudgetTrackerException {
        saving.addSavings(100, "vacation");
        saving.addSavings(200, "vacation");
        assertEquals(2, saving.getSavingsRecords().size());
    }

    @Test
    void setSavingsGoal_existAmount_expectGoalSet() throws BudgetTrackerException {
        saving.addSavings(300, "vacation");
        saving.setSavingsGoal(300, "Buy a laptop");
        assertEquals("Buy a laptop", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void setSavingsGoal_nonExistAmount_expectNoChange() throws BudgetTrackerException {
        saving.addSavings(100.00, "");
        saving.setSavingsGoal(500, "goal");
        assertEquals("(savings goal not provided)", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void setSavingsGoal_setGoalToEmptyGoal_expectGoalSet() throws BudgetTrackerException {
        saving.addSavings(400, "");
        saving.setSavingsGoal(400, "New Goal");
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void setSavingsGoal_multipleSavings_expectCorrectGoalAssigned() throws BudgetTrackerException {
        saving.addSavings(500, "emergency funds");
        saving.addSavings(600, "");
        saving.setSavingsGoal(600, "vacation");
        assertEquals("vacation", saving.getSavingsRecords().get(1).getGoal());
    }

    @Test
    void deleteSavingsGoal_validIndex_expectGoalDeleted() throws BudgetTrackerException {
        saving.addSavings(250, "vacation");
        saving.deleteSavingsGoal(1);
        assertEquals("(savings goal not provided)", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void deleteSavingsGoal_invalidIndex_expectThrowsException() {
        assertThrows(BudgetTrackerException.class, () -> saving.deleteSavingsGoal(0));
    }

    @Test
    void deleteSavingsGoal_existingGoalDeleted_expectGoalRemoved() throws BudgetTrackerException {
        saving.addSavings(300, "vacation");
        saving.setSavingsGoal(300, "New Car");
        saving.deleteSavingsGoal(1);
        assertEquals("(savings goal not provided)", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void deleteSavingsGoal_multipleSavings_expectCorrectGoalDeleted() throws BudgetTrackerException {
        saving.addSavings(100, "vacation");
        saving.addSavings(200, "vacation");
        saving.setSavingsGoal(200, "House");
        saving.deleteSavingsGoal(2);
        int zeroBaseIndex = 1;
        assertEquals("(savings goal not provided)", saving.getSavingsRecords().get(zeroBaseIndex).getGoal());
    }

    @Test
    void processSavingCommand_addSavingsValidAmount_expectSavingRecordAdded() throws BudgetTrackerException {
        commandHandler.processSavingCommand("add savings 300");
        assertEquals(1, saving.getSavingsRecords().size());
    }

    @Test
    void processSavingCommand_deleteSavingsValidIndex_expectSavingRecordDeleted() throws BudgetTrackerException {
        saving.addSavings(500, "vacation");
        commandHandler.processSavingCommand("delete savings 1");
        assertEquals(0, saving.getSavingsRecords().size());
    }

    @Test
    void processSavingCommand_invalidCommand_expectUnknownCommandMessage() throws BudgetTrackerException {
        String exampleInvalidCommand = "ad sav";
        commandHandler.processSavingCommand(exampleInvalidCommand);
    }

    @Test
    void processSavingCommand_transferSavingsValidIndices_expectAmountTransferred() throws BudgetTrackerException {
        saving.addSavings(500, "vacation");
        saving.addSavings(200, "vacation");
        commandHandler.processSavingCommand("transfer savings 1 2 100");
        assertEquals(400, saving.getSavingsRecords().get(0).getAmount());
        assertEquals(300, saving.getSavingsRecords().get(1).getAmount());
    }

    @Test
    void processSavingCommand_setSavingsGoalValid_expectGoalChanged() throws BudgetTrackerException {
        saving.addSavings(300, "vacation");
        commandHandler.processSavingCommand("savings goal set 300 / Buy a laptop");
        assertEquals("Buy a laptop", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_setSavingsGoalInValidAmount_expectGoalRemainsTheSame() throws BudgetTrackerException {
        saving.addSavings(300, "vacation");
        commandHandler.processSavingCommand("savings goal set 400 / Buy a laptop");
        assertEquals("vacation", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_updateSavingsGoalValidGoalInput_expectGoalUpdated() throws BudgetTrackerException {
        saving.addSavings(400, "vacation");
        commandHandler.processSavingCommand("savings goal update 1 400 / New Goal");
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_updateSavingsGoalValidAmountAndGoalInputs_expectGoalUpdated() throws BudgetTrackerException {
        saving.addSavings(400, "vacation");
        commandHandler.processSavingCommand("savings goal update 1 500 / New Goal");
        assertEquals(500, saving.getSavingsRecords().get(0).getAmount());
        assertEquals("New Goal", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_updateSavingsGoalInValidIndex_expectGoalDoesNotUpdated() throws BudgetTrackerException {
        saving.addSavings(400, "vacation");
        commandHandler.processSavingCommand("savings goal update 2 400 / New Goal");
        assertEquals("vacation", saving.getSavingsRecords().get(0).getGoal());
    }

    @Test
    void processSavingCommand_updateSavingsGoalInValidAmountInput_expectGoalUpdated() throws BudgetTrackerException {
        saving.addSavings(400, "vacation");
        assertThrows(BudgetTrackerException.class, () -> saving.updateSavingsGoal(1, -10, "New goal"));
    }

    @Test
    void processSavingCommand_updateSavingsGoalValidAmount_expectAmountChanged() throws BudgetTrackerException {
        saving.addSavings(400, "vacation");
        commandHandler.processSavingCommand("savings goal update 1 100 / vacation");
        assertEquals(100, saving.getSavingsRecords().get(0).getAmount());
    }
}
