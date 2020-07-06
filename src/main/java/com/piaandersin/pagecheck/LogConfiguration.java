/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
 
public class LogConfiguration {
 
    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());
    static{
        try {
            logManager.readConfiguration(new FileInputStream("./src/main/resources/logging.properties"));
            LOGGER.log(Level.INFO, "Read the logger configurations");
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
            System.exit(1);
        }
    }
}
