# Yong Shin's Project Portfolio Page

## Overview
Budget Tracker is a command-line app that helps users manage their money. Users can track how much they earn, spend, and save using simple text commands.

## Summary of Contributions

#### [Code Contributed (Reposense Link)](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=yshinprograms&breakdown=true)

### Features I Built

#### [Summary System (`summary` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7)
*   **Functionality**:
    *   `Summary.java`, is responsible for tracking the user's `totalIncome`, `totalExpense`, and `totalSavings`. It dynamically calculates `availableFunds` (income minus expenses) with methods provided to add or remove income, expenses, and savings safely.
*   **Key Design Aspects/Features**:
    *   **Observer Pattern (Subject)**: Designed as the subject, notifies observers (`FinancialObserver`) of financial changes via `notifyObservers()`. [PR#34](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34)
    *   **Reliability**: Ensured internal consistency through defensive programming, utilizing `BudgetTrackerException` for invalid operations and `assert` statements to verify invariants.

#### [Summary Display (`summary.ui` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)
*   **Functionality**:
    *   `SummaryDisplay.java` obtains `totalIncome`, `totalBalance`, `totalExpense`, and `totalSavings` from `Summary` and formats them into an easy-to-read table.

#### [Alert System (`alerts`, `commands` packages)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34)
*   **Functionality**: Implements an alert system using the Observer pattern. `FundsAlert` (Observer) monitors `Summary` (Subject) and triggers a warning if available funds fall below a user-configurable `warningThreshold` (default $5.00). Includes `AlertParser` for command input and `AlertCommand` for updating the threshold.
*   **Key Design Aspects/Features**:
    *   **Observer Pattern (Observer)**: Uses the Observer pattern (`FundsAlert` as observer, `Summary` as subject) for loose coupling between financial tracking and alerting.

#### [User Help System (`ui` package)](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7)
*   **Functionality**:
    *   `HelpDisplay.java` lists all commands available in the application, including syntax (e.g., `add income <AMOUNT> / <SOURCE>`) and explanations.

### JUnit Tests
* Created comprehensive tests for `Summary`, `SummaryDisplay`, `AlertParser`, `FundsAlert`, and `HelpDisplay` components, covering core functionality, edge cases, formatting and parsing logic.[PR#34](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34),[PR#51](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/51),[PR#140](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/140),[PR#144](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/144)

### [Assertion & Logging](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/144)
*   **Logging Configuration (`util.LoggingConfigurator`)**: Redirects `Summary` and `alerts` logs to separate files, ensuring the `logs` directory exists and preventing console output.
*   **Assertions**: Incorporated `assert` statements within the `Summary` and `FundsAlert` classes to enforce crucial internal invariants during development and testing.[PR#27](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/27),[PR#144](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/144)

### Project Management
* **Project Setup**:
  * Created and configured the GitHub organization and repository for the team
  * Established branch protection rules to enforce the forking workflow
  * Setup & configured Gradle for team use
  * Created and standardized all priority, severity, and type issue tags/labels for project tracking


* **Release Management**:
  * Setup milestones & deadlines for v1.0, v2.0 & v2.1
  * Managed releases v1.0, v2.0 & v2.1 on GitHub, which includes:
    * Conversion of UG and DG into PDF versions
    * Tagging the correct commit for release & building the submission JAR files


* **Community Contributions**:
  * Reviewed Pull Requests with non-trivial review comments: [PR#24](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/24), [PR#35](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/35), [PR#45](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/45), [PR#58](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/58#discussion_r2020096560)
  * Assigned and closed Github Issues in line with project tasks [Issue #6](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/6), [Issue #33](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/33), [Issue #133](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/133)

<div style="page-break-after: always;"></div>

### User Guide:
* **Team Tasks**
  * Established the overall structure and format of the User Guide using standardized command format sections to ensure consistency and readability
  * Created Introduction and Quick Start Guide [Commit: 7e9f000](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/7e9f0004dbca88ec879243c52117a181f814ada7)
  * Consolidated and listed down Command Summary [Commit: 6c5210f](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/6c5210fddfa32decc9f495233c0ccfafee5f468b)
  * Detailed "Features coming soon", which includes "Local Saving to Disk" with a brief explanation [Commit: ca9f771](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/ca9f7717c71feabd38a9b0e6c24e72b02b02c42a)
  * Provided a brief overview of possible error messages [Commit: 7e9f000](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/7e9f0004dbca88ec879243c52117a181f814ada7)


* **Individual Tasks**
  * Added documentation for the Summary Display feature that shows financial information, [#48](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/48)
  * Added documentation for the Help Display feature that lists all available commands
  * Added documentation for the Low Funds Alert system that warns users when funds are low
  * Added comprehensive error handling documentation for all financial operations

<div style="page-break-after: always;"></div>

### Developer Guide:
* **Team Tasks**
    * Established the overall structure and format of the User Guide to ensure consistency and readability
    * Contributed to Acknowledgements [Commit: 2fcbdf5](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/2fcbdf513d5574d08fb9604f9f2555090240a1f3)
    * Contributed to User Stories [Commit: 1d6cddd](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/1d6cddd7f696f66cd32b2f11ea57a77946403868)
    * Detailed the non-functional requirements of "Compatibility", "Performance", and "Usability" [Commit: 283a66c](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/283a66cb6fee5c17acd82c6326181e961252df3c)
    * Developed the Glossary of technical terms [Commit: 283a66c](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/283a66cb6fee5c17acd82c6326181e961252df3c)
    * Contributed to "Help", "Summary", and "Alert" sections of the manual testing instructions [Commit: 83c3d14](https://github.com/AY2425S2-CS2113-T11A-4/tp/commit/83c3d145afd1391b81eb26e66800782153fc74f1)


* **Individual Tasks**
  * Created UML sequence diagrams to illustrate component interactions:
    * `Summary.puml`: Shows how the Summary class is connected to the rest of the program
    * `ViewSummary.puml`: Shows how financial data is retrieved and displayed
    * `SetAlert.puml`: Illustrates the alert threshold configuration process
    * `TriggerAlert.puml`: Demonstrates the Observer pattern in action when funds are low
  * Added implementation details of the Summary component including Observer pattern design
  * Added implementation details of the SummaryDisplay component with sequence flows
  * Added implementation details of the Funds Alert component with threshold management
