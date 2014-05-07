package com.tomasz.design.framuga;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) throws Exception {
        LOGGER.info("Starting application.");
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true").to("file:data/outbox");
            }
        });
        camelContext.start();
        Thread.sleep(10000);
        camelContext.stop();
    }
}
