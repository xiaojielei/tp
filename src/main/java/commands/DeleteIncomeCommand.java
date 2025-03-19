package commands;

import exceptions.BudgetTrackerException;
import expenses.Ui;
import income.Income;
import income.IncomeManager;
import summary.Summary;

/**
 * Command to delete an income entry from the list and update the summary.
 */
public class DeleteIncomeCommand extends IncomeCommand {
    private final int index;
    private final Summary summary;

    /**
     * Creates a new DeleteIncomeCommand with the specified index and summary.
     *
     * @param index the index of the income entry to delete
     * @param summary the summary to update
     */
    public DeleteIncomeCommand(int index, Summary summary) {
        this.index = index;
        this.summary = summary;
    }

    /**
     * Executes the command to delete the income entry at the specified index.
     *
     * @throws BudgetTrackerException if the index is invalid
     */
    @Override
    public void incomeExecute(IncomeManager incomeManager, Ui ui) throws BudgetTrackerException {
        // Check if the income list is empty
        if (IncomeManager.getIncomeList().isEmpty()) {
            throw new BudgetTrackerException("There are no income entries to delete.");
        }
        
        // Check if the index is valid
        if (index < 1 || index > IncomeManager.getIncomeList().size()) {
            throw new BudgetTrackerException("Invalid index. Please provide a valid income index between 1 and " 
                    + IncomeManager.getIncomeList().size() + ".");
        }

        Income incomeToDelete = IncomeManager.getIncomeList().get(index - 1);
        double amountToRemove = incomeToDelete.getAmount();  // Use the getter method

        try {
            IncomeManager.deleteIncome(index - 1);
            summary.removeIncome(amountToRemove);
            ui.showMessage("Deleted income entry at index " + index);
        } catch (BudgetTrackerException e) {
            // This should not happen since we've already validated the index,
            // but we'll handle it just in case
            throw new BudgetTrackerException("Error deleting income: " + e.getMessage());
        }
    }
    
    @Override
    public boolean isExit() {
        return false; // DeleteIncomeCommand does not exit the program
    }
}
