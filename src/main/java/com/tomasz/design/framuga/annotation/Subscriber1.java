package com.tomasz.design.framuga.annotation;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class identifies type of update to be undertaken.
 *
 * @author kusmierc
 */
@Subscriber
public class Subscriber1 extends SubscriberAsyncAbstract<Visitorable> implements Subscribable<Input1> {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriber1.class);

    public Subscriber1(final Visitorable visitor) {
        super(visitor);
    }

    public Subscriber1(final Visitorable visitor, final ListeningExecutorService service) {
        super(visitor, service);
    }

    @Override
    public void execute(final Input1 value) {
        LOG.trace("dispatching to async | " + value.getName());
        UpdateModel um = new UpdateModel();
        super.signalVisitor(um);
        LOG.trace("delegated to async | " + value.getName());
    }

    /**
     *
     */
    @Override
    public final void executionSuccessCallback() {
        LOG.trace(" subscriber's success callback ");
    }

    @Override
    public final void executionFailedCallback() {
        LOG.error(" subscriber's failure callback ");
    }
}
