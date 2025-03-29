package expenses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of expenses with operations to add, delete, and display expenses.
 */
public class ExpenseList {
    private List<Expense> expenses;

    /**
     * Constructs an empty ExpenseList.
     */
    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Adds a new expense to the expense list.
     *
     * @param expense The expense to be added.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
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
        } else {
            System.out.println("Your Expenses:");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
        }
    }

    /**
     * Deletes an expense from the list based on its index.
     *
     * @param expenseNumber The one-based index of the expense to be deleted.
     * @return {@code true} if the expense was successfully deleted, {@code false} otherwise.
     */
    public boolean deleteExpense(int expenseNumber) {
        if (expenseNumber > 0 && expenseNumber <= expenses.size()) {
            expenses.remove(expenseNumber - 1); // List is 0-indexed, so subtract 1
            return true;
        }
        return false;
    }
}
