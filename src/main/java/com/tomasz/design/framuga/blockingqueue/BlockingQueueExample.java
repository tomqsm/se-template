package com.tomasz.design.framuga.blockingqueue;

import java.util.concurrent.*;

/**
 * Horstman, 766 Using a queue data staructure as synchronization mechanism.
 *
 * @author toks
 */
public class BlockingQueueExample {

    public static void main(String... args) {
        BlockingQueue<Product> queue = new ArrayBlockingQueue<>(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
