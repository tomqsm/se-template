package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public interface Listener <Event>{
    void listen(Event event);
}
