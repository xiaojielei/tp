package commands;

import exceptions.BudgetTrackerException;
import expenses.Ui;
import income.Income;
import income.IncomeManager;
import summary.Summary;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command to delete an income entry from the list and update the summary.
 */
public class DeleteIncomeCommand extends IncomeCommand {
    private static final Logger logger = Logger.getLogger(DeleteIncomeCommand.class.getName());
    private final int index;
    private final Summary summary;

    /**
     * Creates a new DeleteIncomeCommand with the specified index and summary.
     *
     * @param index   The index of the income entry to delete.
     * @param summary The summary to update.
     */
    public DeleteIncomeCommand(int index, Summary summary) throws BudgetTrackerException {
        if (index < 1) {
            logger.log(Level.SEVERE, "Invalid income index: {0}", index);
            throw new BudgetTrackerException("Index must be at least 1.");
        }
        if (summary == null) {
            logger.severe("Summary object cannot be null.");
            throw new BudgetTrackerException("Summary instance is required.");
        }
        this.index = index;
        this.summary = summary;
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
     * Executes the command to delete the income entry at the specified index.
     *
     * @param incomeManager The income manager handling income entries.
     * @param ui            The UI component for displaying messages.
     * @throws BudgetTrackerException If index is invalid or income list is empty.
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
        // Check if the income list is empty
        if (IncomeManager.getIncomeList().isEmpty()) {
            throw new BudgetTrackerException("There are no income entries to delete.");
        }

        // Check if the index is within bounds
        if (index > IncomeManager.getIncomeList().size()) {
            logger.log(Level.SEVERE, "Invalid index: {0}, valid range: 1 to {1}",
                    new Object[]{index, IncomeManager.getIncomeList().size()});
            throw new BudgetTrackerException("Invalid index. Please provide a valid income index between 1 and "
                    + IncomeManager.getIncomeList().size() + ".");
        }

        Income incomeToDelete = IncomeManager.getIncomeList().get(index - 1);
        double amountToRemove = incomeToDelete.getAmount();

        double availableBalanceAfterRemoval = summary.getAvailableFunds() - amountToRemove;
        if (availableBalanceAfterRemoval < 0) {
            logger.log(Level.SEVERE, "Cannot remove this income as it would result in negative available funds. " +
                            "Current expenses: {0}, Available balance after removal would be: {1}",
                    new Object[]{summary.getTotalExpense(), availableBalanceAfterRemoval});
            System.out.println("Cannot remove this income as it would result in negative available funds. " +
                    "Current expenses: " + summary.getTotalExpense() +
                    ", Available balance after removal would be: " + availableBalanceAfterRemoval);
        } else {
            IncomeManager.deleteIncome(index - 1);
            summary.removeIncome(amountToRemove);
            ui.showMessage("Deleted income entry at index " + index);
            System.out.println("Successfully deleted income at index " + index);
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
