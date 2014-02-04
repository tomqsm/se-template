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
public abstract class SubscriberAsyncAbstract<T extends Visitorable> {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriberAsyncAbstract.class);
    /**
     * Object interested in events happening at this level i.e. message about a
     * type of change and new value change.
     */
    protected final T visitor;
    protected ListeningExecutorService service;
    protected FutureCallback<Object> futureCallback;

    /**
     * Monitor is a class implementing Visitorable, interested in events
     * happening at this level i.e. type of update and new value changes.
     *
     * @param visitor
     */
    public SubscriberAsyncAbstract(final T visitor) {
        this.visitor = visitor;
    }

    public SubscriberAsyncAbstract(final T visitor, final ListeningExecutorService service) {
        this.visitor = visitor;
        this.service = service;
        this.futureCallback = new FutureCallback<Object>() {

            @Override
            public void onSuccess(Object v) {
                executionSuccessCallback();
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                executionFailedCallback();
            }
        };
    }

    public void signalVisitor(final UpdateModel model) {
        final ListenableFuture<?> submited = service.submit(new Runnable() {
            
            @Override
            public void run() {
                visitor.changeDataModel(model);
            }
        });
        Futures.addCallback(submited, futureCallback);
    }

    public void executionSuccessCallback() {
        LOG.trace(" abstract sub thread finished successfuly ");
    }

    public void executionFailedCallback() {
        LOG.error(" abstract operation failed: {}");
    }
}
