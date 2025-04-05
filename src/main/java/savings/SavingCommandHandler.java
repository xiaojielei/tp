package savings;

import exceptions.BudgetTrackerException;

public class SavingCommandHandler {
    private final Saving saving;

    public SavingCommandHandler(Saving saving) {
        this.saving = saving;
    }

    public void handleAddSavings(String input, Double amount) {
        try {
            String goal;
            boolean goalMissing1 = !input.contains("/");
            boolean goalMissing2 = input.split("/").length < 2;
            boolean goalMissing3 = input.substring(input.indexOf("/") + 1).trim().isEmpty();
            if (goalMissing1 || goalMissing2 ||goalMissing3) {
                goal = "(savings goal not provided)";
            } else {
                goal = input.substring(input.indexOf("/") + 1).trim();
            }
            saving.addSavings(amount, goal);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | BudgetTrackerException e) {
            System.out.println("Invalid format.");
        }
    }

    public void handleDeleteSavings(String input, int index) {
        try {
            saving.deleteSavings(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | BudgetTrackerException e) {
            System.out.println("Invalid index format.");
        }
    }

    public void handleViewSavings() {
        saving.viewSavings();
    }

    public void handleSetSavingsGoal(String input) {
        try {
            int trimSavingsGoalSetInUserCommand = 17;
            String[] goalParts = input.substring(trimSavingsGoalSetInUserCommand).split(" / ", 2);
            double amount = Double.parseDouble(goalParts[0].trim());
            String description = goalParts[1].trim();
            saving.setSavingsGoal(amount, description);
        } catch (Exception e) {
            System.out.println("Invalid format. Use: savings goal set <AMOUNT> / <DESCRIPTION>");
        }
    }

    public void handleUpdateSavingsGoal(String input) {
        try {
            int trimSavingsGoalUpdateInUserCommand = 20;
            String[] goalParts = input.substring(trimSavingsGoalUpdateInUserCommand).split(" / ", 2);
            String[] indexAmount = goalParts[0].trim().split(" ");
            int index = Integer.parseInt(indexAmount[0]) - 1;
            double amount = Double.parseDouble(indexAmount[1]);
            String description = goalParts[1].trim();
            saving.updateSavingsGoal(index, amount, description);
        } catch (Exception e) {
            System.out.println("Invalid format. Use: savings goal update <INDEX> <AMOUNT> / <DESCRIPTION>");
        }
    }

    public void handleDeleteSavingsGoal(String input, int index) {
        try {
            saving.deleteSavingsGoal(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | BudgetTrackerException e) {
            System.out.println("Invalid index format.");
        }
    }

    public void handleTransferSavings(String input) {
        try {
            int trimTransferSavingsInUserCommand = 17;
            String[] partsTransfer = input.substring(trimTransferSavingsInUserCommand).split(" ");
            int fromIndex = Integer.parseInt(partsTransfer[0]);
            int toIndex = Integer.parseInt(partsTransfer[1]);
            double amount = Double.parseDouble(partsTransfer[2]);
            saving.transferSavings(fromIndex, toIndex, amount);
        } catch (Exception e) {
            System.out.println("Invalid format. Use: transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>");
        }
    }

    public void handleExitSavings() {
        System.out.println("Exited savings management.");
    }

    public void handleUnknownCommand() {
        System.out.println("Unknown command.");
    }

    /**
     * Runs the savings management system, processing user commands.
     * @param input The Scanner object for user input.
     */
    public void processSavingCommand(String input) throws BudgetTrackerException {
        String[] parts = input.split(" ", 5);
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        if (input.contains("add savings")) {
            handleAddSavings(input, Double.valueOf(parts[2]));
        } else if (input.contains("delete savings")) {
            handleDeleteSavings(input, Integer.parseInt(parts[2]));
        } else if (input.contains("savings goal set")) {
            handleSetSavingsGoal(input);
        } else if (input.contains("savings goal update")) {
            handleUpdateSavingsGoal(input);
        } else if (input.contains("savings goal delete")) {
            handleDeleteSavingsGoal(input, Integer.parseInt(parts[3]));
        } else if (input.contains("transfer savings")) {
            handleTransferSavings(input);
        } else if (input.contains("exit savings")) {
            handleExitSavings();
        } else if (input.contains("view savings") || input.contains("savings goal view")) {
            handleViewSavings();
        } else {
            handleUnknownCommand();
        }
    }
}
