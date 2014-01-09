package com.tomasz.design.framuga.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 *
 * @author Tomasz
 */
public class ExecutorCallback {

    public void executorWithListener() {
        ListeningExecutorService executor = MoreExecutors.
                listeningDecorator(Executors.newFixedThreadPool(4));
        ListenableFuture<Integer> futureResult = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return pushButton();
            }
        });
        Futures.addCallback(futureResult, new FutureCallback<Integer>() {
            // we want this handler to run immediately after we push the big red button!
            @Override
            public void onSuccess(Integer r) {
                
            }

            @Override
            public void onFailure(Throwable thrown) {
                System.err.println("thrown = " + thrown);
            }
        });
        executor.shutdown();
    }

    private Integer pushButton() {
        for (Integer i = 0; i < 2000000000; i++) {
            if (i % 1000000000 == 0) {
                System.out.println(true);
            }
        }
        return 1234;
    }
}
