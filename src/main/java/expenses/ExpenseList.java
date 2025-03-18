package expenses;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    private List<Expense> expenses;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

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
    public boolean deleteExpense(int expenseNumber) {
        if (expenseNumber > 0 && expenseNumber <= expenses.size()) {
            expenses.remove(expenseNumber - 1); // List is 0-indexed, so we subtract 1
            return true;
        }
        return false;
    }
}
