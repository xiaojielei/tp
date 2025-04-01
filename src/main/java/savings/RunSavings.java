package savings;

import exceptions.BudgetTrackerException;

public class RunSavings {
    /**
     * Runs the savings management system, processing user commands.
     * @param input The Scanner object for user input.
     */
    public static void run(String input) {
        String[] parts = input.split(" ", 4);
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        if (input.contains("add savings")) {
            try {
                double amount = Double.parseDouble(parts[2]);
                Saving.addSavings(amount);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid amount format.");
            } catch (BudgetTrackerException e) {
                throw new RuntimeException(e);
            }
        } else if (input.contains("delete savings")) {
            try {
                int index = Integer.parseInt(parts[2]);
                Saving.deleteSavings(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid index format.");
            }
        } else if (input.contains("savings goal set")) {
            try {
                int trimSavingsGoalSetInUserCommand = 17;
                String[] goalParts = input.substring(trimSavingsGoalSetInUserCommand).split(" / ", 2);
                double amount = Double.parseDouble(goalParts[0].trim());
                String description = goalParts[1].trim();
                Saving.setSavingsGoal(amount, description);
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
                Saving.updateSavingsGoal(index, amount, description);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: savings goal update <INDEX> <AMOUNT> / <DESCRIPTION>");
            }
        } else if (input.contains("savings goal delete")) {
            try {
                int index = Integer.parseInt(parts[3]) - 1;
                Saving.deleteSavingsGoal(index);
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
                Saving.transferSavings(fromIndex, toIndex, amount);
            } catch (Exception e) {
                System.out.println("Invalid format. Use: transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>");
            }
        } else if (input.contains("exit savings")) {
            System.out.println("Exited savings function.");
        } else if (input.contains("view savings") || input.contains("savings goal view")) {
            Saving.viewSavings();
        } else {
            System.out.println("Unknown command.");
        }
    }
}
