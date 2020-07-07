/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

/**
 *
 * @author piaandersin
 */
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class Page {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    String url;
    ArrayList<Rule> rules = new ArrayList<Rule>();
    
    @Autowired
    Timer timer;
    
    public void timePerformance(Long start_nano, Long stop_nano) {
        try {
            Long seconds = timer.countDurationSeconds(start_nano, stop_nano);
            timer.logPerformanceTime(seconds);
        } catch (RuntimeException exception) {
            logger.log(Level.WARNING,"Measuring time for request performance met error." );
        }
    }
    
    
    public void setConnection(HttpURLConnection con) throws IOException {
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "plain/text");
            con.setConnectTimeout(7000);
            con.setReadTimeout(7000);
        } catch (IOException e) {
            con.disconnect();
            throw e;
        }
    }
    
    public String readResponse(HttpURLConnection con) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();
            String result = content.toString();
            return (result);
        } catch (IOException e) {
            con.disconnect();
            throw e;
        }
    }
    
    public String doRequest(URL ulr_address) throws Exception {
        try {
            String content = null;
            HttpURLConnection con = (HttpURLConnection) ulr_address.openConnection();
            setConnection(con);
            int status = con.getResponseCode();
            
            if (status > 299) {
                logger.warning("URL " + this.url + " returned not success status code " + status);
            } else {
                content = readResponse(con);
            }
            con.disconnect();
            return (content);
        } catch (Exception e){
            String errorName = e.getClass().getSimpleName();
            logger.log(Level.WARNING,
                    "Error: making request failed with url " + getUrl() + " due to " + errorName);
            e.printStackTrace();
            return (null);
        } 
    }
    
    public void checkRules(String content) throws Exception {
        try {
            ArrayList<Rule> listRules = getRules();
            
            if (listRules.size() == 0) {
                logger.info(
                    "No rules have been set to this URL " + getUrl());
            } else {
                boolean passed = true; 
                for (Rule rule : listRules) {
                //check rule category
                //check category requirements
                //passed = false if failed a test
                }
                if (passed) {
                    logger.info(
                        "All requirements passed with url " + getUrl());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void performRequest() {
        try {
            timer.setStart(System.nanoTime());
            URL url_address = new URL(this.getUrl());
            String response_content = this.doRequest(url_address);
            this.checkRules(response_content);
            timer.setStop(System.nanoTime());
            timePerformance(timer.getStart(), timer.getStop());
        } catch (Exception e) {
            System.out.println("Caught exception in perform request");
            System.out.println(e);
        }
    }
    
}
