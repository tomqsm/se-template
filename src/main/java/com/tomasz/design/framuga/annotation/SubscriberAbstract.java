package com.tomasz.design.framuga.annotation;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
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
    private final T visitor;
    private ListeningExecutorService service;

    /**
     * Monitor is a class implementing Visitorable, interested in events
     * happening at this level i.e. type of update and new value changes.
     *
     * @param visitor
     */
    public SubscriberAbstract(final T visitor) {
        this.visitor = visitor;
    }

    public SubscriberAbstract(final T visitor, final ListeningExecutorService service) {
        this.visitor = visitor;
        this.service = service;
    }

    public void signalVisitor(final UpdateModel model) {
        LOG.trace("running");
        final ListenableFuture<?> submited = service.submit(new Runnable() {

            @Override
            public void run() {
                LOG.trace("performing long running operation on " + model.getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    LOG.error("{}", ex.getLocalizedMessage());
                }
                visitor.changeDataModel(model);
            }
        });
        Futures.addCallback(submited, new FutureCallback<Object>() {

            @Override
            public void onSuccess(Object v) {
                LOG.trace(" sub thread finished successfuly " + v);
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                LOG.error("operation failed: {}", thrwbl);
            }
        });
    }
}
