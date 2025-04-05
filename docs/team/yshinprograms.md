# Yong Shin's Project Portfolio Page

## Overview
Budget Tracker is a command-line app that helps users manage their money. Users can track how much they earn, spend, and save using simple text commands. The app includes a money summary feature and alerts users when their funds get too low.

## Summary of Contributions

_Note: Links to the respective PRs or commits have been embedded within the relevant text in the PPP_
### [Code Contributed (Reposense Link)](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=yshinprograms&breakdown=true)

### Features I Built

#### [Summary System (`summary` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7)
*   **Functionality**:
    *   `Summary.java`, is responsible for tracking the user's `totalIncome`, `totalExpense`, and `totalSavings`. It calculates the `availableFunds` (simply income minus expenses) to show current spending power. Methods are provided to add or remove financial records (income, expenses, savings) safely, incorporating checks to ensure data consistency and prevent invalid operations like creating a negative balance.
*   **Key Design Aspects/Features**:
    *   [**Observer Pattern (Subject)**](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34): Designed as the subject, notifying registered observers (`FinancialObserver`) of financial changes via `notifyObservers()`.
    *   **Reliability**: Ensured internal consistency through defensive programming, utilizing `BudgetTrackerException` for invalid operations and `assert` statements to verify invariants (e.g., non-negative totals).

#### [Summary Display (`summary.ui` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7)
*   **Functionality**:
    *   `SummaryDisplay.java` obtains the current financial data from the `Summary` object. It then formats key figures – Total Income, Total Expenses, Available Balance, and Total Savings – into a well-aligned table using `String.format()`. This formatted summary is then printed directly to the console for the user.
*   **Key Design Aspects/Features**:
    *   **Separation of Concerns**: Decouples the user interface logic from financial data management in `Summary`.

#### [Alert System (`alerts`, `commands` packages)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34)
*   **Functionality**:
    *   `FinancialObserver.java`: An interface specifying how components can react to financial updates.
    *   `FundsAlert.java`: Implements `FinancialObserver`, acting as an observer that watches the user's finances. It maintains a `warningThreshold` (defaulting to $5.00). When `Summary` updates its data, it notifies `FundsAlert`, which then checks if `availableFunds` are below the threshold, displaying a UI warning if necessary. It also presents an initial notification (`displayInitialNotification`) on startup.
    *   `AlertParser.java`: Processes the raw user input string for commands like `alert set <AMOUNT>`.
    *   `AlertCommand.java`: Executes the logic to update the warning threshold within the `FundsAlert` instance based on the parsed command details.
*   **Key Design Aspects/Features**:
    *   [**Observer Pattern**](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34): Uses the Observer pattern (`FundsAlert` as observer, `Summary` as subject) for loose coupling between financial tracking and alerting.

#### [User Help System (`ui` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7)
*   **What it is**: Provides users with clear, categorized instructions on how to use the application's various commands.
*   **Detailed Functionality**:
    *   `HelpDisplay.java` displays user assistance. It lists all commands available in the application, including syntax (e.g., `add income <AMOUNT> / <SOURCE>`) and brief explanations.
*   **Key Design Aspects/Features**:
    *   **Maintainability**: Easy to update as commands are added or modified.

### JUnit Tests
*   [**`SummaryTest.java`**: Comprehensive tests](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/51) covering initialization, balance calculations, adding/removing income and expenses (including edge cases like zero/negative values, insufficient funds, operations leading to negative balances), and savings operations.
*   **`SummaryDisplayTest.java`**: Tests verifying the correct formatting of the budget summary output for both populated and empty `Summary` objects.
*   **`AlertParserTest.java`**: Tests ensuring correct parsing of valid `alert set` commands and proper handling (exception throwing) for invalid formats (missing arguments, non-numeric input, unrecognized subcommands).
*   **`FundsAlertTest.java`**: Tests covering management of the warning threshold (default value, setting valid/invalid values), alert triggering logic (only when funds are strictly below the threshold), and initial notification messages.
*   **`HelpDisplayTest.java`**: Tests validating the content of the generated help text (presence of headers, essential commands) and confirming that the `display` method prints the output correctly.

