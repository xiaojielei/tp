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

### View Summary: `view summary`

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
add expense <AMOUNT> / <SOURCE> / <CATEGORY>                 Adds an expense record.
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

### Low Funds Alert System

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

### Adding Income: `add income`

Adds a new income entry to your financial record.

Format: `add income <AMOUNT> / <DESCRIPTION>`

* `<AMOUNT>` must be a positive number.
* `<DESCRIPTION>` is a description of where the income came from.

Example of usage:

```
> add income 50 / salary
Added income: $50.00 from salary
```

### Adding Expense: `add expense`

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

### Adding to Savings: `add savings`

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

### Viewing Income: `view income`

Lists all income entries that have been added.

Format: `view income`

Example of usage:

```
> view income
===== INCOME ENTRIES =====
1. $50.00 from salary
2. $100.00 from freelance
===========================
```

### Viewing Expenses: `view expense`

Lists all expense entries that have been added.

Format: `view expense`

Example of usage:

```
> view expense
===== EXPENSE ENTRIES =====
1. [FOOD] $25.00 for food
2. [BILLS] $50.00 for utilities
===========================
```

### Viewing Savings: `view savings`

Lists all savings entries that have been added.

Format: `view savings`

Example of usage:

```
> view savings
===== SAVINGS ENTRIES =====
1. $20.00 for emergency fund
2. $10.00 for vacation
============================
```

### Deleting savings: delete a saving entry

Deletes a savings record by index.

Format: `delete savings <INDEX>`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> delete savings 1
Sure! I have deleted the saving:
1. [ ] 100.00
   Now you have 0 savings in your list.
```

### Setting savings goal: savings goal set

Sets a savings goal with amount and description.

Format: `savings goal set <AMOUNT> / <DESCRIPTION>`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> savings goal set 100.00/buy food
I have set your saving goal:
1. [buy food] 100.00
```

### Viewing savings goals: savings goal view
Displays all current savings goals.

Format: `savings goal view`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> savings goal view
1. [buy food] 100.00
```

### Updating savings goal: update a savings goal 

Updates an existing savings goal by index with new amount and description.

Format: `savings goal update <INDEX> <AMOUNT> / <DESCRIPTION>`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> savings goal update 1 100.00/donate
I have updated your saving amount and saving goal:
1. [donate] 100.00
```

### Deleting savings goal: delete a savings goal

Deletes a savings goal by index.

Format: `savings goal delete <INDEX>`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> savings goal set 100.00/buy food
I have set your saving goal:
1. [buy food] 100.00

> savings goal delete 1
I have deleted the saving goal:
1. [ ] 100.00
```

### Transferring savings: transfer savings amount

Transfers certain amount of one saving record to another

Format: `transfer savings <INDEX1> <INDEX2> <AMOUNT>`

Example of usage:

```
> add savings 100.00
Sure! I have added your savings:
1. [ ] 100.00
Now you have 1 saving in your list.

> add savings 200.00
Sure! I have added your savings:
1. [ ] 200.00
Now you have 2 saving in your list.

> transfer savings 1 2 50.0
Transferred 50.0 from savings 1 to savings 2.
Updated records:
1. [ ] 50.0
2. [ ] 250.0
```

### Exit the Application: `bye`

Exits the Common Cents application.

Format: `bye`

## Error Handling

Common Cents provides informative error messages for invalid commands:

* **Invalid command format:**
  * If you enter a command with incorrect formatting, Common Cents will explain the proper format.
  * Example: `add income 50` → `Invalid format for 'add income' command. Please use 'add income <AMOUNT> / <DESCRIPTION>'`

* **Negative amount:**
  * Financial amounts must be positive.
  * Example: `add income -50 / salary` → `Income amount must be a positive number.`

* **Invalid alert threshold:**
  * Alert thresholds must be non-negative.
  * Example: `alert set -10` → `Warning threshold cannot be negative`

* **Insufficient funds for expense or savings:**
  * You cannot add an expense or move to savings more than your available funds.
  * Example: `add expense 200 / shopping` (when available funds are $100) → `Cannot add this expense as it would exceed your available funds. Available balance: $100.00`

## Command Summary

* View financial summary: `view summary`
* View help information: `help`
* Set alert threshold: `alert set <AMOUNT>`
* Add income: `add income <AMOUNT> / <DESCRIPTION>`
* Add expense: `add expense <AMOUNT> / <DESCRIPTION> / <CATEGORY>`
* Add to savings: `add savings <AMOUNT> / <DESCRIPTION>`
* Delete savings: `delete savings <INDEX>`
* Delete expense entry: `delete expense <INDEX>`
* Transfer savings: `transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>`
* View savings: `view savings`
* Set savings goal: `savings goal set <AMOUNT> / <DESCRIPTION>`
* View savings goal: `savings goal view`
* Update savings goal: `savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>`
* Delete savings goal: `savings goal delete <INDEX>`
* View income entries: `view income`
* View expense entries: `view expense`
* View savings entries: `view savings`
* Exit application: `bye`
