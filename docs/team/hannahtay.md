# Hannah's Project Portfolio Page

## Overview
Budget Tracker is a command-line app that helps users manage their money. Users can track how much they earn, spend, and save using simple text commands. Users are able to add expenses with categorisation.

## Summary of Contributions

### [Code Contributed (Reposense Link)](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=hannahtay&breakdown=true)

### Enhancements implemented (Features I Built)

#### Expenses
I was responsible for implementing the expense management system, which allows users to add, delete and view their expenses.

* **Expense Handing**: 
  * Designed and implemented the `Expense` and `ExpenseParser` class, which serves as the foundation for expense tracking.
  * Created the `ExpenseList` class to store and manage user expenses.
  * Created `Ui` class which displays system messages to the user

* **Commands for Expense Management**: 
Implemented the following command classes
  * `AddExpenseCommand`: allows users to add expenses, specifying amount, description and category.
  * `DeleteExpenseCommand`: enables users to remove an expense from their list.
  * `ViewExpenseCommand`: displays all recorded expenses in a structured and easy-to-read format.

* **Expense Parsing and Input Handling**:
  * Created the `ExpenseParser` class to process user inputs efficiently.
  * Implemented error handling for invalid inputs, ensuring that:
    * Users cannot add negative or zero expenses
    * Users cannot add an expense that exceeds their available funds
    * Users cannot delete an expense with an invalid list number

* **Expense Categories**:
  * Created predefined categories user can use to better keep track of their expenses.

* **Expense Display Enhancements**:
  * Designed the output format for expenses to ensure readability and user-friendly display.

### JUnit Tests
* Wrote tests for the following classes ([#49](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/49), [#30](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/30),[#147](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/147)):
  * `AddExpenseCommand` tests testing adding expenses with different category combinations, and most possible scenarios
  * `ViewExpenseCommand` tests testing most possible scenarios
  * `DeleteExpenseCommand` tests testing most possible scenarios

### Contributions to team-based tasks

#### Github Community Contributions
* Added Github Issues and closed them respectively ([#14](https://github.com/AY2425S2-CS2113-T11A-4/tp/issues/14))

#### Updating Developer Docs
* Added instructions for manual testing under Expenses and Start up, [#85](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/85)
* Added Product Scope and hyperlinked content page, [#143](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/143)

### Documentation
* Wrote JavaDoc comments for almost all the Expenses code, [#38](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/38)
* Added logging and assertions in code, [#62](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/62)

### User Guide:
* Added documentation for the Add Expense feature that allows users to add expenses with categorisation.
* Added documentation for the View Expense feature that lists all input expenses.
* Added documentation for the Delete Expense feature that deletes user-specified expense.

### Developer Guide:
* Created UML sequence diagrams to illustrate component interactions:
  * `AddExpense.puml`: Shows how expenses are added by the user, [#45](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/45)
  * `ViewExpense.puml`: Shows how the expenses are retrieved and displayed
  * `Expense.puml`: Shows how the expenses are managed, [#143](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/143)
* Added implementation details of the Add Expense component 
* Added implementation details of the View Expense component
* Added implementation details of the Delete Expense component

### Review/mentoring Contributions
* Approved code changes from teammates, ([#7](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/7), [#34](https://github.com/AY2425S2-CS2113-T11A-4/tp/pull/34))
