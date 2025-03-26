package commands;

import income.Income;
import income.IncomeManager;
import summary.Summary;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command to list all income entries and display the total income.
 * It retrieves the income entries from the IncomeManager and outputs
 * the details of each income entry along with the total income from
 * the Summary.
 */
public class ListIncomeCommand {
    private static final Logger logger = Logger.getLogger(ListIncomeCommand.class.getName());
    private final Summary summary;

    static {
        configureLogger();  // Configure the logger when the class is loaded
    }

    /**
     * Configures the logger to ensure no timestamp or additional details in log output.
     */
    private static void configureLogger() {
        // Remove all default handlers to avoid unwanted timestamp in log output
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        // Add a new ConsoleHandler with a custom formatter that doesn't include a timestamp
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new java.util.logging.Formatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                return record.getMessage() + "\n";  // Only log the message, no timestamp or other details
            }
        });
        logger.addHandler(consoleHandler);

        // Set the logger level to OFF to suppress all unwanted logging (including INFO, WARNING, etc.)
        logger.setLevel(Level.INFO);  // This will suppress all log levels except SEVERE
    }

    /**
     * Creates a new ListIncomeCommand with the specified summary.
     *
     * @param summary The summary to retrieve the total income from.
     * @throws IllegalArgumentException If the summary is null.
     */
    public ListIncomeCommand(Summary summary) {
        if (summary == null) {
            logger.severe("Summary object is null.");
            throw new IllegalArgumentException("Summary cannot be null.");
        }
        this.summary = summary;
    }

    /**
     * Executes the command to list all income entries and display the total income.
     * If there are no income entries, a message is displayed indicating so.
     */
    public void execute() {
        try {
            if (IncomeManager.getIncomeList().isEmpty()) {
                logger.info("No income entries found.");
                System.out.println("No income entries available.");
                return;
            }

            System.out.println("List of income entries:");
            for (int i = 0; i < IncomeManager.getIncomeList().size(); i++) {
                Income income = IncomeManager.getIncomeList().get(i);
                System.out.println((i + 1) + ". $" + income.getAmount() + " from " + income.getSource());
            }

            System.out.println("Total Income: $" + summary.getTotalIncome());
            System.out.println("Successfully listed income entries."); // logging message

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while listing income entries.", e);
            System.out.println("An error occurred while retrieving income entries.");
        }
    }
}

