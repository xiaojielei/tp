package savings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.BudgetTrackerException;
import summary.Summary;

/**
 * The Saving class manages savings records, allowing users to add, delete,
 * view, and set goals for their savings.
 */
public class Saving {

    private final List<SavingsRecord> savingsRecords = new ArrayList<>();
    private final Summary summary;

    /**
     * Constructor that accepts a Summary instance.
     * @param summary the shared Summary instance
     */
    public Saving(Summary summary) {
        this.summary = summary;
    }

    public List<SavingsRecord> getSavingsRecords() {
        return savingsRecords;
    }

    /**
     * Adds a savings record with the specified amount.
     * @param amount The amount to save.
     */
    public void addSavings(double amount, String goal) throws BudgetTrackerException {
        assert amount > 0 : "Savings amount must be positive";

        if (goal == null || goal.trim().isEmpty()) {
            goal = "(savings goal not provided)";
        }
        savingsRecords.add(new SavingsRecord(amount, goal));
        System.out.printf("Added to savings: $%.2f for %s%n", amount, goal);
        summary.addSavings(amount);
    }

    /**
     * Deletes a savings record at the specified index.
     * @param index The index of the savings record to delete.
     */
    public void deleteSavings(int index) throws BudgetTrackerException {
        if (savingsRecords.isEmpty()) {
            System.out.println("No saving records.");
            return;
        }
        if (index < 0 || index > savingsRecords.size()){
            System.out.println("Invalid index.");
            return;
        }

        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= savingsRecords.size()) {
            throw new BudgetTrackerException("Invalid index.");
        }
        SavingsRecord removedRecord = savingsRecords.remove(zeroBasedIndex);
        System.out.printf("Deleted savings: $%.2f for %s%n", removedRecord.getAmount(), removedRecord.getGoal());
        summary.removeSavings(removedRecord.getAmount());
    }

    /**
     * Displays all savings records.
     */
    public void viewSavings() {
        if (savingsRecords.isEmpty()) {
            System.out.println("No savings records.");
            return;
        }

        System.out.println("===== SAVINGS RECORDS =====");
        for (int i = 0; i < savingsRecords.size(); i++) {
            SavingsRecord record = savingsRecords.get(i);
            System.out.printf("%d. \t$%.2f for %s%n", i + 1, record.getAmount(), record.getGoal());
        }
        System.out.println("===========================");
        System.out.println("Savings Indicator: " + getSavingsIndicator());
    }

    /**
     * Sets a savings goal for a specific amount.
     * @param amount The savings amount to associate with the goal.
     * @param newSavingGoal The new goal description.
     */
    public void setSavingsGoal(double amount, String newSavingGoal) {
        for (SavingsRecord record : savingsRecords) {
            if (record.amount == amount) {
                record.setGoal(newSavingGoal);
                System.out.printf("Savings goal set: $%.2f for %s%n", amount, newSavingGoal);
                return;
            }
        }
        System.out.println("Invalid amount.");
    }

    /**
     * Deletes the goal of a specified savings record.
     * @param oneBasedIndex The index of the savings record.
     */
    public void deleteSavingsGoal(int oneBasedIndex) throws BudgetTrackerException {
        int zeroBasedIndex = oneBasedIndex - 1;
        if (oneBasedIndex > 0 && oneBasedIndex <= savingsRecords.size()) {
            if (Objects.equals(savingsRecords.get(zeroBasedIndex).goal, "(savings goal not provided)")) {
                System.out.println("Saving goal for this saving entry already does not exist");
                return;
            }
            Double originalSavingAmount = savingsRecords.get(zeroBasedIndex).amount;
            String originalSavingGoal = savingsRecords.get(zeroBasedIndex).goal;
            savingsRecords.get(zeroBasedIndex).goal = "(savings goal not provided)";
            System.out.printf("Deleted savings goal: $%.2f for %s(now the saving " +
                    "goal for this entry is empty)%n", originalSavingAmount, originalSavingGoal);
        } else {
            throw new BudgetTrackerException("Invalid index.");
        }
    }

    /**
     * Updates a savings goal for a specified index.
     * @param index The index of the savings record.
     * @param amount The new savings amount.
     * @param newSavingGoal The new goal description.
     */
    public void updateSavingsGoal(int index, double amount, String newSavingGoal) throws BudgetTrackerException {
        if (amount < 0) {
            throw new BudgetTrackerException("Invalid amount. Amount cannot be less than 0.");
        }

        if (index >= 0 && index < savingsRecords.size()) {
            SavingsRecord record = savingsRecords.get(index);
            double originalAmount = record.amount;
            record.amount = amount;
            record.goal = newSavingGoal;
            System.out.printf("Updated savings goal: $%.2f for %s%n", amount, newSavingGoal);

            double difference = amount - originalAmount;

            // Update summary only once with the net change
            if (difference > 0) {
                summary.addSavings(difference);
            } else if (difference < 0) {
                summary.removeSavings(-difference);
            }

        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Transfers a specified amount from one savings record to another.
     * @param fromIndex The index of the savings record to transfer from.
     * @param toIndex The index of the savings record to transfer to.
     * @param amount The amount to transfer.
     */
    public void transferSavings(int fromIndex, int toIndex, double amount) {
        if (fromIndex > savingsRecords.size() || toIndex > savingsRecords.size()) {
            System.out.println("There is no enough saving records.");
            return;
        } else if (fromIndex <= 0 || toIndex <= 0) {
            System.out.println("Invalid index.");
            return;
        }
        // Prevent transferring to the same record
        if (fromIndex == toIndex) {
            System.out.println("Cannot transfer to the same savings record.");
            return;
        }
        if (amount < 0) {
            System.out.println("Transferred amount cannot be less than 0.");
            return;
        }

        fromIndex -= 1;
        toIndex -= 1;

        SavingsRecord fromRecord = savingsRecords.get(fromIndex);
        SavingsRecord toRecord = savingsRecords.get(toIndex);

        // Check for sufficient funds
        if (fromRecord.getAmount() < amount) {
            System.out.println("Insufficient funds in the source savings.");
            return;
        }

        // Perform the transfer using setters
        fromRecord.setAmount(fromRecord.getAmount() - amount);
        toRecord.setAmount(toRecord.getAmount() + amount);

        System.out.printf("Transferred $%.2f from %s to %s%n", amount, fromRecord.getGoal(), toRecord.getGoal());
    }

    /**
     * Determines the savings indicator based on the total income.
     * @return "Good" if savings are above 80% of income, "Bad" if below 50%, otherwise "Neutral".
     */
    public String getSavingsIndicator() {
        double totalIncome = summary.getTotalIncome(); // Get total income from Summary
        double totalSavings = 0;

        for (SavingsRecord record : savingsRecords) {
            totalSavings += record.amount;
        }

        if (totalIncome == 0) {
            return "No income recorded.";
        }

        double savingsRatio = totalSavings / totalIncome;

        if (savingsRatio >= 0.8) {
            return "Good - You are saving well!";
        } else if (savingsRatio < 0.5) {
            return "Bad - Try to save more.";
        } else {
            return "Neutral - You are on track.";
        }
    }

}
