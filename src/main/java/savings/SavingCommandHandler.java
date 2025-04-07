package savings;

import exceptions.BudgetTrackerException;

public class SavingCommandHandler {
    private final Saving saving;

    public SavingCommandHandler(Saving saving) {
        this.saving = saving;
    }

    public void handleAddSavings(String input){
        try {
            String amountPart;
            String goal = "(savings goal not provided)";

            if (input.contains("/")) {
                String[] parts = input.split("/", 2);
                amountPart = parts[0].replaceFirst("add savings", "").trim();
                if (parts.length == 2 && !parts[1].trim().isEmpty()) {
                    goal = parts[1].trim();
                }
            } else {
                amountPart = input.replaceFirst("add savings", "").trim();
            }

            double amount = Double.parseDouble(amountPart);

            saving.addSavings(amount, goal);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | BudgetTrackerException e) {
            System.out.println("Invalid format.");
        }
    }

    public void handleDeleteSavings(String input) {
        try {
            String indexStr = input.replaceFirst("delete savings", "").trim();

            if (indexStr.isEmpty()) {
                System.out.println("Please provide an index.");
                return;
            }

            int index = Integer.parseInt(indexStr);
            saving.deleteSavings(index);

        } catch (NumberFormatException e) {
            System.out.println("Invalid index format.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting savings.");
        }
    }


    public void handleViewSavings(String input) {
        if (input.replaceFirst("view savings", "").trim().isEmpty()) {
            saving.viewSavings();
        } else {
            System.out.println("Invalid command.");
        }
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

    public void handleDeleteSavingsGoal(int index) {
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

        if (input.startsWith("add savings")) {
            handleAddSavings(input);
        } else if (input.startsWith("delete savings")) {
            handleDeleteSavings(input);
        } else if (input.startsWith("savings goal set")) {
            handleSetSavingsGoal(input);
        } else if (input.startsWith("savings goal update")) {
            handleUpdateSavingsGoal(input);
        } else if (input.startsWith("savings goal delete")) {
            handleDeleteSavingsGoal(Integer.parseInt(parts[3]));
        } else if (input.startsWith("transfer savings")) {
            handleTransferSavings(input);
        }  else if (input.startsWith("view savings")) {
            handleViewSavings(input);
        } else {
            handleUnknownCommand();
        }
    }
}
