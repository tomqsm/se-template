package com.tomasz.design.framuga.annotation;

import com.google.common.util.concurrent.ListeningExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class identifies type of update to be undertaken.
 *
 * @author kusmierc
 */
@Subscriber
public class Subscriber2 extends SubscriberAsyncAbstract<Visitorable> implements Subscribable<Input2> {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber2.class);

    public Subscriber2(final Visitorable visitor) {
        super(visitor);
    }

    public Subscriber2(final Visitorable visitor, final ListeningExecutorService service) {
        super(visitor, service);
    }

    @Override
    public void execute(final Input2 value) {
        LOG.trace("running | " + value.getName());
        UpdateModel um = new UpdateModel();
        signalVisitor(um);
        LOG.trace("finished | " + value.getName());
    }

}
