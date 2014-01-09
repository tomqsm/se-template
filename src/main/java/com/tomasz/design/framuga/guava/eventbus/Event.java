package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public abstract class Event<T> {

    private Class sourceClass;

    public Event() {
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }

}
