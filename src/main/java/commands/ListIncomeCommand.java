package commands;

import income.IncomeManager;
import summary.Summary;

/**
 * Command to list all income entries and display the total income.
 * It retrieves the income entries from the IncomeManager and outputs
 * the details of each income entry along with the total income from
 * the Summary.
 */
public class ListIncomeCommand {
    private final Summary summary;

    /**
     * Creates a new ListIncomeCommand with the specified summary.
     *
     * @param summary the summary to retrieve the total income from
     */
    public ListIncomeCommand(Summary summary) { // Constructor accepting Summary
        this.summary = summary;
    }

    /**
     * Executes the command to list all income entries and display the total income.
     * If there are no income entries, a message is displayed indicating so.
     */
    public void execute() {
        if (IncomeManager.getIncomeList().isEmpty()) {
            System.out.println("No income entries available.");
        } else {
            System.out.println("List of income entries:");
            for (int i = 0; i < IncomeManager.getIncomeList().size(); i++) {
                System.out.println((i + 1) + ". $" + IncomeManager.getIncomeList().get(i).amount() +
                        " from " + IncomeManager.getIncomeList().get(i).source());
            }
            System.out.println("Total Income: $" + summary.getTotalIncome()); // Display total income
        }
    }
}
