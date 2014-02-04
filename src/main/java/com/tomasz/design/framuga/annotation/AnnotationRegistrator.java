package com.tomasz.design.framuga.annotation;

import com.google.common.util.concurrent.ListeningExecutorService;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
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
                final Constructor<?> constructor = cl.getDeclaredConstructor(Visitorable.class);
                constructor.setAccessible(true);
                final Subscribable<?> subscribable
                        = (Subscribable) constructor.newInstance(visitor);
                subsribers.add(subscribable);
            } catch (final IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
                LOG.error("Annotating subscriber error. ", ex);
            }
        }
    }
    /**
     * Not deterministic about the order in which sunscribers are added to the
     * list.
     *
     * @param visitor
     * @param service
     */
    public void registerAnnotatedSubscribers(final Visitorable visitor, final ListeningExecutorService service) {
        final Reflections reflections = new Reflections("com.tomasz.design.framuga.annotation");
        final Set<Class<?>> subscriberClasses = reflections.getTypesAnnotatedWith(Subscriber.class);
        final Class<?>[] annotatedClasses = subscriberClasses.toArray(new Class<?>[]{});
        for (final Class<?> cl : annotatedClasses) {
            try {
                final Constructor<?> constructor = cl.getDeclaredConstructor(Visitorable.class, ListeningExecutorService.class);
                constructor.setAccessible(true);
                final Subscribable<?> subscribable
                        = (Subscribable) constructor.newInstance(visitor, service);
                subsribers.add(subscribable);
            } catch (final IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
                LOG.error("Annotating subscriber error. ", ex);
            }
        }
    }

    public List<Subscribable<?>> getSubsribers() {
        return subsribers;
    }
}
