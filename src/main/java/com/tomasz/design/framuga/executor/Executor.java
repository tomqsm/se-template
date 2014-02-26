package com.tomasz.design.framuga.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Executor {

    public static void main(String... args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        ProductionTask productionTask = new ProductionTask();
        Future<Product> result = pool.submit(productionTask);
        Product product = null;
        try {
            product = result.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(product);
        pool.shutdown();
    }
}
