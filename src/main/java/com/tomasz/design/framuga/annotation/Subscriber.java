package com.tomasz.design.framuga.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for easier swithing on and off a particular subscriber.
 * @author kusmierc
 */
@Target(value = {ElementType.TYPE})
public @interface Subscriber {
    
}
