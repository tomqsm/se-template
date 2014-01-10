package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A part of MVC structure used in this application. Liaises with UnmarshallController and registred with it monitor classes.
 * 
 * @author kusmierc
 */
public class Input1 {

    private static final Logger LOG = LoggerFactory.getLogger(Input1.class);
    private final String name = "model1";

    public String getName() {
        return name;
    }
    
}
