/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author piaandersin
 */

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class RequestHTTP {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    @Autowired
    ActiveConnection httpcon;
    
    public void timePerformance(Timer timer, Long start_nano, Long stop_nano) {
        try {
            Long seconds = timer.countDurationSeconds(start_nano, stop_nano);
            timer.logPerformanceTime(seconds);
        } catch (RuntimeException exception) {
            logger.log(Level.WARNING,"Measuring time for request performance met error." );
        }
    }
    
    public String readResponse() {
        try {
            BufferedReader reader = new BufferedReader(httpcon.openInputStream());
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();
            String result = content.toString();
            return (result);
        } catch (IOException e) {
            logger.warning("Error: couldn't read the content from " + httpcon.getUrl());
            return (null);
        }
    }
    
    public String getResponse() {
        String content = null;
        int status = httpcon.getStatusCode(); 
        if (status > 299) {
            logger.warning("URL " + httpcon.getUrl() + " returned not success status code " + status);
            return (null);
        } else {
            content = readResponse();
            return (content);
        }
    }
    
    
    public String doRequest() {
        try {
            String content = null;
            httpcon.openConnection();
            httpcon.setConnection();
            content = getResponse();
            return (content);
        } catch (Exception e){
            String errorName = e.getClass().getSimpleName();
            logger.log(Level.WARNING,
                    "Error: making request failed with url " + httpcon.getUrl() + " due to " + errorName);
            e.printStackTrace();
            return (null);
        } finally {
            httpcon.closeConnection();
        }
    }
    
    public void checkRules(Page page, String content) throws Exception {
        try {
            ArrayList<Rule> listRules = page.getRules();
            
            if (listRules.isEmpty()) {
                logger.info(
                    "No rules have been set to this URL " + httpcon.getUrl());
            } else {
                boolean passed = true; 
                for (Rule rule : listRules) {
                //check rule category
                //check category requirements
                //passed = false if failed a test
                }
                if (passed) {
                    logger.info(
                        "All requirements passed with url " + httpcon.getUrl());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void performRequest(Page page) {
        try {
            Timer timer = new Timer();
            Long start = System.nanoTime();
            timer.setStart(start);
            URL url_address = new URL(page.getUrl());
            httpcon.setUrl(url_address);
            String response_content = this.doRequest();
            if (response_content == null) {
                //move on
            } else {
                this.checkRules(page, response_content);
            }
            timer.setStop(System.nanoTime());
            timePerformance(timer, timer.getStart(), timer.getStop());
        } catch (Exception e) {
            System.out.println("Caught exception in perform request");
            System.out.println(e);
        }
    }
}
