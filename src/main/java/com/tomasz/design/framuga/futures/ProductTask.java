package com.tomasz.design.framuga.futures;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ProductTask implements Callable<Product> {

    @Override
    public Product call() {
        Product p = new Product();
        try {
            // long running computation of product weight
            Thread.sleep(3000);
            p.setWeight(12);
            p.setProducer(Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