### [Assertion & Logging](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/144)
*   **Logging Configuration (`util.LoggingConfigurator`)**: Implemented a dedicated utility class to manage logging for specific components.
    *   Configured `java.util.logging` to redirect logs from the `Summary` class and the `alerts` package to separate files (`logs/summary.log` and `logs/alerts.log` respectively).
    *   Utilized `FileHandler` to write logs and ensured the `logs` directory is created if it doesn't exist.
    *   Used `logger.setUseParentHandlers(false)` to prevent these specific logs from appearing in the console output, keeping the CLI clean for user interaction.
*   **Assertions**: Incorporated `assert` statements within the `Summary` and `FundsAlert` classes to enforce crucial internal invariants during development and testing.

### Project Management
* **Project Setup**:
  * Created and configured the GitHub organization and repository for the team
  * Established branch protection rules to enforce the forking workflow
  * Setup & configured Gradle for team use
  * Created and standardized all priority, severity, and type issue tags/labels for better project tracking


* **Release Management**:
  * Setup milestones & deadlines for v1.0, v2.0 & v2.1
  * Managed releases v1.0 and v2.0 on GitHub, which includes:
    * Conversion of UG and DG into PDF versions
    * Tagging the correct commit for release
    * Building the submission JAR files


* **Community Contributions**:
  * Reviewed and approved Pull Requests with non-trivial review comments: [PR#24](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/24), [PR#35](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/35), [PR#45](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/45), [PR#58](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/58#discussion_r2020096560)
  * Added, assigned and closed Github Issues in line with project tasks [Issue #6](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/6), [Issue #33](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/33), [Issue #133](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/133)

<div style="page-break-after: always;"></div>

### Documentation

#### User Guide:
* **Team Tasks**
  * Established the overall structure and format of the User Guide using standardized command format sections to ensure consistency and readability
  * [Created Introduction and Quick Start Guide](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/7e9f0004dbca88ec879243c52117a181f814ada7)
  * [Consolidated and listed down Command Summary](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/6c5210fddfa32decc9f495233c0ccfafee5f468b)
  * [Detailed "Features coming soon", which includes "Local Saving to Disk" with a brief explanation](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/ca9f7717c71feabd38a9b0e6c24e72b02b02c42a)
  * [Provided a brief overview of possible error messages](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/7e9f0004dbca88ec879243c52117a181f814ada7)


* **Individual Tasks**
  * Added documentation for the Summary Display feature that shows financial information, [#48](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)
  * Added documentation for the Help Display feature that lists all available commands
  * Added documentation for the Low Funds Alert system that warns users when funds are low
  * Added comprehensive error handling documentation for all financial operations

<div style="page-break-after: always;"></div>

#### Developer Guide:
* **Team Tasks**
    * Established the overall structure and format of the User Guide to ensure consistency and readability
    * [Contributed to Acknowledgements](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/2fcbdf513d5574d08fb9604f9f2555090240a1f3)
    * [Contributed to User Stories](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/1d6cddd7f696f66cd32b2f11ea57a77946403868)
    * [Detailed the non-functional requirements of "Compatibility", "Performance", and "Usability"](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/283a66cb6fee5c17acd82c6326181e961252df3c)
    * [Developed the Glossary of technical terms](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/283a66cb6fee5c17acd82c6326181e961252df3c)
    * [Contributed to "Help", "Summary", and "Alert" sections of the manual testing instructions](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/83c3d145afd1391b81eb26e66800782153fc74f1)


* **[Individual Tasks](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)**
  * Created UML sequence diagrams to illustrate component interactions:
    * `Summary.puml`: Shows how the Summary class is connected to the rest of the program
    * `ViewSummary.puml`: Shows how financial data is retrieved and displayed
    * `SetAlert.puml`: Illustrates the alert threshold configuration process
    * `TriggerAlert.puml`: Demonstrates the Observer pattern in action when funds are low
  * Added implementation details of the Summary component including Observer pattern design
  * Added implementation details of the SummaryDisplay component with sequence flows
  * Added implementation details of the Funds Alert component with threshold management
