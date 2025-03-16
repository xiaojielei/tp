package summary;

import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SummaryTest {

    // Test getTotalBalance()
    @Test
    void getTotalBalance_emptySummary_expectZero() throws BudgetTrackerException {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalBalance(), 0.001);
    }

    @Test
    void getTotalBalance_withIncome_expectCorrectBalance() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        assertEquals(100.0, summary.getTotalBalance(), 0.001);
    }

    @Test
    void getTotalBalance_withIncomeAndExpenses_expectCorrectBalance() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.addExpense(50.0);
        assertEquals(50.0, summary.getTotalBalance(), 0.001);
    }

    @Test
    void getTotalBalance_multipleIncomeAndExpenses_expectCorrectBalance() throws BudgetTrackerException{
        Summary summary = new Summary();
        double balance = 0;
        summary.addIncome(200);
        summary.addExpense(50);
        summary.addIncome(100);
        summary.removeIncome(50);
        summary.removeExpense(25);
        assertEquals(225, summary.getTotalBalance(), 0.001); // 175

    }

    // Test addIncome()
    @Test
    void addIncome_positiveAmount_expectIncreaseInTotalIncome() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        assertEquals(100.0, summary.getTotalIncome(), 0.001);
    }

    @Test
    void addIncome_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addIncome(-100.0));
    }

    @Test
    void addIncome_zeroAmount_expectNoChange() throws BudgetTrackerException{ // Test edge case
        Summary summary = new Summary();
        summary.addIncome(0);
        assertEquals(0, summary.getTotalIncome(), 0.001);

    }
    @Test
    void addIncome_multipleAmounts_expectCorrectTotalIncome() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(100.50);
        summary.addIncome(200.75);
        summary.addIncome(50);
        assertEquals(351.25, summary.getTotalIncome(),0.001);

    }

    // Test removeIncome()
    @Test
    void removeIncome_validAmount_expectDecreaseInTotalIncome() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.removeIncome(50.0);
        assertEquals(50.0, summary.getTotalIncome(), 0.001);
    }

    @Test
    void removeIncome_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.removeIncome(-50.0));
    }

    @Test
    void removeIncome_amountGreaterThanTotalIncome_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        assertThrows(BudgetTrackerException.class, () -> summary.removeIncome(150.0));
    }
    @Test
    void removeIncome_zeroAmount_expectNoChange() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100);
        summary.removeIncome(0);
        assertEquals(100, summary.getTotalIncome(), 0.001);

    }
    @Test
    void removeIncome_multipleValidAmount_expectCorrectResult() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.removeIncome(50);
        summary.removeIncome(25);
        assertEquals(125, summary.getTotalIncome(), 0.001);

    }
    @Test
    void removeIncome_removeTotalIncome_expectZero() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.removeIncome(200);
        assertEquals(0, summary.getTotalIncome(), 0.001);

    }
    // Test addExpense()
    @Test
    void addExpense_positiveAmount_expectIncreaseInTotalExpense() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addExpense(50.0);
        assertEquals(50.0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void addExpense_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addExpense(-50.0));
    }

    @Test
    void addExpense_zeroAmount_expectNoChange() throws BudgetTrackerException { // Edge Case
        Summary summary = new Summary();
        summary.addExpense(0);
        assertEquals(0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void addExpense_multipleAmounts_expectCorrectTotalExpense() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addExpense(100.50);
        summary.addExpense(200.75);
        summary.addExpense(50);
        assertEquals(351.25, summary.getTotalExpense(),0.001);

    }

    // Test removeExpense()
    @Test
    void removeExpense_validAmount_expectDecreaseInTotalExpense() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addExpense(100.0);
        summary.removeExpense(50.0);
        assertEquals(50.0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void removeExpense_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.removeExpense(-50.0));
    }

    @Test
    void removeExpense_amountGreaterThanTotalExpense_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addExpense(100.0);
        assertThrows(BudgetTrackerException.class, () -> summary.removeExpense(150.0));
    }

    @Test
    void removeExpense_zeroAmount_expectNoChange() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addExpense(100.0);
        summary.removeExpense(0);
        assertEquals(100, summary.getTotalExpense(), 0.001);
    }
    @Test
    void removeExpense_multipleValidAmount_expectCorrectResult() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addExpense(200);
        summary.removeExpense(50);
        summary.removeExpense(25);
        assertEquals(125, summary.getTotalExpense(), 0.001);

    }
    @Test
    void removeExpense_removeTotalExpense_expectZero() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addExpense(200);
        summary.removeExpense(200);
        assertEquals(0, summary.getTotalExpense(), 0.001);

    }

    // Test addSavings()
    @Test
    void addSavings_positiveAmount_expectIncreaseInTotalSavings() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200); // Need income to have available balance
        summary.addSavings(50.0);
        assertEquals(50.0, summary.getTotalSavings(), 0.001);
    }

    @Test
    void addSavings_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addSavings(-50.0));
    }

    @Test
    void addSavings_amountGreaterThanBalance_expectException() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(100);
        summary.addExpense(50);
        assertThrows(BudgetTrackerException.class, () -> summary.addSavings(60.0)); //More than balance.
    }

    @Test
    void addSavings_zeroAmount_expectNoChange() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.addSavings(0);
        assertEquals(0, summary.getTotalSavings(), 0.001);
    }
    @Test
    void addSavings_multipleAmounts_expectCorrectTotalSavings() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(5000);
        summary.addSavings(100.50);
        summary.addSavings(200.75);
        summary.addSavings(50);
        assertEquals(351.25, summary.getTotalSavings(),0.001);

    }

    // Test removeSavings()
    @Test
    void removeSavings_validAmount_expectDecreaseInTotalSavings() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200); //Need income for balance
        summary.addSavings(100.0);
        summary.removeSavings(50.0);
        assertEquals(50.0, summary.getTotalSavings(), 0.001);
    }

    @Test
    void removeSavings_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.removeSavings(-50.0));
    }

    @Test
    void removeSavings_amountGreaterThanTotalSavings_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200); //Need income for balance
        summary.addSavings(100.0);
        assertThrows(BudgetTrackerException.class, () -> summary.removeSavings(150.0));
    }
    @Test
    void removeSavings_zeroAmount_expectNoChange() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.addSavings(100);
        summary.removeSavings(0);
        assertEquals(100, summary.getTotalSavings(), 0.001);
    }

    @Test
    void removeSavings_multipleValidAmount_expectCorrectResult() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.addSavings(100);
        summary.removeSavings(50);
        summary.removeSavings(25);
        assertEquals(25, summary.getTotalSavings(), 0.001);

    }
    @Test
    void removeSavings_removeTotalSavings_expectZero() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(5000);
        summary.addSavings(200);
        summary.removeSavings(200);
        assertEquals(0, summary.getTotalSavings(), 0.001);

    }

    // Test getTotalIncome()
    @Test
    void getTotalIncome_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalIncome(), 0.001);
    }

    // Test getTotalExpense()
    @Test
    void getTotalExpense_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalExpense(), 0.001);
    }

    // Test getTotalSavings()
    @Test
    void getTotalSavings_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalSavings(), 0.001);
    }

}
