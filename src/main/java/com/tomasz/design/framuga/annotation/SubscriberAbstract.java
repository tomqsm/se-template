package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is abstracted functionality all subscribers should do i.e. signal its
 * monitors about ongoing event of receiving unmarshalled object of their type.
 *
 * @param <T>
 * @author kusmierc
 */
public abstract class SubscriberAbstract<T extends Visitorable> {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriberAbstract.class);
    /**
     * Object interested in events happening at this level i.e. message about a
     * type of change and new value change.
     */
    protected final T visitor;

    /**
     * Monitor is a class implementing Visitorable, interested in events
 happening at this level i.e. type of update and new value changes.
     *
     * @param monitor
     */
    public SubscriberAbstract(final T monitor) {
        this.visitor = monitor;
    }
    
    public void signalMonitor(UpdateModel model) {
        visitor.changeDataModel(model);
    }

}
