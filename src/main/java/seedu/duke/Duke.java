package seedu.duke;

import exceptions.BudgetTrackerException;
import expenses.Ui;
import income.IncomeParser;
import commands.IncomeCommand;
import commands.ListIncomeCommand;
import commands.ViewExpenseCommand;
import summary.Summary;
import summary.ui.SummaryDisplay;
import ui.HelpDisplay;
import expenses.BudgetTracker;
import commands.Command;
import expenses.ExpenseParser;
import expenses.ExpenseList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner in = new Scanner(System.in);
        Summary summary = new Summary();
        SummaryDisplay summaryDisplay = new SummaryDisplay(summary);
        HelpDisplay helpDisplay = new HelpDisplay();
        Ui ui = new Ui();
        BudgetTracker tracker = new BudgetTracker();
        ExpenseList expenseList = new ExpenseList();

        in.nextLine();
        helpDisplay.displayHelp();
        System.out.println(summaryDisplay.displaySummary());

        while (true) {
            try {
                String fullCommand = in.nextLine();
                Command command = null;
                IncomeCommand incomeCommand = null;

                // Handling view commands
                if (fullCommand.equals("view income")) {
                    ListIncomeCommand listIncomeCommand = new ListIncomeCommand(summary);
                    listIncomeCommand.execute();
                    continue;
                } else if (fullCommand.equals("view expense")) {
                    ViewExpenseCommand viewExpenseCommand = new ViewExpenseCommand();
                    viewExpenseCommand.execute(expenseList, ui);
                    continue;
                }

                // Handling income-related commands
                if (fullCommand.startsWith("add income")) {
                    incomeCommand = IncomeParser.parseAddIncomeCommand(fullCommand);
                } else if (fullCommand.startsWith("delete income")) {
                    incomeCommand = IncomeParser.parseDeleteIncomeCommand(fullCommand);
                }

                // Handling expense-related commands
                if (fullCommand.startsWith("add expense")) {
                    command = ExpenseParser.parse(fullCommand);
                } else if (fullCommand.startsWith("delete expense")) {
                    command = ExpenseParser.parse(fullCommand);
                }

                // Execute income commands
                if (incomeCommand != null) {
                    tracker.executeincomeCommand(incomeCommand, ui);
                }
                if (incomeCommand != null && incomeCommand.isExit()) {
                    break;
                }

                // Execute expense commands
                if (command != null) {
                    tracker.executeCommand(command, ui);
                }
                if (command != null && command.isExit()) {
                    break;
                }
            } catch (BudgetTrackerException e) {
                System.out.println(e.getMessage());
            }
        }
        displayExitMessage();
    }

    public static void displayWelcomeMessage() {
        System.out.println("Welcome to Common Cents!");
        System.out.println("Use `help` to see available commands.");
    }

    public static void displayExitMessage() {
        System.out.println("Thank you for using Common Cents. See you next time!");
    }
}


