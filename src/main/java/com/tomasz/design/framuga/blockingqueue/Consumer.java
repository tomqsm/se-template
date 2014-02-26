package com.tomasz.design.framuga.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toks
 */
class Consumer implements Runnable {

    private final BlockingQueue<Product> queue;

    Consumer(BlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean done = false;
        try {
            while (!done) {
                Product p = queue.take();
                if (productionStopped(p)) {
                    done = true;
                } else {
                    System.out.println("Consumed: " + p);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean productionStopped(Product p) {
        return p == Producer.STOPPER;
    }
}
