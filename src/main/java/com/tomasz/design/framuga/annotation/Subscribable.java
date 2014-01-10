package com.tomasz.design.framuga.annotation;

/**
 * Interface required by all observers. This method is to be invoked upon an
 * observed event.
 *
 * @param <T>
 * @author kusmierc
 */
public interface Subscribable<T> {

    void execute(T value);
}
