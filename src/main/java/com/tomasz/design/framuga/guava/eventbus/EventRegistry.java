package com.tomasz.design.framuga.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 *
 * @author kusmierc
 */
public enum EventRegistry {
    
    LISTENERS {
                @Override
                public void add(Listener t) {
                    EVENT_BUS.register(t);
                }
                
                @Override
                public void remove(Listener t) {
                    EVENT_BUS.unregister(t);
                }
                
                @Override
                public void post(Event event) {
                    EVENT_BUS.post(event);
                }
            };
    private static final EventBus EVENT_BUS;
    
    static {
        EVENT_BUS = new EventBus();
    }
    
    public abstract void add(Listener t);
    
    public abstract void remove(Listener t);

    public abstract void post(Event event);
}
