package com.tomasz.design.framuga.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Horstman, 784;
 */
public class ExecutorCompletion {

    public static void main(String... args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorCompletionService<Product> completionService = new ExecutorCompletionService<>(pool);
        List<Callable<Product>> tasks = new ArrayList<>();

        Product product = null;
        for (int i = 0; i < 10; i++) {
            ProductionTask productionTask = new ProductionTask();
            tasks.add(productionTask);
        }
        for (Callable<Product> c : tasks) {
            completionService.submit(c);
        }
        try {
            for (int i = 0; i < tasks.size(); i++) {
                product = completionService.take().get();
                System.out.println("processing further: " + product);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ExecutorCompletion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.shutdown();
        }
    }
}
