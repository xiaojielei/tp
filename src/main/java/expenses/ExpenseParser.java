package expenses;

import commands.AddExpenseCommand;
import commands.DeleteExpenseCommand;
import commands.ViewExpenseCommand;
import commands.Command;
import exceptions.BudgetTrackerException;
import summary.Summary;

/**
 * Parses user input for expense-related commands and returns the corresponding command objects.
 */
public class ExpenseParser {

    /**
     * Parses a given user command string and returns the corresponding Command object.
     *
     * @param fullCommand The full user input command.
     * @param summary     The summary object that tracks financial data.
     * @param expenseList The list of expenses.
     * @return The appropriate Command object based on the user's input.
     * @throws BudgetTrackerException If the command format is incorrect or invalid.
     */
    public static Command parse(String fullCommand, Summary summary, ExpenseList expenseList)
            throws BudgetTrackerException {
        String[] words = fullCommand.trim().split(" ", 2); // Split into command and arguments
        String commandWord = words[0].toLowerCase(); // First word is the command
        String argument = words.length > 1 ? words[1].trim() : ""; // Remaining part is the argument

        switch (commandWord) {
        case "add":
            if (argument.startsWith("expense ")) {
                return parseAddExpense(argument.substring(8).trim(), summary);
            }
            throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <description>");

        case "view":
            if (argument.equalsIgnoreCase("expense")) {
                return new ViewExpenseCommand(expenseList);
            }
            throw new BudgetTrackerException("Invalid format! Use: view expense");

        case "delete":
            if (argument.startsWith("expense ")) {
                return parseDeleteExpense(argument.substring(8).trim(), summary);
            }
            throw new BudgetTrackerException("Invalid format! Use: delete expense <index>");

        default:
            throw new BudgetTrackerException("Invalid command! Type 'help' to see the available commands.");
        }
    }

    /**
     * Parses the arguments for adding an expense.
     *
     * @param argument The user input containing the amount and description.
     * @param summary  The summary object to update with the new expense.
     * @return An AddExpenseCommand object if the input is valid.
     * @throws BudgetTrackerException If the format is incorrect or amount is invalid.
     */
    private static Command parseAddExpense(String argument, Summary summary) throws BudgetTrackerException {
        if (argument.isEmpty()) {
            throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <description>");
        }

        String[] parts = argument.split(" / ", 2);
        if (parts.length < 2) {
            throw new BudgetTrackerException("Invalid format! Use: add expense <amount> / <description>");
        }

        try {
            double amount = Double.parseDouble(parts[0].trim());
            String description = parts[1].trim();
            return new AddExpenseCommand(amount, description, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid amount! Please enter a valid numeric value.");
        }
    }

    /**
     * Parses the arguments for deleting an expense.
     *
     * @param argument The user input containing the expense index.
     * @param summary  The summary object to update after deleting the expense.
     * @return A DeleteExpenseCommand object if the input is valid.
     * @throws BudgetTrackerException If the format is incorrect or the index is invalid.
     */
    private static Command parseDeleteExpense(String argument, Summary summary) throws BudgetTrackerException {
        try {
            int expenseNumber = Integer.parseInt(argument.trim());
            if (expenseNumber <= 0) {
                throw new BudgetTrackerException("Expense number must be a positive integer.");
            }
            return new DeleteExpenseCommand(expenseNumber, summary);
        } catch (NumberFormatException e) {
            throw new BudgetTrackerException("Invalid expense number! Please enter a valid integer.");
        }
    }
}
