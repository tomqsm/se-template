package com.tomasz.design.framuga.executor;

import java.util.concurrent.Callable;

/**
 *
 */
public class ProductionTask implements Callable<Product>{

    @Override
    public Product call() throws Exception {
        Product p = new Product();
        p.setProducer(Thread.currentThread().getName());
        p.setWeight(12);
        return p;
    }
}
