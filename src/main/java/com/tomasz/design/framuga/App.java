package com.tomasz.design.framuga;

import com.tomasz.design.framuga.config.Configuration;
import com.tomasz.design.framuga.guava.ExecutorCallback;
import com.tomasz.design.framuga.guava.GuavaExpriment;
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
        LOGGER.info("Configuration xml color: {}", Configuration.COLOR.toString());
        Configuration.COLOR.setValue("red");
        LOGGER.info("Configuration xml color: {}", Configuration.COLOR.toString());
        ExecutorCallback ec = new ExecutorCallback();
        GuavaExpriment ge = new GuavaExpriment();
        ec.executorWithListener();
        ge.scan();
        System.out.println("Main Finished");
    }
}
