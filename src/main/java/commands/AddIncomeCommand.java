package commands;

import exceptions.BudgetTrackerException;
import income.Income;
import income.IncomeManager;
import summary.Summary;
import expenses.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

/**
 * Command to add a new income entry to the list and update the summary.
 */
public class AddIncomeCommand extends IncomeCommand {
    private static final Logger logger = Logger.getLogger(AddIncomeCommand.class.getName());
    private final double amount;
    private final String source;
    private final Summary summary;

    /**
     * Creates a new AddIncomeCommand with the specified amount, source, and summary.
     *
     * @param amount  The income amount.
     * @param source  The income source.
     * @param summary The summary to update.
     * @throws BudgetTrackerException If the input values are invalid.
     */
    public AddIncomeCommand(double amount, String source, Summary summary) throws BudgetTrackerException {
        if (amount <= 0) {
            logger.log(Level.SEVERE, "Invalid income amount: {0}", amount);
            throw new BudgetTrackerException("Income amount must be greater than zero.");
        }
        if (source.trim().isEmpty()) {
            logger.log(Level.SEVERE, "Invalid income source: {0}", source);
            throw new BudgetTrackerException("Income source cannot be empty.");
        }

        this.amount = amount;
        this.source = source;
        this.summary = summary;

        assert this.amount > 0 : "Income amount should always be positive.";
        assert !this.source.trim().isEmpty() : "Income source should not be empty.";
    }

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
     * Executes the command to add the income entry and updates the summary.
     *
     * @param incomeManager The income manager handling income entries.
     * @param ui            The UI component for displaying messages.
     * @throws BudgetTrackerException If an error occurs during execution.
     */
    @Override
    public void incomeExecute(IncomeManager incomeManager, Ui ui) throws BudgetTrackerException {
        if (incomeManager == null) {
            logger.severe("IncomeManager instance is null.");
            throw new BudgetTrackerException("IncomeManager cannot be null.");
        }
        if (ui == null) {
            logger.severe("Ui instance is null.");
            throw new BudgetTrackerException("Ui instance cannot be null.");
        }

        Income income = new Income(amount, source);
        try {
            IncomeManager.addIncome(income);
            summary.addIncome(amount);
            ui.showMessage("Added income: " + income);
            System.out.println("Successfully added income: " + income);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding income: " + income, e);
            throw new BudgetTrackerException("Failed to add income due to an unexpected error.");
        }
    }

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return false since this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}



