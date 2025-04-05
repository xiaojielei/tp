# Yong Shin's Project Portfolio Page

## Overview
Budget Tracker is a command-line app that helps users manage their money. Users can track how much they earn, spend, and save using simple text commands. The app includes a money summary feature and alerts users when their funds get too low.

## Summary of Contributions

### [Code Contributed (Reposense Link)](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=yshinprograms&breakdown=true)

### Features I Built

#### Summary System (`summary` package)
*   **What it is**: Manages the core financial data (income, expenses, savings) and serves as the central data model for the application's financial state.
*   **Detailed Functionality**:
    *   `Summary.java`, is responsible for tracking the user's `totalIncome`, `totalExpense`, and `totalSavings`. It calculates the `availableFunds` (simply income minus expenses) to show current spending power. Methods are provided to add or remove financial records (income, expenses, savings) safely, incorporating checks to ensure data consistency and prevent invalid operations like creating a negative balance.
*   **Key Design Aspects/Features**:
    *   **Observer Pattern (Subject)**: Designed as the subject, notifying registered observers (`FinancialObserver`) of financial changes via `notifyObservers()`.
    *   **Data Integrity**: Implemented robust input validation (e.g., positive amounts only) and safeguards (e.g., preventing expenses exceeding available funds).
    *   **Reliability**: Utilized `java.util.logging` for tracing operations (configured via `LoggingConfigurator` to output to `logs/summary.log` instead of the console) and assertions to enforce internal consistency (e.g., totals never being negative).

#### Summary Display (`summary.ui` package)
*   **What it is**: Responsible for presenting the financial summary held by the `Summary` object to the user in a clear and structured format.
*   **Detailed Functionality**:
    *   `SummaryDisplay.java` obtains the current financial data from the `Summary` object. It then formats key figures – Total Income, Total Expenses, Available Balance, and Total Savings – into a well-aligned table using `String.format()`. This formatted summary is then printed directly to the console for the user.
*   **Key Design Aspects/Features**:
    *   **Separation of Concerns**: Decouples the user interface presentation logic from the core financial data management in `Summary`.

#### Alert System (`alerts`, `commands` packages)
*   **What it is**: Monitors the user's available funds, provides warnings when funds drop below a user-defined threshold, and handles user commands for setting this threshold.
*   **Detailed Functionality**:
    *   `FinancialObserver.java`: An interface specifying how components can react to financial updates.
    *   `FundsAlert.java`: Implements `FinancialObserver`, acting as an observer that watches the user's finances. It maintains a `warningThreshold` (defaulting to $5.00). When `Summary` updates its data, it notifies `FundsAlert`, which then checks if `availableFunds` are below the threshold, displaying a UI warning if necessary. It also presents an initial notification (`displayInitialNotification`) on startup.
    *   `AlertParser.java`: Processes the raw user input string for commands like `alert set <AMOUNT>`.
    *   `AlertCommand.java`: Executes the logic to update the warning threshold within the `FundsAlert` instance based on the parsed command details.
*   **Key Design Aspects/Features**:
    *   **Observer Pattern**: Uses the Observer pattern (`FundsAlert` as observer, `Summary` as subject) for loose coupling between financial tracking and alerting.
    *   **User Configurability**: Allows users to customize the alert threshold via the `alert set` command.
    *   **Reliability**: Incorporated `java.util.logging` for tracking alert checks and threshold changes (configured via `LoggingConfigurator` to output to `logs/alerts.log` instead of the console), along with assertions to ensure valid states (e.g., non-negative threshold).

#### User Help System (`ui` package)
*   **What it is**: Provides users with clear, categorized instructions on how to use the application's various commands.
*   **Detailed Functionality**:
    *   `HelpDisplay.java` focuses on generating and displaying user assistance. It constructs a comprehensive help message that lists all commands available in the application, including their syntax (e.g., `add income <AMOUNT> / <SOURCE>`) and a brief explanation of what they do. This help text is organized into logical sections (Income Management, Expense Management, Alerts, etc.) for clarity and is shown to the user when they issue the `help` command.
*   **Key Design Aspects/Features**:
    *   **User Experience**: Offers easily accessible, in-application documentation to guide users.
    *   **Maintainability**: Centralizes help text generation, making it easier to update as commands are added or modified.

### JUnit Tests
*   **`SummaryTest.java`**: Comprehensive tests covering initialization, balance calculations, adding/removing income and expenses (including edge cases like zero/negative values, insufficient funds, operations leading to negative balances), and savings operations.
*   **`SummaryDisplayTest.java`**: Tests verifying the correct formatting of the budget summary output for both populated and empty `Summary` objects.
*   **`AlertParserTest.java`**: Tests ensuring correct parsing of valid `alert set` commands and proper handling (exception throwing) for invalid formats (missing arguments, non-numeric input, unrecognized subcommands).
*   **`FundsAlertTest.java`**: Tests covering management of the warning threshold (default value, setting valid/invalid values), alert triggering logic (only when funds are strictly below the threshold), and initial notification messages.
*   **`HelpDisplayTest.java`**: Tests validating the content of the generated help text (presence of headers, essential commands) and confirming that the `display` method prints the output correctly.

### Project Management
* **Project Setup**:
  * Created and configured the GitHub organization and repository for the team
  * Established branch protection rules to enforce the forking workflow
  * Setup & configured Gradle for team use


* **Github Issues Management**
  * Created and standardized all priority, severity, and type issue tags/labels for better project tracking
  * Added, assigned and closed Github Issues in line with project tasks [Issue #6](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/6), [Issue #33](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/33), [Issue #133](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/133)


* **Release Management**:
  * Setup milestones & deadlines for v1.0, v2.0 & v2.1
  * Managed releases v1.0 and v2.0 on GitHub, which includes:
    * Conversion of UG and DG into PDF versions
    * Tagging the correct commit for release
    * Building the submission JAR files


* **Community Contributions**:
  * Reviewed and approved Pull Requests with non-trivial review comments: [#24](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/24), [#35](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/35), [#45](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/45), [#58](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/58#discussion_r2020096560)


### Documentation

### User Guide:
* **Team Tasks**
  * Established the overall structure and format of the User Guide to ensure consistency and readability
  * Created standardized command format sections with clear syntax highlighting


* **Individual Tasks**
  * Added documentation for the Summary Display feature that shows financial information, [#48](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)
  * Added documentation for the Help Display feature that lists all available commands
  * Added documentation for the Low Funds Alert system that warns users when funds are low
  * Added comprehensive error handling documentation for all financial operations

### Developer Guide:
* **Team Tasks**
    * Established the overall structure and format of the User Guide to ensure consistency and readability
    * Created standardized command format sections with clear syntax highlighting


* **Individual Tasks**
  * Created UML sequence diagrams to illustrate component interactions:
    * `Summary.puml`: Shows how the Summary class is connected to the rest of the program, [#48](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)
    * `ViewSummary.puml`: Shows how financial data is retrieved and displayed
    * `SetAlert.puml`: Illustrates the alert threshold configuration process
    * `TriggerAlert.puml`: Demonstrates the Observer pattern in action when funds are low
  * Added implementation details of the Summary component including Observer pattern design
  * Added implementation details of the SummaryDisplay component with sequence flows
  * Added implementation details of the Funds Alert component with threshold management
  * Added explanation of the income removal validation to prevent negative funds
