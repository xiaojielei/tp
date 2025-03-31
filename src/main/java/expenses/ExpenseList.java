package expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a list of expenses with operations to add, delete, and display expenses.
 */
public class ExpenseList {
    private static final Logger logger = Logger.getLogger(ExpenseList.class.getName());
    private List<Expense> expenses;

    /**
     * Constructs an empty ExpenseList.
     */
    public ExpenseList() {
        this.expenses = new ArrayList<>();
        assert expenses.isEmpty() : "Expense list should be empty upon initialization.";
    }

    /**
     * Adds a new expense to the expense list.
     *
     * @param expense The expense to be added.
     */
    public void addExpense(Expense expense) {
        int prevSize = expenses.size();
        expenses.add(expense);
        logger.info("Added new expense: " + expense);

        assert expenses.size() == prevSize + 1 : "Expense list size should increase after adding an expense.";
        assert expenses.contains(expense) : "Expense list should contain the added expense.";
    }

    /**
     * Retrieves the list of expenses.
     *
     * @return A list of recorded expenses.
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Displays all recorded expenses. If no expenses exist, a message is displayed.
     */
    public void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            assert expenses.isEmpty() : "Expense list should be empty if 'No expenses recorded' is displayed.";
        } else {
            logger.info("Displaying expenses.");
            System.out.println("Your Expenses:");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
            assert !expenses.isEmpty() : "Expense list should not be empty when expenses are displayed.";
        }
    }

    /**
     * Deletes an expense from the list based on its index.
     *
     * @param expenseNumber The one-based index of the expense to be deleted.
     * @return {@code true} if the expense was successfully deleted, {@code false} otherwise.
     */
    public boolean deleteExpense(int expenseNumber) {
        int prevSize = expenses.size();
        if (expenseNumber > 0 && expenseNumber <= expenses.size()) {
            expenses.remove(expenseNumber - 1); // List is 0-indexed, so subtract 1

            assert expenses.size() == prevSize - 1 : "Expense list size did not decrease after deleting an expense.";
            return true;
        }
        logger.warning("Failed to delete expense. Invalid index: " + expenseNumber);
        assert expenses.size() == prevSize : "Expense list size should remain the same if deletion fails.";
        return false;
    }
}
