package expenses;

import commands.AddExpenseCommand;
import commands.DeleteExpenseCommand;
import commands.ViewExpenseCommand;
import commands.Command;
import exceptions.BudgetTrackerException;
import summary.Summary;

public class ExpenseParser {

    public static Command parse(String fullCommand,
                                Summary summary,
                                ExpenseList expenseList) throws BudgetTrackerException {
        String[] words = fullCommand.split(" ", 2);  // Split the command into words
        String commandWord = words[0].toLowerCase(); // First word is the command
        String argument = words.length > 1 ? words[1] : ""; // Remaining part is the argument

        switch (commandWord) {
        case "add":
            if (argument.startsWith("expense ")) {
                return parseAddExpense(argument.substring(8).trim(), summary);
            } else {
                throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <source>");
            }

        case "view":
            if (argument.equalsIgnoreCase("expense")) {
                return new ViewExpenseCommand(expenseList);
            }
            break;

        case "delete":
            if (argument.startsWith("expense ")) {
                return parseDeleteExpense(argument.substring(8).trim(), summary);
            }
            break;

        default:
            throw new BudgetTrackerException("Invalid command! Please enter a valid command.");
        }
        return null;
    }

    private static Command parseAddExpense(String argument, Summary summary) throws BudgetTrackerException {
        if (argument.isEmpty()) {
            throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <source>");
        }

        String[] parts = argument.split(" / ", 2);
        if (parts.length < 2) {
            throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <source>");
        }

        try {
            double amount = Double.parseDouble(parts[0].trim());  // Parse the amount
            String description = parts[1].trim();  // Parse the description
            return new AddExpenseCommand(amount, description, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid amount! Please enter a valid number.");
        }
    }

    private static Command parseDeleteExpense(String argument, Summary summary) throws BudgetTrackerException {
        try {
            int expenseNumber = Integer.parseInt(argument.trim());
            return new DeleteExpenseCommand(expenseNumber, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid expense number! Please enter a valid number.");
        }
    }
}
