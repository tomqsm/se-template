package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class EventListener implements Listener {

    private Class eventSource;

    /**
     *
     * @param event
     */
    @Override
    public void listen(Event event) {
        eventSource = event.getSourceClass();
    }

    public Class getEventSource() {
        return eventSource;
    }
}
