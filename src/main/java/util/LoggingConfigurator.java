package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import summary.Summary;

/**
 * Utility class for configuring logging settings for the application during development.
 */
public class LoggingConfigurator {
    private LoggingConfigurator() {}

    /**
     * Configures logging to redirect logs specifically from the Summary class to logs/summary.log
     * It prevents these logs from appearing in the console via parent handlers.
     */
    public static void configureSummaryFileLogging() {
        Logger summaryLogger = Logger.getLogger(Summary.class.getName());
        summaryLogger.setUseParentHandlers(false);

        try {
            String logFilePath = "logs/summary.log";
            Path logPath = Paths.get(logFilePath);

            Path parentDir = logPath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());

            summaryLogger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Error setting up summary log file handler: " + e.getMessage());
        }
    }

    /**
     * Configures logging to redirect logs specifically from the alerts package 
     * to a file named alerts.log within the logs directory.
     * It prevents these logs from appearing in the console via parent handlers.
     */
    public static void configureAlertsFileLogging() {
        Logger alertsLogger = Logger.getLogger("alerts"); 
        alertsLogger.setUseParentHandlers(false);

        try {
            String logFilePath = "logs/alerts.log";
            Path logPath = Paths.get(logFilePath);
            Path parentDir = logPath.getParent();
            
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());

            alertsLogger.addHandler(fileHandler);

        } catch (IOException e) {
            System.err.println("Error setting up alerts log file handler: " + e.getMessage());
        }
    }
}
