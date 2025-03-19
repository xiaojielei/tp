package income;

import commands.AddIncomeCommand;
import commands.DeleteIncomeCommand;
import exceptions.BudgetTrackerException;
import summary.Summary;

public class IncomeParser {

    public static AddIncomeCommand parseAddIncomeCommand(String fullCommand, Summary summary) throws BudgetTrackerException {
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

    public static DeleteIncomeCommand parseDeleteIncomeCommand(String fullCommand, Summary summary) throws BudgetTrackerException {
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
