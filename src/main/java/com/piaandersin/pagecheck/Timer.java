/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Class provides a simple timer that keeps track of time elapsed in performing a
 * task in milliseconds.
 * 
 * Throws Runtime in case start time is "later" than stop time.
 */

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class Timer {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    private Long start = 0L;
    private Long stop = 0L;
    
    public void logPerformanceTime(double seconds) {
        logger.info("It took " + seconds + " milliseconds to perform the request.");
    }
    
    public double countDurationMilliSeconds(Long start_nano, Long stop_nano) throws RuntimeException {
        long nanosDuration = stop_nano - start_nano;
        if (nanosDuration < 0) {
            throw new RuntimeException("Counted time should not be negative");
        }
        double milliseconds = TimeUnit.MILLISECONDS.convert(nanosDuration, TimeUnit.NANOSECONDS);
        return (milliseconds);
    }
    
}
