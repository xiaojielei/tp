package expenses;

public class Ui {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void showList(String message) {
        System.out.println("===== EXPENSE ENTRIES =====");
        System.out.println(message);
        System.out.println("============================");
    }
    /**
     * Displays an alert message with emphasis.
     * 
     * @param alertMessage The alert message to display
     */
    public void showAlert(String alertMessage) {
        System.out.println("\n====== ALERT ======");
        System.out.println(alertMessage);
        System.out.println("===================\n");
    }
}
