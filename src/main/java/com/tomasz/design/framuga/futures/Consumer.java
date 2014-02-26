package com.tomasz.design.framuga.futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Consumer {

    public static void main(String... args) {
        ProductTask productTask = new ProductTask();
        FutureTask<Product> productTaskFuture = new FutureTask<>(productTask);
        Thread t = new Thread(productTaskFuture);
        t.start();
        System.out.println("Task started and " + Thread.currentThread().getName() + " runs on.");
        //some more main thread operations
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        Product p = null;
        try {
            p = productTaskFuture.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("produced: " + p);
        System.out.println("In the meantime execution of " + Thread.currentThread().getName() + " holded.");
    }
}
