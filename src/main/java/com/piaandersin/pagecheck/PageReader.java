/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author piaandersin
 */
public class PageReader {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    public void reportCurrentTime() {
            String dateString = dateFormat.format(new Date());
            logger.info("The time is now " + dateString);         
    }
}
