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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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
 * Class extracts the url addresses and rules from pagecheck configuration file
 * (path of file in application properties).
 * 
 * Whole configuration file is read first before running the checks and comparing
 * them to requirements. In case the address is not a valid URL address, row is skipped
 * and a warning is logged to the file.
 */

@Component
@Data @NoArgsConstructor @AllArgsConstructor
public class PageReader {
    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());
    
    @Value( "${pagecheck.configuration}" )
    private String configFileName;
    private ArrayList<Page> listPages = new ArrayList<Page>();
    
    public boolean isValidURL(String url) throws MalformedURLException, URISyntaxException
    {
        new URL(url).toURI(); 
        return true; 
    }
    
    public String extractURL(String line) {
        char delimiter = ';';
        String url;
        
        try {
            int index = line.indexOf(delimiter);
            if (index != -1) {
                url = line.substring(0, index);
            } else {
                url = line;
            }
            isValidURL(url);
            return (url);
        } catch (MalformedURLException|URISyntaxException exception) {
            throw new java.lang.RuntimeException("Error: line has no valid URL address.");
        }
    }
    
    public Page extractRequest(String line, int line_nb) {
        try {
            String url = extractURL(line);
            Page request = new Page();
            request.setUrl(url);
            return (request);
        } catch (RuntimeException expection) {
             logger.log(Level.WARNING,
                    "Exception met when reading address from page check"
                            + " configuration file. Line nb " + line_nb + " skipped.");
            return (null);
        }
     }
    
    private void addToListPages(String line, int line_nb) {
    
        Page request = extractRequest(line, line_nb);
        if (request != null) {
            listPages.add(request);
        }
    }
    
    private void iterateConfigFile(BufferedReader readPages) {
        try {
            String line;
            int i = 0;
            while((line = readPages.readLine()) != null) {
                addToListPages(line, i);             
                i++;
            }
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Exception met when reading page check config file", exception);
        } 
    }
    
    public BufferedReader connectToConfigfile(String fileName){
        try {
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
    
    /*
    //Below is for development testing
    public void reportCurrentTime() {
            String dateString = dateFormat.format(new Date());
            logger.info("The time is now " + dateString);         
    }*/
    
    public ArrayList<Page> readConfigFile() {
        try {
            String fileName = this.getConfigFileName();
            BufferedReader pageCheckContent = connectToConfigfile(fileName);
            if(pageCheckContent == null) {
                return (listPages);
            } else {
                iterateConfigFile(pageCheckContent);
                pageCheckContent.close();
            }
        } catch (IOException exception){
            logger.log(Level.SEVERE, "Error reading paeg check configuration file",exception);
        } finally {
            return (listPages);
        }
    }
}
