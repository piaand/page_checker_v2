/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.support.CronSequenceGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 *
 * @author piaandersin
 */

@Component
public class PageCheck {
    
    private static final Logger log = LoggerFactory.getLogger(PageCheck.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    public void reportCurrentTime() {
            log.info("The time is now {}", dateFormat.format(new Date()));
    }

    public static void printInstructions() {
            System.out.println("Start program by giving it only one argument. That argument represents the amount of hours the program should be repeated. Amount can be minimun of 1.");
    }
}

