# Common Cents

Common Cents is a personal finance management application that helps you track your income, expenses, savings, and financial goals. The application provides a command-line interface for managing your finances with features for budget tracking, savings management, and financial alerts.

## Features

### Financial Management
- **Income Management**: Add, delete, and view income records
- **Expense Management**: Add, delete, and view expense records with categorization
- **Savings Management**: Add, delete, view, and set goals for savings
- **Summary View**: View a comprehensive summary of your financial status
- **Funds Alert System**: Get warnings when your available funds fall below a set threshold

### Income Management
- **Add Income**:
    - `add income <AMOUNT> / <SOURCE>`
    - Adds a new income record with the specified amount and source
    - Example: `add income 1000 / Salary`
    - Note: Amount must be a positive number
    - Example output:
      ```
      > add income 2000 / Monthly Salary
      Sure! I have added your income:
      1. 	$2000.0 from Monthly Salary
      Now you have 1 income(s) in your list.
      ```

- **Delete Income**:
    - `delete income <INDEX>`
    - Deletes an income record at the specified index
    - Example: `delete income 1`
    - Note: Index starts from 1

- **View Income**:
    - `view income`
    - Lists all income records
    - Example: `view income`

### Expense Management
- **Add Expense**:
    - `add expense <AMOUNT> / <DESCRIPTION> / <CATEGORY>`
    - Adds a new expense record with the specified amount, source, and category
    - Example: `add expense 50 / Lunch / F`
    - Note: Categories are F (Food), T (Transport), B (Bills), O (Others)
    - Example output:
      ```
      > add expense 50 / Lunch / F
      Sure! I have added your expense:
      1. 	[FOOD] $50.0 from Lunch
      Now you have 1 expense(s) in your list.
      ```

- **Delete Expense**:
    - `delete expense <INDEX>`
    - Deletes an expense record at the specified index
    - Example: `delete expense 2`
    - Note: Index starts from 1

- **View Expense**:
    - `view expense`
    - Lists all expense records
    - Example: `view expense`

### Savings Management
- **Add Savings**:
    - `add savings <AMOUNT>`
    - Adds a new savings record with the specified amount
    - Example: `add savings 200`
    - Note: Amount must be a positive number
    - Example output:
      ```
      > add savings 500
      Sure! I have added your savings:
      1. 	[ ] 500.0
      Now you have 1 saving(s) in your list.
      ```

- **Delete Savings**:
    - `delete savings <INDEX>`
    - Deletes a savings record at the specified index
    - Example: `delete savings 1`
    - Note: Index starts from 1

- **View Savings**:
    - `view savings`
    - Lists all savings records
    - Example: `view savings`
    - Example output:
      ```
      > view savings
      Here are the savings in your list:
      1. 	[Emergency Fund] 500.0
      You have 1 saving(s) in total.
      Savings Indicator: Good
      ```

- **Set Savings Goal**:
    - `savings goal set <AMOUNT> / <DESCRIPTION>`
    - Sets a new savings goal for a specific amount
    - Example: `savings goal set 1000 / Emergency Fund`
    - Example output:
      ```
      > savings goal set 500 / Emergency Fund
      I have set your saving goal:
      [Emergency Fund] 500.0
      ```

- **View Savings Goals**:
    - `savings goal view`
    - Views all current savings goals
    - Example: `savings goal view`

- **Update Savings Goal**:
    - `savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>`
    - Updates an existing savings goal
    - Example: `savings goal update 1 / 1500 / Vacation Fund`

- **Delete Savings Goal**:
    - `savings goal delete <INDEX>`
    - Deletes a savings goal by index
    - Example: `savings goal delete 2`

- **Transfer Savings**:
    - `transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>`
    - Transfers a specified amount from one savings record to another
    - Example: `transfer savings 1 2 100`

### Summary Management
- **View Summary**:
    - `view summary`
    - Lists all income, expense, and savings records
    - Example: `view summary`
    - Example output:
      ```
      > view summary
      ===== FINANCIAL SUMMARY =====
      Total Income: $2000.00
      Total Expenses: $50.00
      Available Funds: $1950.00
      Total Savings: $0.00
      ```

### Funds Alert System
- **Set Alert Threshold**:
    - `alert set <AMOUNT>`
    - Sets the warning threshold for low available funds
    - Example: `alert set 100`
    - Note: Default threshold is $5.00
    - Example output:
      ```
      > alert set 100
      Alert threshold set to $100.00
      
      > add expense 1900 / New Laptop / O
      Sure! I have added your expense:
      2. 	[OTHERS] $1900.0 from New Laptop
      Now you have 2 expense(s) in your list.
      
      WARNING: Available funds ($50.00) are below warning threshold ($100.00)
      ```

