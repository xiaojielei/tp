# User Guide

## Introduction

Common Cents is a Command Line Interface (CLI) financial management application that helps you track your income, expenses, and savings. It provides a simple way to monitor your financial health and alerts you when your funds drop below a set threshold.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `Common Cents` from [here](https://github.com/AY2425S2-CS2113-T11A-4/tp).
3. Copy the file to the folder you want to use as the home folder for Common Cents.
4. Open a command terminal and navigate to the folder.
5. Run the command `java -jar commoncents.jar` to start the application.
6. Type `help` to view available commands.

## Features 

Common Cents offers several features to help you manage your finances effectively:

### Income Management

#### Adding Income: `add income`

Adds a new income entry to your financial record.

Format: `add income <AMOUNT> / <DESCRIPTION>`

* `<AMOUNT>` must be a positive number.
* `<DESCRIPTION>` is a description of where the income came from.

Example of usage:

```
> add income 50 / salary
Added income: $50.00 from salary
```

#### Viewing Income: `view income`

Lists all your income entries.

Format: `view income`

Example of usage:

```
> view income
===== INCOME RECORDS =====
1. 	$50.00 from salary
2. 	$100.00 from freelance
=========================
```

#### Deleting Income: `delete income`

Removes an income entry from your records.

Format: `delete income <INDEX>`

* `<INDEX>` refers to the index number shown in the income list.
* The index must be a positive integer (1, 2, 3, ...).

Example of usage:

```
> delete income 1
Deleted income: $50.00 from salary
```

### Expense Management

#### Adding Expense: `add expense`

Adds a new expense entry to your financial record.

Format: `add expense <AMOUNT> / <DESCRIPTION> / <CATEGORY>`

* `<AMOUNT>` must be a positive number.
* `<DESCRIPTION>` is a description of what the expense was for.
* `<CATEGORY>` is the category of the expense (F (Food), T (Transport), B (Bills), O (Others))

Example of usage:

```
> add expense 25 / food
Added expense: [FOOD] $25.00 for food
```

#### Viewing Expenses: `view expense`

Lists all your expense entries.

Format: `view expense`

Example of usage:

```
> view expense
===== EXPENSE ENTRIES =====
1. [FOOD] $25.00 for food
2. [BILLS] $50.00 for utilities
==========================
```

#### Deleting Expense: `delete expense`

Removes an expense entry from your records.

Format: `delete expense <INDEX>`

* `<INDEX>` refers to the index number shown in the expense list.
* The index must be a positive integer (1, 2, 3, ...).

Example of usage:

```
> delete expense 1
Deleted expense: [FOOD] $25.00 for food
```

### Savings Management

#### Adding to Savings: `add savings`

Moves money from your available balance to your savings.

Format: `add savings <AMOUNT> / <DESCRIPTION>`

* `<AMOUNT>` must be a positive number.
* `<DESCRIPTION>` is a description of the savings purpose.
* You cannot add to savings more than your available balance.

Example of usage:

```
> add savings 20 / emergency fund
Added to savings: $20.00 for emergency fund
```

#### Viewing Savings: `view savings`

Lists all your savings entries.

Format: `view savings`

Example of usage:

```
> view savings
===== SAVINGS RECORDS =====
1. 	$20.00 for emergency fund
2. 	$10.00 for vacation
==========================
```

#### Deleting Savings: `delete savings`

Removes a savings entry and returns the amount to your available balance.

Format: `delete savings <INDEX>`

* `<INDEX>` refers to the index number shown in the savings list.
* The index must be a positive integer (1, 2, 3, ...).

Example of usage:

```
> delete savings 1
Deleted savings: $20.00 for emergency fund
```

#### Transferring Savings: `transfer savings`

Transfers a specified amount from one savings entry to another.

Format: `transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>`

* `<FROM_INDEX>` and `<TO_INDEX>` refer to the index numbers shown in the savings list.
* `<AMOUNT>` is the amount to transfer, which must be a positive number not exceeding the amount in the source savings entry.

Example of usage:

```
> transfer savings 1 2 10
Transferred $10.00 from emergency fund to vacation
```

#### Setting Savings Goals: `savings goal set`

Sets a new savings goal.

Format: `savings goal set <AMOUNT> / <DESCRIPTION>`

* `<AMOUNT>` is the target amount for your savings goal.
* `<DESCRIPTION>` is a description of what you're saving for.

Example of usage:

```
> savings goal set 500 / new laptop
Savings goal set: $500.00 for new laptop
```

#### Viewing Savings Goals: `savings goal view`

Lists all your savings goals.

Format: `savings goal view`

Example of usage:

```
> savings goal view
===== SAVINGS GOALS =====
1. 	$500.00 for new laptop (Current: $0.00)
=========================
```

#### Updating Savings Goals: `savings goal update`

Updates an existing savings goal.

Format: `savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>`

* `<INDEX>` refers to the index number shown in the savings goals list.
* `<AMOUNT>` is the new target amount.
* `<DESCRIPTION>` is the new description.

Example of usage:

```
> savings goal update 1 / 600 / gaming laptop
Updated savings goal: $600.00 for gaming laptop
```

#### Deleting Savings Goals: `savings goal delete`

Removes a savings goal.

Format: `savings goal delete <INDEX>`

* `<INDEX>` refers to the index number shown in the savings goals list.

Example of usage:

```
> savings goal delete 1
Deleted savings goal: $600.00 for gaming laptop
```

#### Exiting Savings Mode: `exit savings`

Exits the savings management mode.

Format: `exit savings`

Example of usage:

```
> exit savings
Exited savings management
```

### Summary Management

#### View Summary: `view summary`

Displays a summary of your current financial status, including total income, expenses, available balance, and savings.

Format: `view summary`

Example of usage: 

```
> view summary
===== BUDGET SUMMARY =====
Total Income:         $150.00
Total Expenses:       $75.00
Available Balance:    $75.00
Total Savings:        $30.00
===========================
```

#### Detailed Financial Tracking

The Summary component maintains your financial data using these calculations:
- **Available Funds** = Total Income - (Total Expenses + Total Savings)
- All calculations are performed automatically when you add or remove entries

#### Summary Display Format

When you view your summary, you'll see information organized like this:
```
Total Income: $1000.00
Total Expenses: $400.00
Total Savings: $200.00
Available Funds: $400.00
```

#### Data Validation

The Summary component includes built-in validation to ensure financial data integrity:
- All amounts (income, expenses, savings) must be positive numbers
- You cannot remove more income than your current total
- You cannot add expenses that would result in negative available funds
- You cannot remove more expenses or savings than currently recorded

#### Financial Calculations

The Summary component automatically:
- Recalculates available funds after every transaction
- Updates all observers (like the alert system) when financial data changes
- Maintains running totals for all financial categories
- Ensures data consistency across the application

### Alert System

#### Low Funds Alert System

Common Cents includes a low funds alert feature that warns you when your available funds fall below a set threshold.

#### Set Alert Threshold: `alert set`

Sets the threshold for low funds alerts. When your available funds drop below this amount, Common Cents will display a warning.

Format: `alert set <AMOUNT>`

* `<AMOUNT>` must be a positive number.
* The default threshold is $5.00 if not explicitly set.

Example of usage:

```
> alert set 20
Funds alert threshold set to $20.00
```

#### Alert Trigger

When your available funds drop below the set threshold due to adding expenses, Common Cents will automatically display a warning:

```
> add expense 40 / groceries

====== ALERT ======
WARNING: Available funds ($10.00) are below warning threshold ($20.00)
===================

Added expense: $40.00 for groceries
```

#### Alert System Implementation

The alert system:
- Uses an observer pattern to monitor financial changes
- Has a default threshold of $5.00
- Can be customized to any positive amount
- Shows warnings immediately when funds drop below the threshold

#### Alert System Features

The FundsAlert component provides these additional features:
- **Initial Notification**: Displays information about the alert feature when you start the application
- **Real-time Monitoring**: Continuously checks your financial status after every transaction
- **Customizable Threshold**: Allows you to set a warning level that matches your financial comfort zone
- **Clear Visual Warnings**: Displays prominent alerts when your funds drop below the threshold

### Help Display: `help`

Shows a list of all available commands and their formats.

Format: `help`

Example of usage:

```
> help
===== Budget Tracker Help =====

--- Income Management ---
add income <AMOUNT> / <SOURCE>                               Adds an income record.
delete income <INDEX>                                        Deletes an income record by index.
view income                                                  Lists all income records.

--- Expense Management ---
add expense <AMOUNT> / <DESCRIPTION> / <CATEGORY>            Adds an expense record.
delete expense <INDEX>                                       Deletes an expense record by index.
view expense                                                 Lists all expense records.

--- Savings Management ---
add savings <AMOUNT>                                         Adds a savings record.
delete savings <INDEX>                                       Deletes a savings record by index.
transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>            Transfers certain amount of cash of one saving record to another.
view savings                                                 Lists all savings records.

--- Summary Management ---
view summary                                                 Lists all income, expense and saving records.

--- Savings Goals ---
savings goal set <AMOUNT> / <DESCRIPTION>                    Sets a new savings goal.
savings goal view                                            Views all current savings goals.
savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>       Updates an existing savings goal.
savings goal delete <INDEX>                                  Deletes a savings goal by index.
exit savings                                                 exited savings function

--- Funds Alerts ---
alert set <AMOUNT>                                           Sets the warning threshold for low available funds.

--- General Commands ---
help                                                         Displays this help message.
===============================
```

### Help Command Details

The help command organizes information into these categories:
1. Financial Management Commands
2. Alert System Commands
3. Viewing Commands
Each category shows relevant commands with their formats and examples.

#### Help Display Features

The HelpDisplay component:
- Organizes commands into logical categories for easier reference
- Formats command information consistently with clear descriptions
- Aligns text for better readability
- Provides a comprehensive overview of all available functionality
- Includes proper syntax examples for each command

### Exit: `exit`

Exits the application.

Format: `exit`

Example of usage:

```
> exit
Goodbye! Your financial data has been saved.
```

## Command Summary

* **Help**: `help`
* **View Summary**: `view summary`
* **Add Income**: `add income <AMOUNT> / <DESCRIPTION>`
* **View Income**: `view income`
* **Delete Income**: `delete income <INDEX>`
* **Add Expense**: `add expense <AMOUNT> / <DESCRIPTION> / <CATEGORY>`
* **View Expenses**: `view expense`
* **Delete Expense**: `delete expense <INDEX>`
* **Add Savings**: `add savings <AMOUNT> / <DESCRIPTION>`
* **View Savings**: `view savings`
* **Delete Savings**: `delete savings <INDEX>`
* **Transfer Savings**: `transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>`
* **Set Savings Goal**: `savings goal set <AMOUNT> / <DESCRIPTION>`
* **View Savings Goals**: `savings goal view`
* **Update Savings Goal**: `savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>`
* **Delete Savings Goal**: `savings goal delete <INDEX>`
* **Exit Savings Mode**: `exit savings`
* **Set Alert Threshold**: `alert set <AMOUNT>`
* **Exit Application**: `exit`

## Error Messages

Common Cents provides helpful error messages to guide you when something goes wrong:

* **Invalid command:**
  * If you enter an unrecognized command, Common Cents will suggest using the help command.
  * Example: `hello` → `Oops! I don't recognize that command. Type 'help' to see available commands.`

* **Invalid command format:**
  * If you enter a command with incorrect formatting, Common Cents will explain the proper format.
  * Example: `add income 50` → `Invalid format for 'add income' command. Please use 'add income <AMOUNT> / <DESCRIPTION>'`

* **Negative amount:**
  * If you try to add a negative amount, Common Cents will remind you that amounts must be positive.
  * Example: `add income -50 / salary` → `Amount must be positive.`

* **Insufficient funds:**
  * If you try to add to savings more than your available balance, Common Cents will warn you.
  * Example: `add savings 100 / vacation` when you only have $50 available → `Cannot add to savings more than available balance.`

* **Invalid index:**
  * If you try to delete an entry with an invalid index, Common Cents will inform you.
  * Example: `delete income 10` when you only have 2 income entries → `Invalid index. Please use an index from the list.`
