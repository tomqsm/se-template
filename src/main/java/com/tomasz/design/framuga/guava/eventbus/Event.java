package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class Event {

    private Class sourceClass;
    private final long time;
    private Object source;

    public Event(Object data) {
        time = System.nanoTime();
        this.source = data;
    }

    public long getTime() {
        return time;
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
