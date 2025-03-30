package alerts;

import commands.AlertCommand;
import commands.Command;
import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import expenses.Ui;
import expenses.ExpenseList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlertParserTest {
    
    private FundsAlert fundsAlert;
    private Ui ui;
    private ExpenseList expenseList;
    
    @BeforeEach
    void setUp() {
        ui = new Ui();
        fundsAlert = new FundsAlert(ui);
        expenseList = new ExpenseList();
    }
    
    @Test
    void parse_validAlertSetCommand_returnsAlertCommand() throws BudgetTrackerException {
        String input = "alert set 25.0";
        
        Command command = AlertParser.parse(input, fundsAlert);
        
        assertTrue(command instanceof AlertCommand);
        
        // Execute the command with required parameters
        command.execute(expenseList, ui);
        assertEquals(25.0, fundsAlert.getWarningThreshold(), 0.001);
    }
    
    @Test
    void parse_missingAmountParameter_throwsBudgetTrackerException() {
        String input = "alert set";
        
        Exception exception = assertThrows(BudgetTrackerException.class, () -> {
            AlertParser.parse(input, fundsAlert);
        });
        
        assertTrue(exception.getMessage().contains("Invalid alert command format"));
    }
    
    @Test
    void parse_invalidNumberFormat_throwsBudgetTrackerException() {
        String input = "alert set invalid-number";
        
        Exception exception = assertThrows(BudgetTrackerException.class, () -> {
            AlertParser.parse(input, fundsAlert);
        });
        
        assertTrue(exception.getMessage().contains("Please provide a valid number"));
    }
    
    @Test
    void parse_unrecognizedAlertCommand_throwsBudgetTrackerException() {
        String input = "alert unknown 10";
        
        Exception exception = assertThrows(BudgetTrackerException.class, () -> {
            AlertParser.parse(input, fundsAlert);
        });
        
        assertTrue(exception.getMessage().contains("Unrecognized alert command"));
    }
}
