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

    public IncomeManager() {}

    public static IncomeManager getInstance() {
        return instance;
    }
    /**
     * Adds a new income entry to the list.
     *
     * @param income the income entry to add
     */
    public static void addIncome(Income income) {
        incomeList.add(income);
    }

    /**
     * Deletes an income entry at the specified index.
     *
     * @param index the index of the income entry to delete
     * @throws BudgetTrackerException if the index is out of bounds
     */
    public static void deleteIncome(int index) throws BudgetTrackerException {
        // Validate index is within bounds
        if (index < 0 || index >= incomeList.size()) {
            throw new BudgetTrackerException("Invalid index: " + (index + 1) + ". Index must be between 1 and " + incomeList.size());
        }
        
        // Check that the list is not empty
        if (incomeList.isEmpty()) {
            throw new BudgetTrackerException("Cannot delete from an empty income list.");
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
        incomeList.clear();
    }
}
