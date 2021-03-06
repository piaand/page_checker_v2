package com.piaandersin.pagecheck;

import java.io.FileInputStream;
import java.io.InputStream;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * Class sets up the LogConfiguration and creates the handlers
 * for PageCheck logger. The configuration path is loaded from
 * application.properties.
 *
 * In any errors when reading log file or log configurations, systems exits.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogConfiguration {

    private static final LogManager LOGMANAGER = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());

    @Value("${logging.path}")
    private String configFileName;

    public void setConfig() {
        try {
            FileInputStream configFile = new FileInputStream(configFileName);
            LOGMANAGER.getLogManager().readConfiguration(configFile);
            LOGGER.log(Level.INFO, "Read the logger configurations");
        } catch (IOException | NullPointerException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration", exception);
            System.exit(1);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "Unexpected error in loading configuration", exception);
            System.exit(1);
        }
    }

}
