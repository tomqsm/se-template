package com.tomasz.design.framuga;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.tomasz.design.framuga.annotation.AnnotationRegistrator;
import com.tomasz.design.framuga.annotation.Input1;
import com.tomasz.design.framuga.annotation.Input2;
import com.tomasz.design.framuga.annotation.Subscribable;
import com.tomasz.design.framuga.annotation.Visitor;
import com.tomasz.design.framuga.annotation.Visitorable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

    public static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws ConfigurationException, InterruptedException {

        final ExecutorService pool = Executors.newFixedThreadPool(4);
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

        final Visitorable visitor = new Visitor();
        final AnnotationRegistrator registrator = new AnnotationRegistrator();
        registrator.registerAnnotatedSubscribers(visitor, service);

        final List<Subscribable<?>> subscribers = registrator.getSubsribers();
        final Input1 input1 = new Input1();
        final Input2 input2 = new Input2();
        for (Subscribable s : subscribers) {
            try {
                s.execute(input1);
            } catch (ClassCastException e) {
                LOG.error(" mismatched input ");
            }
//            try{
//                s.execute(input2);
//            } catch (ClassCastException ex){
//                LOG.error("mismatched input");
//            }
        }
        service.shutdown();
        LOG.trace("main finished");
    }
}
