package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class Event {

    private Class sourceClass;

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }
    
}
