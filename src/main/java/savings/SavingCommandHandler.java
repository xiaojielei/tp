package savings;

import exceptions.BudgetTrackerException;

public class SavingCommandHandler {
    private final Saving saving;

    public SavingCommandHandler(Saving saving) {
        this.saving = saving;
    }

    /**
     * Runs the savings management system, processing user commands.
     * @param input The Scanner object for user input.
     */
    public void processSavingCommand(String input) throws BudgetTrackerException {
        String[] parts = input.split(" ", 4);
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        if (input.contains("add savings")) {
            try {
                double amount = Double.parseDouble(parts[2]);
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
        } else if (input.contains("delete savings")) {
            try {
                int index = Integer.parseInt(parts[2]);
                saving.deleteSavings(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | BudgetTrackerException e) {
                System.out.println("Invalid index format.");
            }
        } else if (input.contains("savings goal set")) {
            try {
                int trimSavingsGoalSetInUserCommand = 17;
                String[] goalParts = input.substring(trimSavingsGoalSetInUserCommand).split(" / ", 2);
                double amount = Double.parseDouble(goalParts[0].trim());
                String description = goalParts[1].trim();
                saving.setSavingsGoal(amount, description);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: savings goal set <AMOUNT> / <DESCRIPTION>");
            }
        } else if (input.contains("savings goal update")) {
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
        } else if (input.contains("savings goal delete")) {
            try {
                int index = Integer.parseInt(parts[3]);
                saving.deleteSavingsGoal(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid index format.");
            }
        }  else if (input.contains("transfer savings")) {
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
        } else if (input.contains("exit savings")) {
            System.out.println("Exited savings management.");
        } else if (input.contains("view savings") || input.contains("savings goal view")) {
            saving.viewSavings();
        } else {
            System.out.println("Unknown command.");
        }
    }
}
