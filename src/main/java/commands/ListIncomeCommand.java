package commands;

import income.Income;
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
    public ListIncomeCommand(Summary summary) {
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
                // Use getter methods to access the private fields
                Income income = IncomeManager.getIncomeList().get(i);
                System.out.println((i + 1) + ". $" + income.getAmount() + " from " + income.getSource());
            }
            System.out.println("Total Income: $" + summary.getTotalIncome()); // Display total income
        }
    }
}

