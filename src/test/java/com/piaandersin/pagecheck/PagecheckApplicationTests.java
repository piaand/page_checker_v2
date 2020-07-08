package com.piaandersin.pagecheck;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/appTest.properties")
class PagecheckApplicationTests {
    
    private static final LogManager LOGMANAGER = LogManager.getLogManager();   
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());
    private static final String logFileName = "./testcheck.log";
    private static final File logFile = new File(logFileName);
    private static final String configFileName = "./configTest_request.txt";
    private static final String hs_address = "https://www.hs.fi/";
    private static final String geek_address = "https://www.geeksforgeeks.org/";
    
    
    private void writeToConfigFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(configFileName, true));
            writer.write(content);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: error writing to configfile.");
            e.printStackTrace();
        }
    }
    
    private void createNewTestConfigFile() {
        try {
            File configFile = new File(configFileName);
            if (configFile.exists()) {
                configFile.delete();
                configFile.createNewFile();
            } else {
                configFile.createNewFile();
            }
          } catch (IOException e) {
            System.out.println("Error: error creating configfile.");
            e.printStackTrace();
          }
    }

        @Autowired
        LogConfiguration config;
        
        @Autowired
        PageReader reader;
        
        @Autowired
        RequestHTTP fetcher;
        
        @Autowired
        ActiveConnection httpcon;
        
        @BeforeAll
        static void init() {
            if (logFile.exists()) {
                logFile.delete();
            }   
        }
        
        @BeforeEach
        void configure_log() {
            config.setConfig();
        }
        
	@Test
	void testCreateLogFile() {
            boolean exists = logFile.exists();
            assertTrue(exists);
        }
        
        @Test
        void emptyPageCheckFile() {
            BufferedReader read = reader.connectToConfigfile("./notAFile");
            assertNull(read); 
        }
        
        @Test
        void testReadConfigFile() {
            assertEquals(configFileName, reader.getConfigFileName());
            createNewTestConfigFile();
            writeToConfigFile(hs_address);
            File configFile = new File(configFileName);
            assertTrue(configFile.exists());
            ArrayList<Page> pages = reader.readConfigFile();
            assertFalse(pages.isEmpty());
        }
        
        @Test
        void testReadConfigFile2() {
            assertEquals(configFileName, reader.getConfigFileName());
            createNewTestConfigFile();
            writeToConfigFile(hs_address);
            writeToConfigFile("Nonsense!");
            writeToConfigFile("More nonsense!");
            writeToConfigFile(geek_address);
            File configFile = new File(configFileName);
            assertTrue(configFile.exists());
            ArrayList<Page> pages = reader.readConfigFile();
            assertEquals(pages.size(), 2);
        }
        
        @Test
        void testDoRequest() {
            Page page = new Page();
            page.setUrl(geek_address);
            fetcher.performRequest(page);
            assertFalse(page.getContent().isEmpty());
        }
        
        @Test
        void testDoRequest2() {
            try {
                Page page = new Page();
                page.setUrl(hs_address);
                URL url_address = new URL(page.getUrl());
                httpcon.setUrl(url_address);
                String response_content = fetcher.doRequest();
                assertNotNull(response_content);
            } catch (MalformedURLException ex) {
                System.out.println("Test met MalformedURL exception");
            }
        }
        
        @Test
        void testStatusCode200() {
            try {
                int status = -1;
                Page page = new Page();
                page.setUrl(hs_address);
                URL url_address = new URL(page.getUrl());
                httpcon.setUrl(url_address);
                httpcon.openConnection();
                httpcon.setConnection();
                status = httpcon.getStatusCode();
                httpcon.closeConnection();
                assertTrue(status >= 200 && status < 299);
            } catch (IOException ex) {
                System.out.println("Test met IOException");
            }
        }

}
