package income;

import commands.AddIncomeCommand;
import commands.DeleteIncomeCommand;
import exceptions.BudgetTrackerException;
import summary.Summary;

/**
 * Parses user input related to income commands and returns appropriate command objects.
 */
public class IncomeParser {

    /**
     * Parses the user input for the "add income" command and returns an AddIncomeCommand object.
     *
     * @param fullCommand The full user input string containing the command and parameters.
     * @param summary     The Summary object to update with the new income.
     * @return An AddIncomeCommand object containing the parsed income details.
     * @throws BudgetTrackerException If the input format is incorrect or contains invalid data.
     */
    public static AddIncomeCommand parseAddIncomeCommand(String fullCommand, Summary summary)
            throws BudgetTrackerException {
        String[] parts = fullCommand.split(" / ");
        if (parts.length != 2) {
            throw new BudgetTrackerException("Invalid format for 'add income' command. " +
                    "Please use 'add income <AMOUNT> / <SOURCE>'");
        }

        try {
            double amount = Double.parseDouble(parts[0].replace("add income", "").trim());
            String source = parts[1].trim();
            return new AddIncomeCommand(amount, source, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid amount format. Please provide a valid number.");
        }
    }

    /**
     * Parses the user input for the "delete income" command and returns a DeleteIncomeCommand object.
     *
     * @param fullCommand The full user input string containing the command and parameters.
     * @param summary     The Summary object to update after deleting the income entry.
     * @return A DeleteIncomeCommand object containing the parsed index to delete.
     * @throws BudgetTrackerException If the input format is incorrect or contains an invalid index.
     */
    public static DeleteIncomeCommand parseDeleteIncomeCommand(String fullCommand, Summary summary)
            throws BudgetTrackerException {
        String[] parts = fullCommand.split(" ");
        if (parts.length != 3 || !parts[1].equalsIgnoreCase("income")) {
            throw new BudgetTrackerException("Invalid format for 'delete income' command. " +
                    "Please use 'delete income <INDEX>'");
        }

        try {
            int index = Integer.parseInt(parts[2].trim());
            return new DeleteIncomeCommand(index, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid index format. Please provide a valid number.");
        }
    }
}
