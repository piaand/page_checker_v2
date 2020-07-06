/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Class that operates the runPageCheck function repeatedly
 * according to the cron configuration in enviromental variables.
 * 
 * Static block runs ones and calls LogConfiguration file that reads the
 * configuration from logging.properties.
 * 
 * In any errors when reading log file or log configurations, systems exits.
 */

@Component
public class PageCheck {
    
   private static final Logger logger = Logger.getLogger(PageCheck.class.getName());

   static{
        LogConfiguration config = new LogConfiguration();
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler  = new FileHandler("./pagecheck.log");
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error in loading the log file",e);
            System.exit(1);
        }
    }

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    public void runPageCheck() {
        PageReader check = new PageReader();
        check.reportCurrentTime();
    }

    public static void printInstructions() {
            System.out.println("Start program by giving it only one argument. That argument represents the amount of hours the program should be repeated. Amount can be minimun of 1.");
    }
}

