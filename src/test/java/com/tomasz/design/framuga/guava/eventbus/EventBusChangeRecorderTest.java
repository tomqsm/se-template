package com.tomasz.design.framuga.guava.eventbus;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class EventBusChangeRecorderTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void eventSender() {
        Event event = new Event();
        event.setSourceClass(getClass());
        EventListener eventListener = new EventListener();
        EventRegistry.LISTENERS.add(eventListener);
        EventRegistry.LISTENERS.post(event);
        assertEquals(getClass(), eventListener.getEventSource());
    }
}
