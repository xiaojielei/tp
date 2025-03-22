package alerts;

import expenses.Ui;

/**
 * Alerts when funds fall below a warning threshold.
 * This class observes financial data without being tightly coupled to Summary.
 */
public class FundsAlert implements FinancialObserver {
    private double warningThreshold;
    private final Ui ui;
    
    /**
     * Creates a new FundsAlert with the default threshold of $10.
     * 
     * @param ui UI handler for displaying alerts
     */
    public FundsAlert(Ui ui) {
        this.warningThreshold = 10.0; // Default warning threshold is $10
        this.ui = ui;
    }
    
    /**
     * Creates a new FundsAlert with a specified threshold.
     * 
     * @param warningThreshold Threshold for warning alerts
     * @param ui UI handler for displaying alerts
     */
    public FundsAlert(double warningThreshold, Ui ui) {
        if (warningThreshold < 0) {
            throw new IllegalArgumentException("Warning threshold cannot be negative");
        }
        this.warningThreshold = warningThreshold;
        this.ui = ui;
    }
    
    /**
     * Changes the warning threshold.
     * 
     * @param newThreshold The new warning threshold
     * @return true if the threshold was changed successfully
     * @throws IllegalArgumentException if the threshold is negative
     */
    public boolean setWarningThreshold(double newThreshold) {
        if (newThreshold < 0) {
            throw new IllegalArgumentException("Warning threshold cannot be negative");
        }
        this.warningThreshold = newThreshold;
        return true;
    }
    
    /**
     * Gets the current warning threshold.
     * 
     * @return The current warning threshold
     */
    public double getWarningThreshold() {
        return warningThreshold;
    }
    
    @Override
    public void update(double availableFunds, double totalIncome, double totalExpense, double totalSavings) {
        checkAndDisplayAlert(availableFunds);
    }
    
    /**
     * Checks funds against threshold and displays alert if needed.
     * 
     * @param availableFunds Current available funds
     */
    private void checkAndDisplayAlert(double availableFunds) {
        if (availableFunds <= warningThreshold) {
            String message = "WARNING: Available funds ($" + String.format("%.2f", availableFunds) + 
                    ") are below warning threshold ($" + String.format("%.2f", warningThreshold) + ")";
            ui.showAlert(message);
        }
    }
    
    /**
     * Displays the initial notification about the funds alert feature.
     */
    public void displayInitialNotification() {
        String message = "Funds Alert feature is active. You will be warned when available funds fall below $" 
                + String.format("%.2f", warningThreshold) + ".";
        ui.showMessage(message);
        ui.showMessage("Use 'alert set <amount>' to change this threshold.");
    }
}
