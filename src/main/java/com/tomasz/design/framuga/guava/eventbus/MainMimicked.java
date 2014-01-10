package com.tomasz.design.framuga.guava.eventbus;

/**
 *
 * @author kusmierc
 */
public class MainMimicked {
    public static void main(){
        EventListener eventListener = new EventListener();
        EventRegistry.LISTENERS.add(eventListener);
        EventProducer producer = new EventProducer();
    }    
}
