package com.tomasz.design.framuga;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) throws ConfigurationException, InterruptedException {
        LOGGER.info("Logging hello world.");
        LOGGER.debug("Logging hello world.");
    }
}
