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
 * task.
 * 
 * Throws Runtime in case start time is "later" than stop time.
 */

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class Timer {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    private Long start;
    private Long stop;
    
    public void logPerformanceTime(Long seconds) {
        logger.info("It took " + seconds + " seconds to perform the request.");
    }
    
    public long countDurationSeconds(Long start_nano, Long stop_nano) throws RuntimeException {
        long nanosDuration = stop_nano - start_nano;
        if (nanosDuration < 0) {
            throw new RuntimeException("Counted time should not be negative");
        }
        long seconds = TimeUnit.SECONDS.convert(nanosDuration, TimeUnit.NANOSECONDS);
        return (seconds);
    }
    
}
