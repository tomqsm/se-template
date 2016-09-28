package com.tomasz.design.framuga.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toks
 */
class Consumer1 implements Runnable {

    private final BlockingQueue<Product> queue;

    Consumer1(BlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!productionStopped(queue.peek())) {
                Product p = queue.take();
                System.out.println(this.getClass().getSimpleName() + "Consumed: " + p);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean productionStopped(Product p) {
        return p == Producer.STOPPER;
    }
}