package savings;

import java.util.ArrayList;
import java.util.List;

import exceptions.BudgetTrackerException;
import summary.Summary;

//substring functions (substring(17)/substring(20), etc.) are used to trim the input
//to only keep the amount/index/goal description part

/**
 * The Saving class manages savings records, allowing users to add, delete,
 * view, and set goals for their savings.
 */
public class Saving {
    /**
     * Represents a single savings record with an amount and an optional goal.
     */
    private static class SavingsRecord {
        double amount;
        String goal;

        /**
         * Constructs a SavingsRecord with a specified amount.
         * @param amount The savings amount.
         */
        public SavingsRecord(double amount) {
            this.amount = amount;
            this.goal = " ";
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        /**
         * Sets the goal for this savings record.
         * @param goal The goal description.
         */
        public void setGoal(String goal) {
            this.goal = goal;
        }

        @Override
        public String toString() {
            return "[" + goal + "] " + amount;
        }
    }

    private final List<SavingsRecord> savingsRecords = new ArrayList<>();
    private final Summary summary;

    /**
     * Constructor that accepts a Summary instance.
     * @param summary the shared Summary instance
     */
    public Saving(Summary summary) {
        this.summary = summary;
    }

    /**
     * Adds a savings record with the specified amount.
     * @param amount The amount to save.
     */
    public void addSavings(double amount) throws BudgetTrackerException {
        assert amount > 0 : "Savings amount must be positive";

        savingsRecords.add(new SavingsRecord(amount));
        System.out.println("Sure! I have added your savings:");
        System.out.println((savingsRecords.size()) + ". \t" + savingsRecords.get(savingsRecords.size() - 1));
        System.out.println("Now you have " + savingsRecords.size() + " saving(s) in your list.");
        summary.addSavings(amount);
    }

    /**
     * Deletes a savings record at the specified index.
     * @param index The index of the savings record to delete.
     */
    public void deleteSavings(int index) {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex >= 0 && zeroBasedIndex < savingsRecords.size()) {
            SavingsRecord removedRecord = savingsRecords.get(zeroBasedIndex);

            System.out.println("Sure! I have deleted the saving:");
            System.out.println(index + ". \t" + removedRecord);

            savingsRecords.remove(zeroBasedIndex);

            System.out.println("Now you have " + savingsRecords.size() + " savings in your list.");

            try {
                summary.removeSavings(removedRecord.amount);
            } catch (BudgetTrackerException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid index.");
        }
    }



    /**
     * Displays all savings records.
     */
    public void viewSavings() {
        if (savingsRecords.isEmpty()) {
            System.out.println("No savings records.");
        } else {
            System.out.println("Here are the savings in your list:");
            for (int i = 0; i < savingsRecords.size(); i++) {
                System.out.println((i + 1) + ". \t" + savingsRecords.get(i));
            }
            System.out.println("You have " + savingsRecords.size() + " saving(s) in total.");
        }
        System.out.println("Savings Indicator: " + getSavingsIndicator());
    }

    /**
     * Sets a savings goal for a specific amount.
     * @param amount The savings amount to associate with the goal.
     * @param description The goal description.
     */
    public void setSavingsGoal(double amount, String description) {
        for (SavingsRecord record : savingsRecords) {
            if (record.amount == amount) {
                record.setGoal(description);
                System.out.println("I have set your saving goal:");
                System.out.println("[" + description + "] " + record.amount);
                return;
            }
        }
        System.out.println("Invalid amount.");
    }

    /**
     * Updates a savings goal for a specified index.
     * @param index The index of the savings record.
     * @param amount The new savings amount.
     * @param description The new goal description.
     */
    public void updateSavingsGoal(int index, double amount, String description) {
        if (index >= 0 && index < savingsRecords.size()) {
            SavingsRecord record = savingsRecords.get(index);
            record.amount = amount;
            record.goal = description;
            System.out.println("I have updated your saving amount and saving goal:");
            System.out.println((index + 1) + ". \t" + record);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Deletes the goal of a specified savings record.
     * @param index The index of the savings record.
     */
    public void deleteSavingsGoal(int index) {
        if (index >= 0 && index < savingsRecords.size()) {
            savingsRecords.get(index).goal = " ";
            System.out.println("I have deleted the saving goal:");
            System.out.println((index + 1) + ". \t" + savingsRecords.get(index));
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
        fromIndex -= 1;
        toIndex -= 1;

        // Check for valid indices
        if (fromIndex < 0 || fromIndex >= savingsRecords.size() ||
                toIndex < 0 || toIndex >= savingsRecords.size()) {
            throw new IllegalArgumentException("Invalid index.");
        }

        // Prevent transferring to the same record
        if (fromIndex == toIndex) {
            throw new IllegalArgumentException("Cannot transfer to the same savings record.");
        }

        SavingsRecord fromRecord = savingsRecords.get(fromIndex);
        SavingsRecord toRecord = savingsRecords.get(toIndex);

        // Check for sufficient funds
        if (fromRecord.getAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds in the source savings.");
        }

        // Perform the transfer using setters
        fromRecord.setAmount(fromRecord.getAmount() - amount);
        toRecord.setAmount(toRecord.getAmount() + amount);

        System.out.printf("Transferred %.2f from savings %d to savings %d.%n", amount, fromIndex + 1, toIndex + 1);
        System.out.println("Updated records:");
        System.out.println((fromIndex + 1) + ". \t" + fromRecord);
        System.out.println((toIndex + 1) + ". \t" + toRecord);
    }


    /**
     * Runs the savings management system, processing user commands.
     * @param input The Scanner object for user input.
     */
    public void run(String input) {
        String[] parts = input.split(" ", 4);
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        if (input.contains("add savings")) {
            try {
                double amount = Double.parseDouble(parts[2]);
                addSavings(amount);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid amount format.");
            } catch (BudgetTrackerException e) {
                throw new RuntimeException(e);
            }
        } else if (input.contains("delete savings")) {
            try {
                int index = Integer.parseInt(parts[2]);
                deleteSavings(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid index format.");
            }
        } else if (input.contains("savings goal set")) {
            try {
                String[] goalParts = input.substring(17).split(" / ", 2);
                double amount = Double.parseDouble(goalParts[0].trim());
                String description = goalParts[1].trim();
                setSavingsGoal(amount, description);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: savings goal set <AMOUNT> / <DESCRIPTION>");
            }
        } else if (input.contains("savings goal update")) {
            try {
                String[] goalParts = input.substring(20).split(" / ", 2);
                String[] indexAmount = goalParts[0].trim().split(" ");
                int index = Integer.parseInt(indexAmount[0]) - 1;
                double amount = Double.parseDouble(indexAmount[1]);
                String description = goalParts[1].trim();
                updateSavingsGoal(index, amount, description);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: savings goal update <INDEX> <AMOUNT> / <DESCRIPTION>");
            }
        } else if (input.contains("savings goal delete")) {
            try {
                int index = Integer.parseInt(parts[3]) - 1;
                deleteSavingsGoal(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid index format.");
            }
        }  else if (input.contains("transfer savings")) {
            try {
                String[] partsTransfer = input.substring(17).split(" ");
                int fromIndex = Integer.parseInt(partsTransfer[0]);
                int toIndex = Integer.parseInt(partsTransfer[1]);
                double amount = Double.parseDouble(partsTransfer[2]);
                transferSavings(fromIndex, toIndex, amount);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>");
            }
        } else if (input.contains("exit savings")) {
            System.out.println("Exited savings function.");
        } else if (input.contains("view savings") || input.contains("savings goal view")) {
            viewSavings();
        } else {
            System.out.println("Unknown command.");
        }
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
