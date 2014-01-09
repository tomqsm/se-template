package com.tomasz.design.framuga.guava.eventbus;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class EventBusChangeRecorderTest {

    public EventBusChangeRecorderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRecordCustomerChange() {
        Event event = new StrategyImpl(new EventModel("eventA"));
        EventListener eventListener = new EventListener();
        EventRegistry.LISTENERS.add(eventListener);
        EventRegistry.LISTENERS.post(event);
        assertEquals("eventA", eventListener.getReceivedMessage());
    }

}
