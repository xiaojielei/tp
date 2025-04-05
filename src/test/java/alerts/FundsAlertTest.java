package alerts;

import expenses.Ui;
import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FundsAlertTest {
    private FundsAlert fundsAlert;
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        ui = new Ui();
        fundsAlert = new FundsAlert(ui);
    }
    
    @AfterEach
    public void tearDown() {
        // Reset stream for next test
        System.setOut(originalOut);
        outputStream.reset();
    }
    
    @Test
    public void getWarningThreshold_newFundsAlert_expectDefaultValue() {
        assertEquals(5.0, fundsAlert.getWarningThreshold(), 
                "Default warning threshold should be $5");
    }
    
    @Test
    public void setWarningThreshold_validPositiveValue_expectThresholdUpdated() throws BudgetTrackerException {
        double newThreshold = 50.0;
        boolean result = fundsAlert.setWarningThreshold(newThreshold);
        
        assertTrue(result, "Setting threshold should return true on success");
        assertEquals(newThreshold, fundsAlert.getWarningThreshold(), 
                "Warning threshold should be updated to the new value");
    }
    
    @Test
    public void setWarningThreshold_negativeValue_expectBudgetTrackerException() {
        double negativeThreshold = -10.0;
        
        Exception exception = assertThrows(BudgetTrackerException.class, () -> {
            fundsAlert.setWarningThreshold(negativeThreshold);
        });
        
        String expectedMessage = "Warning threshold cannot be negative";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Exception message should indicate that threshold cannot be negative");
        
        assertEquals(5.0, fundsAlert.getWarningThreshold(),
                "Warning threshold should remain at default after rejected update");
    }

    @Test
    public void setWarningThreshold_zeroValue_expectThresholdUpdated() throws BudgetTrackerException {
        double zeroThreshold = 0.0;
        boolean result = fundsAlert.setWarningThreshold(zeroThreshold);

        assertTrue(result, "Setting threshold to zero should return true");
        assertEquals(zeroThreshold, fundsAlert.getWarningThreshold(),
                "Warning threshold should be updated to zero");
    }

    @Test
    public void update_fundsBelowThreshold_expectAlertTriggered() {
        fundsAlert.update(3.0, 100.0, 97.0, 0.0);
        
        String output = outputStream.toString();
        assertTrue(output.contains("WARNING") && output.contains("below warning threshold"),
                "Alert should be triggered when funds are below threshold");
    }
    
    @Test
    public void update_fundsAboveThreshold_expectNoAlert() {
        fundsAlert.update(10.0, 100.0, 90.0, 0.0);
        
        String output = outputStream.toString();
        assertFalse(output.contains("WARNING") && output.contains("below warning threshold"),
                "No alert should be triggered when funds are above threshold");
    }
    
    @Test
    public void displayInitialNotification_defaultSettings_expectCorrectMessage() {
        fundsAlert.displayInitialNotification();
        
        String output = outputStream.toString();
        // 3 assertions to ensure that the initial notification is complete
        assertTrue(output.contains("Funds Alert feature is active"),
                "Initial notification should indicate the feature is active");
        assertTrue(output.contains("below $5.00"),
                "Initial notification should show the default threshold");
        assertTrue(output.contains("alert set"),
                "Initial notification should mention how to change the threshold");
    }
    
    @Test
    public void update_fundsAboveNewThreshold_noAlertTriggered() throws BudgetTrackerException {
        fundsAlert.setWarningThreshold(20.0);
        
        fundsAlert.update(25.0, 100.0, 75.0, 0.0);
        
        String output = outputStream.toString();
        assertFalse(output.contains("WARNING"), 
                "No alert should be triggered when funds are above the new higher threshold");
    }

    @Test
    public void update_fundsBelowNewThreshold_alertTriggered() throws BudgetTrackerException {
        fundsAlert.setWarningThreshold(20.0);
        
        fundsAlert.update(15.0, 100.0, 85.0, 0.0);
        
        String output = outputStream.toString();
        assertTrue(output.contains("WARNING") && output.contains("below warning threshold"),
                "Alert should be triggered when funds are below the new higher threshold");
        assertTrue(output.contains("$15.00") && output.contains("$20.00"),
                "Alert should show the correct available funds and threshold values");
    }
    
    @Test
    public void update_fundsAboveThenBelowNewThreshold_noAlertTriggered() throws BudgetTrackerException {
        fundsAlert.setWarningThreshold(20.0);
        fundsAlert.update(15.0, 100.0, 85.0, 0.0);

        outputStream.reset();
        fundsAlert.setWarningThreshold(10.0);
        fundsAlert.update(15.0, 100.0, 85.0, 0.0);
        
        String output = outputStream.toString();
        assertFalse(output.contains("WARNING"),
                "No alert should be triggered when threshold is lowered below the available funds");
    }

    @Test
    public void update_fundsEqualToDefaultThreshold_noAlertTriggered() {
        double fundsEqualToThreshold = 5.0;
        fundsAlert.update(fundsEqualToThreshold, 100.0, 95.0, 0.0); // Income/Expense adjusted for balance

        String output = outputStream.toString();
        assertFalse(output.contains("WARNING"),
                "No alert should trigger when funds exactly match the default threshold");
    }

    @Test
    public void update_fundsEqualToNewThreshold_noAlertTriggered() throws BudgetTrackerException {
        double newThreshold = 10.0;
        fundsAlert.setWarningThreshold(newThreshold);

        double fundsEqualToThreshold = 10.0;
        fundsAlert.update(fundsEqualToThreshold, 100.0, 90.0, 0.0); // Income/Expense adjusted

        String output = outputStream.toString();
        assertFalse(output.contains("WARNING"),
                "No alert should trigger when funds exactly match the newly set threshold");
    }
}
