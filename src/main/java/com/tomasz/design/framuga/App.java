package com.tomasz.design.framuga;

import com.tomasz.design.framuga.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        LOGGER.info("Logging hello world.");
        LOGGER.info("Configuration xml color: {}", Configuration.COLOR.toString());
    }
}
