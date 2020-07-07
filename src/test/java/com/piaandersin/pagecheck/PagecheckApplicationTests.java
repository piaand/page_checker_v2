package com.piaandersin.pagecheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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
import java.nio.file.Files;
import java.io.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/appTest.properties")
class PagecheckApplicationTests {
    
    private static final LogManager LOGMANAGER = LogManager.getLogManager();   
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());
    private static final String logFileName = "./testcheck.log";
    private static final File logFile = new File(logFileName);
    
        @Autowired
        LogConfiguration config;
        
        @Autowired
        PageReader reader;
        
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

}
