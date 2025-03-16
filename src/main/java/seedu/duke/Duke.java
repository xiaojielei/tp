package seedu.duke;

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

        Summary summary = new Summary();
        SummaryDisplay summaryDisplay = new SummaryDisplay(summary);
        HelpDisplay helpDisplay = new HelpDisplay();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
