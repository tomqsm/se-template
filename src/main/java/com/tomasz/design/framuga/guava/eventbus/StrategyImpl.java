package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
class StrategyImpl extends Event<EventModel>{

    private EventModel eventModel;

    public StrategyImpl(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    

}
