package com.tomasz.design.framuga.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 *
 * @author kusmierc
 */
public interface Listener{
    @Subscribe void listen(Event event);
}
