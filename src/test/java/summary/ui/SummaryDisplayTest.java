package summary.ui;

import exceptions.BudgetTrackerException;
import org.junit.jupiter.api.Test;
import summary.Summary;

import static org.junit.jupiter.api.Assertions.*;

class SummaryDisplayTest {

    @Test
    void displaySummary_validSummary_correctOutput() throws BudgetTrackerException {
        Summary summary = new Summary();
        summary.addIncome(1000.50);
        summary.addExpense(250.25);
        summary.addSavings(100);

        SummaryDisplay display = new SummaryDisplay(summary);

        String expectedOutput =
                "===== BUDGET SUMMARY =====\n" +
                        "Total Income:        $1000.50\n" +
                        "Total Expenses:      $250.25\n" +
                        "Available Balance:   $750.25\n" +
                        "Total Savings:       $100.00\n" +
                        "===========================\n"; // Ensure consistent line endings

        // Normalize line endings AND trim whitespace from each line:
        String expected = normalizeLineEndingsAndTrim(expectedOutput);
        String actual = normalizeLineEndingsAndTrim(display.displaySummary());

        assertEquals(expected, actual);
    }

    @Test
    void displaySummary_emptySummary_correctOutput() {
        Summary summary = new Summary();
        SummaryDisplay display = new SummaryDisplay(summary);

        String expectedOutput =
                "===== BUDGET SUMMARY =====\n" +
                        "Total Income:        $0.00\n" +
                        "Total Expenses:      $0.00\n" +
                        "Available Balance:   $0.00\n" +
                        "Total Savings:       $0.00\n" +
                        "===========================\n";


        String expected = normalizeLineEndingsAndTrim(expectedOutput);
        String actual = normalizeLineEndingsAndTrim(display.displaySummary());
        assertEquals(expected, actual);
    }

    // Helper method to normalize line endings and trim whitespace from each line
    private String normalizeLineEndingsAndTrim(String input) {
        String[] lines = input.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(line.trim()).append("\n");
        }

        return sb.toString();
    }
}
