package com.tomasz.design.framuga.annotation;

import com.google.common.base.Stopwatch;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class AnnotationRegistratorTest {

    public AnnotationRegistratorTest() {
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
    public void testSomeMethod() {
        Visitorable visitor = new Visitor();
        AnnotationRegistrator registrator = new AnnotationRegistrator();
        registrator.registerAnnotatedSubscribers(visitor);
        final List<Subscribable<?>> subscribers = registrator.getSubsribers();
        assertEquals(2, subscribers.size());
        Input1 input1 = new Input1();
        Input2 input2 = new Input2();
        for (Subscribable s : subscribers) {
            try {
                s.execute(input2);
            } catch (ClassCastException e) {
            }
        }

    }

    /**
     * Simple timing test. Toggle subscribable 1 or 2 to measure differences in
     * performance. When checked they were hardly seen in this setting.
     * Problem is that when cast doesn't fail some action takes place otherwise 
     * just exception runs. 15340 no class cast - 26695 with class cast in 
     * microseconds.
     */
    @Test
    public void simpleTimingtest() {
        final Input1 input1 = new Input1();
        final Input2 input2 = new Input2();
        final Visitorable visitor = new Visitor();
        final Subscribable subscribable1 = new Subscriber1(visitor);
        final Subscribable subscribable2 = new Subscriber2(visitor);
        final Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1; i++) {
            try {
                subscribable1.execute(input1);
            } catch (ClassCastException e) {
                System.out.println("not matched input type");
            }
        }
        final long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.err.println("elapsed = " + elapsed);
        stopwatch.stop();
    }
}
