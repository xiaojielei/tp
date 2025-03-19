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
        if (index < 1 || index > IncomeManager.getIncomeList().size()) {
            throw new BudgetTrackerException("Invalid index. Please provide a valid income index.");
        }

        Income incomeToDelete = IncomeManager.getIncomeList().get(index - 1);
        double amountToRemove = incomeToDelete.getAmount();  // Use the getter method

        IncomeManager.deleteIncome(index - 1);
        summary.removeIncome(amountToRemove);

        ui.showMessage("Deleted income entry at index " + index);  // Assuming ui has this method
    }
    @Override
    public boolean isExit() {
        return false; // AddIncomeCommand does not exit the program
    }

}
