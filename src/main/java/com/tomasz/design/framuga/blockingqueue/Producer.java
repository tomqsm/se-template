package com.tomasz.design.framuga.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author toks
 */
class Producer implements Runnable {

    Producer(BlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                Product p = new Product();
                p.setWeight(i);
                System.out.println("Produced: " + p);
                queue.put(p);
            }
            stopProduction();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void stopProduction() throws InterruptedException {
        queue.put(STOPPER);
    }

    private BlockingQueue<Product> queue;
    public static Product STOPPER = new Product();
}
