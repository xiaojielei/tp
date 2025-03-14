package seedu.commoncents;

import summary.Summary;
import summary.ui.SummaryDisplay;
import ui.HelpDisplay;

import java.util.Scanner;

public class CommonCents {
    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        System.out.println("Hello " + name + "!");

        Summary summary = new Summary();
        SummaryDisplay summaryDisplay = new SummaryDisplay(summary);
        HelpDisplay helpDisplay = new HelpDisplay();

        in.close();
    }
}
