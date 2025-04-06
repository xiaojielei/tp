package income;

import java.util.ArrayList;
import java.util.List;
import exceptions.BudgetTrackerException;

/**
 * Manages the list of income entries. Provides methods to add, delete,
 * retrieve, and clear income entries.
 */
public class IncomeManager {
    private static final IncomeManager instance = new IncomeManager();
    private static final List<Income> incomeList = new ArrayList<>();

    /**
     * Constructs an IncomeManager instance.
     * Public constructor allows external instantiation if needed.
     */
    public IncomeManager() {}

    /**
     * Retrieves the singleton instance of IncomeManager.
     *
     * @return The singleton instance of IncomeManager.
     */
    public static IncomeManager getInstance() {
        return instance;
    }

    /**
     * Adds a new income entry to the list.
     *
     * @param income the income entry to add
     */
    public static void addIncome(Income income) {
        assert !incomeList.isEmpty() : "Income list should not be empty after adding an income";
        incomeList.add(income);
    }

    /**
     * Deletes an income entry at the specified index.
     *
     * @param index the index of the income entry to delete
     * @throws BudgetTrackerException if the index is out of bounds
     */
    public static void deleteIncome(int index) throws BudgetTrackerException {
        if (incomeList.isEmpty()) {
            throw new BudgetTrackerException("Cannot delete from an empty income list.");
        }

        if (index < 0 || index >= incomeList.size()) {
            throw new BudgetTrackerException("Invalid index: " + (index + 1)
                    + ". Index must be between 1 and " + incomeList.size());
        }

        incomeList.remove(index);
    }

    /**
     * Returns the list of income entries.
     *
     * @return the list of income entries
     */
    public static List<Income> getIncomeList() {
        return incomeList;
    }

    /**
     * Clears all income entries from the list.
     */
    public static void clearIncomeList() {
        assert incomeList != null : "Income list should not be null";
        incomeList.clear();
    }
}
