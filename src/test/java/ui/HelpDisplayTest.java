package ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelpDisplayTest {
    
    private HelpDisplay helpDisplay;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() {
        helpDisplay = new HelpDisplay();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    void getHelpText_newHelpDisplayInstance_containsAllCategoryHeadings() {
        String helpText = helpDisplay.getHelpText();
        
        assertTrue(helpText.contains("Budget Tracker Help"), "Help text should contain the header");
        assertTrue(helpText.contains("Income Management"), "Help text should contain income heading");
        assertTrue(helpText.contains("Expense Management"), "Help text should contain expense heading");
        assertTrue(helpText.contains("Savings Management"), "Help text should contain savings heading");
        assertTrue(helpText.contains("Summary Management"), "Help text should contain summary heading");
        assertTrue(helpText.contains("Savings Goals"), "Help text should contain goals heading");
        assertTrue(helpText.contains("General Commands"), "Help text should contain general commands heading");
        assertTrue(helpText.contains("Funds Alerts"), "Help text should contain alerts heading");
    }
    
    @Test
    void getHelpText_newHelpDisplayInstance_containsEssentialCommands() {
        String helpText = helpDisplay.getHelpText();
        
        assertTrue(helpText.contains("add income"), "Help text should contain add income command");
        assertTrue(helpText.contains("add expense"), "Help text should contain add expense command");
        assertTrue(helpText.contains("add savings"), "Help text should contain add savings command");
        assertTrue(helpText.contains("view summary"), "Help text should contain view summary command");
        assertTrue(helpText.contains("alert set"), "Help text should contain alert set command");
        assertTrue(helpText.contains("help"), "Help text should contain help command");
    }
    
    @Test
    void display_helpDisplayInstance_printsToConsole() {
        helpDisplay.display();
        
        String consoleOutput = outContent.toString();
        assertFalse(consoleOutput.isEmpty(), "Display should output to console");
        assertEquals(helpDisplay.getHelpText() + System.lineSeparator(), consoleOutput);
    }
    
    @Test
    void constructor_newHelpDisplayInstance_properlyInitialized() {
        HelpDisplay newHelpDisplay = new HelpDisplay();
        String helpText = newHelpDisplay.getHelpText();
        
        assertNotNull(helpText, "Help text should not be null");
        assertTrue(helpText.length() > 0, "Help text should not be empty");
        assertTrue(helpText.startsWith("===== Budget Tracker Help ====="), 
                "Help text should start with the header");
    }
}
