package com.piaandersin.pagecheck;

import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class that operates the runPageCheck function repeatedly according to the
 * cron configuration in enviromental variables.
 *
 * Static block runs ones and calls LogConfiguration file that reads the
 * configuration from logging.properties.
 */
@Component
public class PageCheck {

    private static final Logger logger = Logger.getLogger(PageCheck.class.getName());

    @Autowired
    LogConfiguration config;

    @Autowired
    PageReader reader;

    @Autowired
    RequestHTTP fetcher;

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Helsinki")
    public void runPageCheck() {
        config.setConfig();
        ArrayList<Page> pages = reader.readConfigFile();
        for (Page page : pages) {
            fetcher.performRequest(page);
        }
        reader.setListPages(new ArrayList<Page>());
        logger.info("Ended the requirement checking.");
    }

}
