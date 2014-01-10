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
        Event event = new Event(this);
        event.setSourceClass(getClass());
        EventListener eventListener = new EventListener();
        EventRegistry.LISTENERS.add(eventListener);
        EventRegistry.LISTENERS.post(event);
        assertEquals(getClass(), eventListener.getEventSource());
    }
    @Test
    public void eventInAnonymousWay(){
        Event event = new Event(new Integer(13));
        event.setSourceClass(getClass());
        Listener l = new Listener() {

            @Override
            public void listen(Event event) {
                System.out.println("time: " + event.getTime());
                System.out.println("data: " + event.getSource());
            }
        };
        EventRegistry.LISTENERS.add(l);
        EventRegistry.LISTENERS.post(event);
    }
    
    @Test
    public void mimickedMainTest(){
        MainMimicked.main();
    }
}
