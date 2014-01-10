package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class identifies type of update to be undertaken.
 *
 * @author kusmierc
 */
@Subscriber
public class Subscriber1 extends SubscriberAbstract<Visitorable> implements Subscribable<Input1> {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber1.class);
    public Subscriber1(final Visitorable visitor) {
        super(visitor);
    }

    @Override
    public void execute(Input1 value) {
//        LOG.info("running");
        UpdateModel um = new UpdateModel();
        signalMonitor(um);
    }

    
}
