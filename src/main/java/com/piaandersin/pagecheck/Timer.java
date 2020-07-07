/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

/**
 * Class provides a simple timer that keeps track of time elapsed in performing a
 * task.
 * 
 * Throws Runtime in case start time is "later" than stop time.
 */

public class Timer {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    private Long start;
    private Long stop;
    
    private long countDurationSeconds(Long start_nano, Long stop_nano) throws RuntimeException {
        long nanosDuration = stop_nano - start_nano;
        if (nanosDuration < 0) {
            throw new RuntimeException("Counted time should not be negative");
        }
        long seconds = TimeUnit.SECONDS.convert(nanosDuration, TimeUnit.NANOSECONDS);
        return (seconds);
    }
}
