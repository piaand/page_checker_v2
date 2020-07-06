package com.piaandersin.pagecheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class sets up the LogConfiguration and creates the handlers
 * for PageCheck logger.
 *
 * In any errors when reading log file or log configurations, systems exits.
 */

@Data @NoArgsConstructor @AllArgsConstructor
public class LogConfiguration {
 
    private static final LogManager LOGMANAGER = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());
    
    static{
        try {
            LOGMANAGER.readConfiguration(new FileInputStream("./src/main/resources/logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
            System.exit(1);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Unexpected error in loading configuration",exception);
            System.exit(1);
        }
    }
    
    private String logFileName = "./pagecheck.log";
    
    public void setLogHandlers() {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler  = new FileHandler(logFileName);
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
            LOGGER.log(Level.INFO, "Read the logger configurations");
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in creating the log file",exception);
            System.exit(1);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Unexpected error in creating the log file",exception);
            System.exit(1);
        }
    }
}
