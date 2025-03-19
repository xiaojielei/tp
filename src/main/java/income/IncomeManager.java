package income;

import java.util.ArrayList;
import java.util.List;

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
     */
    public static void deleteIncome(int index) {
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
