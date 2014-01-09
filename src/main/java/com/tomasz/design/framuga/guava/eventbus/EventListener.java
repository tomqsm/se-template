package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class EventListener implements Listener<EventModel>{
    
    private String receivedMessage;

    public String getReceivedMessage() {
        return receivedMessage;
    }

    @Override
    public void listen(EventModel event) {
        System.out.println("event model: " + event.getName());
    }
}
