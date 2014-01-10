package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kusmierc
 */
public class Visitor implements Visitorable{
    private static final Logger LOG = LoggerFactory.getLogger(Visitor.class);
    @Override
    public void changeDataModel(UpdateModel updateModel) {
//        LOG.info("Visistor ready do operate on model");
        System.out.println("vv");
    }
    
}
