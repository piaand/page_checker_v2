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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//@Data @NoArgsConstructor @AllArgsConstructor
public class Page {
	String url;
        //ArrayList<Rule> rules;
    
    public Page(String url) {
	this.url = url;
        //this.rules = new ArrayList<Rule>();
    }
    
    public String getUrl() {
        return (this.url);
    }
    /*
    public ArrayList<Rule> getRules() {
        return (this.rules);
    }
    
    public void setConnection(HttpURLConnection con, Log log) throws IOException {
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
    
    public String readResponse(HttpURLConnection con, Log log) throws IOException {
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
    
    public String doRequests(Log log) throws Exception {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            setConnection(con, log);
            int status = con.getResponseCode();
            
            if (status > 299) {
                handleStatusNotOK(status, log);
                return (null);
            } else {
                String content = readResponse(con, log);
                con.disconnect();
                return (content);
            }
        } catch (Exception e){
            String errorName = e.getClass().getSimpleName();
            log.writeToLog(
                    "Error: making request failed with url " + getUrl() + " due to " + errorName);
            e.printStackTrace();
            return (null);
        } 
    }
    
    public void handleStatusNotOK(int status, Log log) throws Exception {
        try {
            log.writeToLog(
            "Request response was not from Success family. Status code: " + status);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void checkRules(String content, Log log) throws Exception {
        try {
            ArrayList<Rule> listRules = getRules();
            boolean passed = true; 

            for (Rule rule : listRules) {
                //check rule category
                //check category requirements
                //passed = false if failed a test
            }

            if (passed) {
                log.writeToLog(
                    "All requirements passed with url " + getUrl());
            }
        } catch (Exception e) {
            throw e;
        }
    }*/
}
