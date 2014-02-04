package com.tomasz.design.framuga.guava.executor;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author kusmierc
 */
public class GuavaExpriment {

    public GuavaExpriment() {

    }

    public void scan() {
        Scanner in = new Scanner(System.in);
        final int nThreads = in.nextInt();
        final int n = in.nextInt();

        System.out.println("Using " + nThreads + " threads");
        final ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

        try {
            try {
                workersDo(service, n);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } finally {
            // necessary or the thread pool will keep the JVM up and running!
            service.shutdown();
        }
    }

    public void workersDo(ListeningExecutorService service, int n) throws InterruptedException, ExecutionException {
        // using the Guava's utility method allAsList will return all the results in a future list
        // enormously simplifying the code:
        ListenableFuture<List<Integer>> results = Futures.successfulAsList(loadTasks(service, n));
        // the call to get() is now the blocking piece of code
        System.out.println("Values returned from computations: " + results.get());
        System.out.println("All done.");
    }

    public void workersCancel(ListeningExecutorService service, int n) throws InterruptedException, ExecutionException {
        List<ListenableFuture<Integer>> tasks = loadTasks(service, n);
        ListenableFuture<List<Integer>> futures = Futures.allAsList(tasks);
        Thread.sleep(500);
        System.out.println("Actually nevermind!");
        futures.cancel(true);
        // let's see how many tasks were actually cancelled by asking the original futures:
        List<Integer> completed = new ArrayList<>();
        for (ListenableFuture<Integer> f : tasks) {
            if (!f.isCancelled()) {
                completed.add(f.get());
            }
        }
        System.out.println("There were " + (n - completed.size()) + " cancelled tasks and " + completed.size() + " completed tasks: " + completed);
        System.out.println("All done.");
    }

    private List<ListenableFuture<Integer>> loadTasks(ListeningExecutorService service, int howMany) {
        System.out.println("Enqueuing " + howMany + " tasks...");
        List<ListenableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= howMany; i++) {
            final int n = i;
            futures.add(service.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    try {
                        try {
                            System.out.println("Task " + n + ": Doing some very important work...");
                            Thread.sleep(200 + rnd.nextInt(200));
                        } catch (InterruptedException e) {
                            System.out.println("Task " + n + " interrupted while doing very important work");
                            return null;
                        }
                        int ret = rnd.nextInt();
                        System.out.println("Task " + n + ": about to return " + ret);
                        return ret;
                    } finally {
                        System.out.println("Task " + n + ": cleaning up");
                    }
                }
            }));
        }
        return futures;
    }

    private final static Random rnd = new Random();

}
