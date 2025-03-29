package alerts;

import expenses.Ui;
import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void update_fundsBelowThreshold_expectAlertTriggered() {
        outputStream.reset();
        
        fundsAlert.update(3.0, 100.0, 97.0, 0.0);
        
        String output = outputStream.toString();
        assertTrue(output.contains("WARNING") && output.contains("below warning threshold"),
                "Alert should be triggered when funds are below threshold");
    }
    
    @Test
    public void update_fundsAboveThreshold_expectNoAlert() {
        outputStream.reset();
        
        fundsAlert.update(10.0, 100.0, 90.0, 0.0);
        
        String output = outputStream.toString();
        assertFalse(output.contains("WARNING") && output.contains("below warning threshold"),
                "No alert should be triggered when funds are above threshold");
    }
    
    @Test
    public void displayInitialNotification_defaultSettings_expectCorrectMessage() {
        outputStream.reset();
        
        fundsAlert.displayInitialNotification();
        
        String output = outputStream.toString();
        assertTrue(output.contains("Funds Alert feature is active"),
                "Initial notification should indicate the feature is active");
        assertTrue(output.contains("below $5.00"),
                "Initial notification should show the default threshold");
        assertTrue(output.contains("alert set"),
                "Initial notification should mention how to change the threshold");
    }
    
    @Test
    public void update_thresholdChangedAndFundsCrossThreshold_correctAlertBehavior() throws BudgetTrackerException {
        // First set a higher threshold
        fundsAlert.setWarningThreshold(20.0);
        
        // Test with funds above new threshold - should not trigger alert
        outputStream.reset();
        fundsAlert.update(25.0, 100.0, 75.0, 0.0);
        String output1 = outputStream.toString();
        assertFalse(output1.contains("WARNING"), 
                "No alert should be triggered when funds are above the new threshold");
        
        // Test with funds below new threshold but above original threshold - should trigger alert
        outputStream.reset();
        fundsAlert.update(15.0, 100.0, 85.0, 0.0);
        String output2 = outputStream.toString();
        assertTrue(output2.contains("WARNING") && output2.contains("below warning threshold"),
                "Alert should be triggered when funds are below the new threshold");
        assertTrue(output2.contains("$15.00") && output2.contains("$20.00"),
                "Alert should show the correct available funds and threshold values");
        
        // Reset threshold to lower value
        fundsAlert.setWarningThreshold(10.0);
        
        // Same funds should now be above threshold - should not trigger alert
        outputStream.reset();
        fundsAlert.update(15.0, 100.0, 85.0, 0.0);
        String output3 = outputStream.toString();
        assertFalse(output3.contains("WARNING"),
                "No alert should be triggered when threshold is lowered below the available funds");
    }
}
