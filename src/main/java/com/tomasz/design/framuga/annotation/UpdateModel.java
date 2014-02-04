package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A part of MVC structure used in this application. Liaises with UnmarshallController and registred with it monitor classes.
 * 
 * @author kusmierc
 */
public class UpdateModel {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateModel.class);
    private String name = "my model";

    public String getName() {
        return name;
    }
    
    public void setName(final String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "model: " + name;
    }
    
}
