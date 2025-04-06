# Keng Jer's Project Portfolio Page

## Overview
Budget Tracker is a command-line app that helps users manage their money. Users can track how much they earn, spend, and save using simple text commands. Users are able to add expenses with categorisation.

## Summary of Contributions

### [Code Contributed (Reposense Link)](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=teokj&breakdown=true)

### Features I Built

#### Income
I was responsible for implementing the income management system, which allows users to add, delete and view their 
incomes and sources.

* **Income Handling**:
    * Designed and implemented the `Income` and `IncomeParser` class, which serves as the foundation for income 
  tracking.
    * Created the `IncomeManager` class to store and manage user incomes and sources.

* **Commands for Income Management**:
  Implemented the following command classes
    * `AddIncomeCommand`: allows users to add income entries, specifying amount and source.
    * `DeleteIncomeCommand`: enables users to remove an income entry from their list.
    * `ListIncomeCommand`: displays all recorded income entries in a structured format.

* **Income Parsing and Input Handling**:
    * Created the `IncomeParser` class to efficiently parse user input for adding income entries.
    * Implemented error handling for invalid income entries, ensuring that:
        * Users cannot add negative income values
        * Users cannot delete an income entry with an invalid index
        * The system displays helpful error messages for invalid input scenarios.

* **Income Display Enhancements**:
    * Designed a user-friendly format to display income records, improving readability and user experience.

### Savings Indicator
I implemented a feature that provides a savings indicator based on a user's total savings relative to their income. 
This feature helps users assess their financial habits and encourages better saving practices.

**Implementation Details**: 
* Developed the getSavingsIndicator() method to analyze savings as a percentage of income.
* Categorizes savings levels into "Good," "Neutral," or "Bad" based on predefined thresholds:
  * "Good": Savings are 80% or more of total income.
  * "Neutral": Savings are between 50% and 80% of income.
  * "Bad": Savings are less than 50% of total income.

### JUnit Tests
* Wrote tests for the following classes ([#25](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/25))
    * `AddIncomeCommand` tests for adding income with various sources
    * `ViewIncomeCommand` tests covering edge cases and different formats of income records
    * `DeleteIncomeCommand` tests for successful and erroneous income deletions
    * `Income` tests to cover basic cases
    * `IncomeManager` tests to cover different commands with various income lists
    * `IncomeParser` tests to cover formatting of user inputs

### Github Practices
* **Community Contributions**:
    * Approved code changes from teammates ([#16](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/16), [#39](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/39))
    * Added Github Issues and closed them respectively ([#8](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/8),
[#9](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/9), [#10](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/10),
[#11](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/11), [#12](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/12),
[#41](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/41), [#42](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/42),
[#44](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/44))

### Documentation
* Wrote extensive JavaDoc comments for most of the classes in the project, particularly the Income classes, 
improving code maintainability and clarity.
* Updated documentation for new features and functionalities implemented, ensuring users have easy-to-follow instructions.

### User Guide:
* Added documentation for the Add Income feature, helping users add income with clear instructions on specifying sources and amounts.
* Added documentation for the View Income feature, describing how users can view their income records with clear formatting.
* Documented the Delete Income feature, explaining how users can delete income entries from the list.

### Developer Guide:
* Created UML class diagram `Income.puml` to illustrate the interactions between income-related classes
* Created UML sequence diagrams to illustrate component interactions:
    * `AddIncome.puml`: Shows how income is added through the user interface
    * `ViewIncome.puml`: Demonstrates the flow for displaying income records to the user
    * `DeleteIncome.puml`: Shows how income is deleted through the user interface
* Added implementation details of the Add Income component
* Added implementation details of the View Income component
* Added implementation details of the Delete Income component