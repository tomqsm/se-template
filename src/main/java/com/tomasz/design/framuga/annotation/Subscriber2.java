package com.tomasz.design.framuga.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class identifies type of update to be undertaken.
 *
 * @author kusmierc
 */
@Subscriber
public class Subscriber2 extends SubscriberAbstract<Visitorable> implements Subscribable<Input2> {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber2.class);

    public Subscriber2(final Visitorable visitor) {
        super(visitor);
    }

    @Override
    public void execute(final Input2 value) {
        LOG.trace("running | " + value.getName());
        UpdateModel um = new UpdateModel();
        signalVisitor(um);
    }

}
