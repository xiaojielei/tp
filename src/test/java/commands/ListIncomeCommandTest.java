package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import summary.Summary;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListIncomeCommandTest {
    private Summary summary;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        summary = new Summary();
        IncomeManager.clearIncomeList();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testListIncomeWithEntries() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(100.0, "Job"));
        IncomeManager.addIncome(new Income(50.0, "Freelance"));
        summary.addIncome(100.0); // Ensure summary gets updated
        summary.addIncome(50.0);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $100.0 from Job"));
        assertTrue(output.contains("2. $50.0 from Freelance"));
        assertTrue(output.contains("Total Income: $150.0")); // Summary now tracks total income
    }

    @Test
    void testListIncomeWithNoEntries() {
        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        assertEquals("No income entries available.", outputStream.toString().trim());
    }

    @Test
    void testListIncomeWithOneEntry() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(88.5, "Allowance"));
        summary.addIncome(88.5);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $88.5 from Allowance"));
        assertTrue(output.contains("Total Income: $88.5"));
    }

    @Test
    void testListIncomeFormattingForLargeAmounts() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(123456.78, "Mega Bonus"));
        summary.addIncome(123456.78);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $123456.78 from Mega Bonus"));
        assertTrue(output.contains("Total Income: $123456.78"));
    }

    @Test
    void testListIncomeWithSimilarSources() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(100.0, "Scholarship"));
        IncomeManager.addIncome(new Income(200.0, "Scholarship"));
        summary.addIncome(100.0);
        summary.addIncome(200.0);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $100.0 from Scholarship"));
        assertTrue(output.contains("2. $200.0 from Scholarship"));
        assertTrue(output.contains("Total Income: $300.0"));
    }

    @Test
    void testListIncomeAfterOtherOutput() throws BudgetTrackerException {
        System.out.println("Some other output");
        outputStream.reset(); // Clear previous output

        IncomeManager.addIncome(new Income(60.0, "Prize"));
        summary.addIncome(60.0);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $60.0 from Prize"));
        assertTrue(output.contains("Total Income: $60.0"));
    }

    @Test
    void testListIncomeWithSpecialSourceNames() throws BudgetTrackerException {
        IncomeManager.addIncome(new Income(25.0, "   Bonus $$$   "));
        summary.addIncome(25.0);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $25.0 from    Bonus $$$   "));
        assertTrue(output.contains("Total Income: $25.0"));
    }

    @Test
    void testListIncomeAfterDeletion() throws BudgetTrackerException {
        Income income1 = new Income(40.0, "Old Job");
        Income income2 = new Income(100.0, "New Job");

        IncomeManager.addIncome(income1);
        IncomeManager.addIncome(income2);
        summary.addIncome(40.0);
        summary.addIncome(100.0);

        // Simulate deletion of first income
        IncomeManager.getIncomeList().remove(0);
        summary.removeIncome(40.0);

        ListIncomeCommand command = new ListIncomeCommand(summary);
        command.execute();

        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. $100.0 from New Job"));
        assertFalse(output.contains("Old Job"));
        assertTrue(output.contains("Total Income: $100.0"));
    }
}
