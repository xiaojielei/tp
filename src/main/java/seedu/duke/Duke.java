package seedu.duke;

import commands.Command;
import commands.IncomeCommand;
import commands.ListIncomeCommand;
import commands.ExitCommand;
import commands.ViewExpenseCommand;
import exceptions.BudgetTrackerException;
import expenses.Ui;
import income.IncomeParser;
import savings.SavingCommandHandler;
import summary.Summary;
import summary.ui.SummaryDisplay;
import ui.HelpDisplay;
import expenses.BudgetTracker;
import expenses.ExpenseParser;
import expenses.ExpenseList;
import savings.Saving;
import alerts.FundsAlert;
import alerts.AlertParser;
import java.util.Scanner;
import util.LoggingConfigurator;

public class Duke {
    private final Scanner in;
    private final Summary summary;
    private final SummaryDisplay summaryDisplay;
    private final HelpDisplay helpDisplay;
    private final Ui ui;
    private final BudgetTracker tracker;
    private final ExpenseList expenseList;
    private final Saving saving;
    private final FundsAlert fundsAlert;
    private final SavingCommandHandler handler;
    
    /**
     * Constructs a new Duke application with all necessary components initialized.
     */
    public Duke() {
        in = new Scanner(System.in);
        summary = new Summary();
        summaryDisplay = new SummaryDisplay(summary);
        helpDisplay = new HelpDisplay();
        ui = new Ui();
        tracker = new BudgetTracker();
        expenseList = new ExpenseList();
        saving = new Saving(summary);
        fundsAlert = new FundsAlert(ui);
        summary.registerObserver(fundsAlert);
        handler = new SavingCommandHandler(saving);

        assert ui != null : "Ui should be initialized";
        assert summary != null : "Summary should be initialized";
        assert summaryDisplay != null : "SummaryDisplay should be initialized";
        assert fundsAlert != null : "FundsAlert should be initialized";
        assert helpDisplay != null : "HelpDisplay should be initialized";
        assert handler != null : "SavingCommandHandler should be initialized";
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        LoggingConfigurator.configureSummaryFileLogging();
        LoggingConfigurator.configureAlertsFileLogging();
        displayWelcomeMessage();
        new Duke().execute();
    }

    /**
     * Displays the welcome message for the application.
     */
    private static void displayWelcomeMessage() {
        System.out.println("Welcome to Common Cents!");
        System.out.println("Use `help` to see available commands.");
    }

    /**
     * Runs the main program loop, processing user commands until exit.
     */
    public void execute() {
        fundsAlert.displayInitialNotification();

        while (true) {
            try {
                // Check if there's input available before reading
                if (!in.hasNextLine()) {
                    System.out.println("No input available. Exiting program.");
                    break;
                }

                String fullCommand = in.nextLine();
                String trimmedCommand = fullCommand.trim();
                Command command = null;
                IncomeCommand incomeCommand = null;
                boolean commandRecognized = false;

                if (trimmedCommand.equals("help")) {
                    helpDisplay.display();
                    commandRecognized = true;
                    continue;
                }

                if (fullCommand.startsWith("bye")) {
                    Command exitCommand = new ExitCommand();
                    exitCommand.execute(expenseList, ui);
                    break;
                }

                if (fullCommand.equals("view income")) {
                    ListIncomeCommand listIncomeCommand = new ListIncomeCommand(summary);
                    listIncomeCommand.execute();
                    commandRecognized = true;
                    continue;
                } else if (fullCommand.trim().equals("view expense")) {
                    ViewExpenseCommand viewExpenseCommand = new ViewExpenseCommand(expenseList);
                    viewExpenseCommand.execute(expenseList, ui);
                    commandRecognized = true;
                    continue;
                } else if (trimmedCommand.equals("view summary")) {
                    summaryDisplay.displaySummary();
                    commandRecognized = true;
                    continue;
                }

                if (trimmedCommand.startsWith("alert")) {
                    try {
                        Command alertCommand = AlertParser.parse(trimmedCommand, fundsAlert);
                        alertCommand.execute(expenseList, ui);
                        commandRecognized = true;
                        continue;
                    } catch (BudgetTrackerException e) {
                        ui.showMessage(e.getMessage());
                        commandRecognized = true;
                        continue;
                    }
                }

                if (fullCommand.startsWith("add income")) {
                    incomeCommand = IncomeParser.parseAddIncomeCommand(fullCommand, summary);
                    commandRecognized = true;
                } else if (fullCommand.startsWith("delete income")) {
                    incomeCommand = IncomeParser.parseDeleteIncomeCommand(fullCommand, summary);
                    commandRecognized = true;
                }

                if (fullCommand.startsWith("add expense")) {
                    command = ExpenseParser.parse(fullCommand, summary, expenseList);
                    commandRecognized = true;
                } else if (fullCommand.startsWith("delete expense")) {
                    command = ExpenseParser.parse(fullCommand, summary, expenseList);
                    commandRecognized = true;
                }

                if (fullCommand.contains("savings")) {
                    handler.processSavingCommand(fullCommand);
                    commandRecognized = true;
                }

                if (incomeCommand != null) {
                    tracker.executeincomeCommand(incomeCommand, ui);
                    if (incomeCommand.isExit()) {
                        break;
                    }
                }

                if (command != null) {
                    command.execute(expenseList, ui);
                    if (command.isExit()) {
                        break;
                    }
                }

                // Handle unrecognized commands
                if (!commandRecognized && !fullCommand.trim().isEmpty()) {
                    System.out.println("Oops! I don't recognize that command. Type 'help' to see available commands.");
                }

            } catch (BudgetTrackerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
