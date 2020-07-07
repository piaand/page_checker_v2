/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piaandersin.pagecheck;

import java.util.Properties;
import org.springframework.core.env.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author piaandersin
 */

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class PageReader {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    @Value( "${pagecheck.configuration}" )
    private String configFileName;
    
    public BufferedReader connectToConfigfile(String fileName){
        try {
            System.out.println(fileName);
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader buff = new BufferedReader(fileReader);
            logger.info("Started reading from the file: " + fileName);
            return(buff);
        } catch (FileNotFoundException | NullPointerException exception) {
             logger.log(Level.SEVERE, "Configuration file error in loading page checker configuration",exception);
             return (null);
        }
    }
    
    /*private ArrayList<Page> iterateConfigFile(BufferedReader page) {
        try {
            String line;
            int i;
            
            ArrayList<Page> listPages = new ArrayList<Page>();
            i = 0;
            while((line = bufferedReader.readLine()) != null) {
                try {
                    RequestHTTP request = extractRequest(line);
                    listRequests.add(request);
                } catch(Exception e) {
                    log.writeToLog(
                        "Error extracting requests at line " + i);
                }                
                i++;
            }
            // close files
            bufferedReader.close();
        } catch (IOException exception) {
            
        }
    }
    
    public RequestHTTP extractRequest(String line) {
        String url = extractURL(line);
        if (isValidURL(url)) {
            RequestHTTP request = new RequestHTTP(url);
            return (request);
        } else {
            throw new java.lang.RuntimeException("Error: line has no valid URL address.");
        }
    }
    
    public String extractURL(String line) {
        char delimiter = ';';
        
        int index = line.indexOf(delimiter);
        if (index == -1) {
            return (line);
        }
        String url = line.substring(0, index);
        return (url);
    }
    
    public boolean isValidURL(String url) 
    { 
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
        catch (Exception e) { 
            return false; 
        } 
    }*/
    
    public void reportCurrentTime() {
            String dateString = dateFormat.format(new Date());
            logger.info("The time is now " + dateString);         
    }
    
    public ArrayList<Page> readConfigFile() {
        String fileName = this.getConfigFileName();
        BufferedReader pageCheckContent = connectToConfigfile(fileName);
        if(pageCheckContent == null) {
            return (new ArrayList<Page>());
        } else {
          //ArrayList<Page> list = iterateConfigFile(pageCheckContent);
          ArrayList<Page> list = new ArrayList<Page>();
          return (list);
        }
    }
}
