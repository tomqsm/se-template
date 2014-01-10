package com.tomasz.design.framuga.annotation;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kusmierc
 */
public class AnnotationRegistrator {

    private static final Logger LOG = LoggerFactory.getLogger(AnnotationRegistrator.class);
    private List<Subscribable<?>> subsribers;

    public AnnotationRegistrator() {
        subsribers = new ArrayList<>();
    }

    /**
     * Not deterministic about the order in which sunscribers are added to the
     * list.
     *
     * @param visitor
     */
    public void registerAnnotatedSubscribers(final Visitorable visitor) {
        final Reflections reflections = new Reflections("com.tomasz.design.framuga.annotation");
        final Set<Class<?>> subscriberClasses = reflections.getTypesAnnotatedWith(Subscriber.class);
        final Class<?>[] annotatedClasses = subscriberClasses.toArray(new Class<?>[]{});
        for (final Class<?> cl : annotatedClasses) {
            try {
                final Constructor<?> declaredConstructor = cl.getDeclaredConstructor(Visitorable.class);
                declaredConstructor.setAccessible(true);
                final Subscribable<?> subscribable
                        = (Subscribable) declaredConstructor.newInstance(visitor);
                subsribers.add(subscribable);
            } catch (final Exception ex) {
                LOG.error("Annotating subscriber error. ", ex);
            }
        }
    }

    public List<Subscribable<?>> getSubsribers() {
        return subsribers;
    }
}
