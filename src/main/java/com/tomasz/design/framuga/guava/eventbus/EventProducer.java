package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class EventProducer {

    private String producerData = "producer data example";

    public EventProducer() {
        Event event = new Event(this);
        EventRegistry.LISTENERS.post(event);
    }

    public String getProducerData() {
        return producerData;
    }

}
