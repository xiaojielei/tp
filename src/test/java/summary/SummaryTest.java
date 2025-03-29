package summary;

import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SummaryTest {

    @Test
    void getTotalIncome_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalIncome(), 0.001);
    }

    @Test
    void getTotalExpense_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void getTotalSavings_emptySummary_expectZero() {
        Summary summary = new Summary();
        assertEquals(0, summary.getTotalSavings(), 0.001);
    }

    @Test
    void getAvailableFunds_emptySummary_expectZero() throws BudgetTrackerException {
        Summary summary = new Summary();
        assertEquals(0, summary.getAvailableFunds(), 0.001);
    }

    @Test
    void getAvailableFunds_withIncome_expectCorrectBalance() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        assertEquals(100.0, summary.getAvailableFunds(), 0.001);
    }

    @Test
    void getAvailableFunds_withIncomeAndExpenses_expectCorrectBalance() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.addExpense(50.0);
        assertEquals(50.0, summary.getAvailableFunds(), 0.001);
    }

    @Test
    void getAvailableFunds_multipleIncomeAndExpenses_expectCorrectBalance() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(200);
        summary.addExpense(50);
        summary.addIncome(100);
        summary.removeIncome(50);
        summary.removeExpense(25);
        assertEquals(225, summary.getAvailableFunds(), 0.001);
    }

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
    void addIncome_zeroAmount_expectException() throws BudgetTrackerException{
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addIncome(0));
    }
    
    @Test
    void addIncome_multipleAmounts_expectCorrectTotalIncome() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(100.50);
        summary.addIncome(200.75);
        summary.addIncome(50);
        assertEquals(351.25, summary.getTotalIncome(),0.001);
    }

    @Test
    void removeIncome_exceedingCurrentIncome_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.addExpense(50.0);

        assertThrows(BudgetTrackerException.class, () -> summary.removeIncome(120.0));

        assertEquals(100.0, summary.getTotalIncome(), 0.001);
        assertEquals(50.0, summary.getTotalExpense(), 0.001);
    }
    
    @Test
    void removeIncome_wouldResultInNegativeAvailableFunds_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        
        summary.addIncome(100.0);
        
        summary.addExpense(80.0);
        
        assertEquals(100.0, summary.getTotalIncome(), 0.001);
        assertEquals(80.0, summary.getTotalExpense(), 0.001);
        assertEquals(20.0, summary.getAvailableFunds(), 0.001);
        
        assertThrows(BudgetTrackerException.class, () -> summary.removeIncome(50.0));
        
        assertEquals(100.0, summary.getTotalIncome(), 0.001);
        assertEquals(80.0, summary.getTotalExpense(), 0.001);
        assertEquals(20.0, summary.getAvailableFunds(), 0.001);
        
        summary.removeIncome(10.0);
        assertEquals(90.0, summary.getTotalIncome(), 0.001);
        assertEquals(80.0, summary.getTotalExpense(), 0.001);
        assertEquals(10.0, summary.getAvailableFunds(), 0.001);
    }

    @Test
    void addExpense_positiveAmount_expectIncreaseInTotalExpense() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0); 
        summary.addExpense(50.0);
        assertEquals(50.0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void addExpense_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addExpense(-50.0));
    }

    @Test
    void addExpense_zeroAmount_expectNoChange() throws BudgetTrackerException { 
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.addExpense(0));
    }

    @Test
    void addExpense_multipleAmounts_expectCorrectTotalExpense() throws BudgetTrackerException{
        Summary summary = new Summary();
        summary.addIncome(500.0); 
        summary.addExpense(100.50);
        summary.addExpense(200.75);
        summary.addExpense(50);
        assertEquals(351.25, summary.getTotalExpense(),0.001);
    }
    
    @Test
    void addExpense_exceedingAvailableFunds_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);

        assertThrows(BudgetTrackerException.class, () -> summary.addExpense(150.0));

        assertEquals(100.0, summary.getTotalIncome(), 0.001);
        assertEquals(0.0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void removeExpense_negativeAmount_expectException() {
        Summary summary = new Summary();
        assertThrows(BudgetTrackerException.class, () -> summary.removeExpense(-50.0));
    }
    
    @Test
    void removeExpense_exceedingCurrentExpense_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.addExpense(50.0);

        assertThrows(BudgetTrackerException.class, () -> summary.removeExpense(75.0));

        assertEquals(50.0, summary.getTotalExpense(), 0.001);
    }

    @Test
    void addSavings_largerThanIncome_shouldSucceed() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(50.0);
        summary.addExpense(20.0);

        summary.addSavings(100.0);

        assertEquals(100.0, summary.getTotalSavings(), 0.001);
        assertEquals(50.0, summary.getTotalIncome(), 0.001);
        assertEquals(20.0, summary.getTotalExpense(), 0.001);
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
    
    @Test
    void removeSavings_zeroAmount_expectException() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(100.0);
        summary.addSavings(50.0);

        assertThrows(BudgetTrackerException.class, () -> summary.removeSavings(0.0));

        assertEquals(50.0, summary.getTotalSavings(), 0.001);
    }

    @Test
    void observerNotification_whenFinancialChanges_observersAreNotified() throws BudgetTrackerException {
        Summary summary = new Summary();
        TestObserver observer = new TestObserver();

        summary.registerObserver(observer);

        summary.addIncome(100.0);
        assertTrue(observer.wasNotified());
        assertEquals(100.0, observer.getLastIncome(), 0.001);
        
        observer.reset();
        summary.removeObserver(observer);
        summary.addExpense(50.0);
        assertFalse(observer.wasNotified());
    }
    
    @Test
    void notifyObservers_multipleRegisteredObservers_allObserversNotified() throws BudgetTrackerException {
        Summary summary = new Summary();
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();
        
        summary.registerObserver(observer1);
        summary.registerObserver(observer2);
        
        summary.addIncome(75.0);
        
        assertTrue(observer1.wasNotified());
        assertTrue(observer2.wasNotified());
        assertEquals(75.0, observer1.getLastIncome(), 0.001);
        assertEquals(75.0, observer2.getLastIncome(), 0.001);
        
        observer1.reset();
        observer2.reset();
        
        summary.addExpense(25.0);
        
        assertTrue(observer1.wasNotified());
        assertTrue(observer2.wasNotified());
        assertEquals(75.0, observer1.getLastIncome(), 0.001);
        assertEquals(25.0, observer1.getLastExpense(), 0.001);
    }
    
    @Test
    void summaryOperations_complexSequenceOfOperations_correctFinalState() throws BudgetTrackerException {
        Summary summary = new Summary();
        
        summary.addIncome(1000.0);
        summary.addExpense(300.0);
        summary.addSavings(400.0);
        
        summary.removeIncome(200.0);
        summary.removeExpense(100.0);
        summary.removeSavings(150.0);
        
        summary.addIncome(150.0);
        summary.addExpense(125.0);
        summary.addSavings(75.0);
        
        assertEquals(950.0, summary.getTotalIncome(), 0.001);
        assertEquals(325.0, summary.getTotalExpense(), 0.001);
        assertEquals(325.0, summary.getTotalSavings(), 0.001);
        assertEquals(625.0, summary.getAvailableFunds(), 0.001);
    }

    private static class TestObserver implements alerts.FinancialObserver {
        private boolean notified = false;
        private double lastAvailableFunds = 0;
        private double lastIncome = 0;
        private double lastExpense = 0;
        private double lastSavings = 0;

        @Override
        public void update(double availableFunds, double income, double expense, double savings) {
            notified = true;
            this.lastAvailableFunds = availableFunds;
            this.lastIncome = income;
            this.lastExpense = expense;
            this.lastSavings = savings;
        }

        public boolean wasNotified() {
            return notified;
        }

        public double getLastIncome() {
            return lastIncome;
        }
        
        public double getLastExpense() {
            return lastExpense;
        }

        public void reset() {
            notified = false;
        }
    }
}
