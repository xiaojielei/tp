package income;

import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.Test;
import summary.Summary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IncomeParserTest {

    private final Summary summary = new Summary();

    @Test
    void testParseAddIncomeCommand_invalidFormat() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                IncomeParser.parseAddIncomeCommand("add income 100.0 Salary", summary)
        );
        assertEquals("Invalid format for 'add income' command. Please use 'add income <AMOUNT> / <SOURCE>'",
                exception.getMessage());
    }

    @Test
    void testParseAddIncomeCommand_invalidAmount() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                IncomeParser.parseAddIncomeCommand("add income abc / Salary", summary)
        );
        assertEquals("Invalid amount format. Please provide a valid number.", exception.getMessage());
    }

    @Test
    void testParseDeleteIncomeCommand_invalidFormat() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                IncomeParser.parseDeleteIncomeCommand("delete 1", summary)
        );
        assertEquals("Invalid format for 'delete income' command. Please use 'delete income <INDEX>'",
                exception.getMessage());
    }

    @Test
    void testParseDeleteIncomeCommand_invalidIndex() {
        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                IncomeParser.parseDeleteIncomeCommand("delete income abc", summary)
        );
        assertEquals("Invalid index format. Please provide a valid number.", exception.getMessage());
    }

    @Test
    void testParseDeleteIncomeCommand_indexOutOfRange() {
        IncomeManager.clearIncomeList();
        IncomeManager.addIncome(new Income(100.0, "Job"));
        IncomeManager.addIncome(new Income(50.0, "Freelance"));

        BudgetTrackerException exception = assertThrows(BudgetTrackerException.class, () ->
                IncomeParser.parseDeleteIncomeCommand("delete income 10", summary)
        );

        String expectedMessage = "Invalid index. Please provide a valid income index between 1 and 2.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}