### General Commands
- **Help**:
    - `help`
    - Displays all available commands
    - Example: `help`

- **Exit**:
    - `bye`
    - Exits the application
    - Example: `bye`

## Error Handling

### Income Management Errors
- **Negative Income**:
    - Input: `add income -100 / Salary`
    - Error: "Income must be positive."
    - Validation: Checks if income amount is positive

- **Invalid Delete Index**:
    - Input: `delete income 999`
    - Error: "Invalid index."
    - Validation: Checks if index is within valid range

### Expense Management Errors
- **Negative Expense**:
    - Input: `add expense -50 / Lunch / F`
    - Error: "Expense must be positive."
    - Validation: Checks if expense amount is positive

- **Invalid Category**:
    - Input: `add expense 50 / Lunch / X`
    - Error: "Invalid category! Use: F (Food), T (Transport), B (Bills), O (Others)."
    - Validation: Checks if category is one of the valid options

- **Insufficient Funds**:
    - Input: `add expense 5000 / Shopping / O` (when available funds are less)
    - Error: "Cannot add this expense as it would exceed your available funds. Available balance: $X.XX"
    - Validation: Checks if expense exceeds available funds

- **Invalid Format**:
    - Input: `add expense 50 Lunch`
    - Error: "Invalid format! Use: add expense <AMOUNT> / <SOURCE> / <CATEGORY>"
    - Validation: Checks if command follows the correct format

### Savings Management Errors
- **Zero Income for Savings**:
    - Input: `add savings 100` (when total income is 0)
    - Error: "Income cannot be 0 if wish to add savings."
    - Validation: Checks if there is income before adding savings

- **Transfer to Same Record**:
    - Input: `transfer savings 1 1 100`
    - Error: "Cannot transfer to the same savings record."
    - Validation: Checks if source and destination indices are different

- **Insufficient Funds for Transfer**:
    - Input: `transfer savings 1 2 1000` (when source has less)
    - Error: "Insufficient funds in the source savings."
    - Validation: Checks if source has enough funds for transfer

### Funds Alert Errors
- **Negative Threshold**:
    - Input: `alert set -10`
    - Error: "Warning threshold cannot be negative"
    - Validation: Checks if threshold is non-negative

## Command Format

### Parameter Conventions
- `<AMOUNT>`: A positive decimal number (e.g., 100, 50.50)
- `<SOURCE>`: Text describing the source of income/expense
- `<CATEGORY>`: Single letter code for expense categories (F, T, B, O)
- `<INDEX>`: Integer representing the position in a list (starting from 1)
- `<DESCRIPTION>`: Text describing a savings goal

### Command Syntax Rules
- Parameters are separated by forward slashes with spaces (`/`)
- Commands are case-insensitive (e.g., `ADD INCOME` is the same as `add income`)
- Amounts should not include currency symbols

## Command Summary Table

| Command | Format | Description |
|---------|--------|-------------|
| **Income** | | |
| Add Income | `add income <AMOUNT> / <SOURCE>` | Adds a new income record |
| Delete Income | `delete income <INDEX>` | Deletes an income record |
| View Income | `view income` | Lists all income records |
| **Expenses** | | |
| Add Expense | `add expense <AMOUNT> / <SOURCE> / <CATEGORY>` | Adds a new expense record |
| Delete Expense | `delete expense <INDEX>` | Deletes an expense record |
| View Expense | `view expense` | Lists all expense records |
| **Savings** | | |
| Add Savings | `add savings <AMOUNT>` | Adds a new savings record |
| Delete Savings | `delete savings <INDEX>` | Deletes a savings record |
| View Savings | `view savings` | Lists all savings records |
| Set Goal | `savings goal set <AMOUNT> / <DESCRIPTION>` | Sets a new savings goal |
| View Goals | `savings goal view` | Views all savings goals |
| Update Goal | `savings goal update <INDEX> / <AMOUNT> / <DESCRIPTION>` | Updates a savings goal |
| Delete Goal | `savings goal delete <INDEX>` | Deletes a savings goal |
| Transfer | `transfer savings <FROM_INDEX> <TO_INDEX> <AMOUNT>` | Transfers between savings records |
| **Summary** | | |
| View Summary | `view summary` | Shows financial summary |
| **Alerts** | | |
| Set Alert | `alert set <AMOUNT>` | Sets low funds warning threshold |
| **General** | | |
| Help | `help` | Shows available commands |
| Exit | `bye` | Exits the application |
