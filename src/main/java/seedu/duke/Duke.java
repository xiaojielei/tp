package seedu.duke;

import commands.AddIncomeCommand;
import commands.DeleteIncomeCommand;
import commands.ListIncomeCommand;
import exceptions.BudgetTrackerException;
import summary.Summary;
import summary.ui.SummaryDisplay;
import ui.HelpDisplay;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        displayWelcomeMessage();

        Summary summary = new Summary();
        SummaryDisplay summaryDisplay = new SummaryDisplay(summary);
        HelpDisplay helpDisplay = new HelpDisplay();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        try {
            AddIncomeCommand addIncomeCommand = new AddIncomeCommand(500.0, "Part-time job", summary);
            addIncomeCommand.execute();

            ListIncomeCommand listIncomeCommand = new ListIncomeCommand(summary);
            listIncomeCommand.execute();

            DeleteIncomeCommand deleteIncomeCommand = new DeleteIncomeCommand(1, summary);
            deleteIncomeCommand.execute();

        } catch (BudgetTrackerException e) {
            System.out.println(e.getMessage());
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
