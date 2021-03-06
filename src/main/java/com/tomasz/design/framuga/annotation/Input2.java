package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A part of MVC structure used in this application. Liaises with UnmarshallController and registred with it monitor classes.
 * 
 * @author kusmierc
 */
public class Input2 {

    private static final Logger LOG = LoggerFactory.getLogger(Input2.class);
    private final String name = "input2";

    public String getName() {
        return name;
    }
    
}
