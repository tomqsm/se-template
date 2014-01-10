package com.tomasz.design.framuga.guava.eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kusmierc
 */
public class EventListener implements Listener {
    private static Logger LOG = LoggerFactory.getLogger(EventListener.class);
    private Class eventSource;

    /**
     *
     * @param event
     */
    @Override
    public void listen(Event event) {
        EventProducer producer = (EventProducer) event.getSource();
        LOG.info("received event with data: {}", producer.getProducerData());
        eventSource = event.getSourceClass();
    }

    public Class getEventSource() {
        return eventSource;
    }
}
