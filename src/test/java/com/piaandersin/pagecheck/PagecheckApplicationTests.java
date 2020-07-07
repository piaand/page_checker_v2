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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/appTest.properties")
class PagecheckApplicationTests {
    
    private static final LogManager LOGMANAGER = LogManager.getLogManager();   
    private static final Logger LOGGER = Logger.getLogger(PageCheck.class.getName());
    
        @Autowired
        LogConfiguration config;
        
        @BeforeEach
        void configure_log() {
            config.setConfig();
        }
        
	@Test
	void contextLoads() {
            LOGGER.fine("Writing to testfile.");
            assertTrue(true);
        }

}
