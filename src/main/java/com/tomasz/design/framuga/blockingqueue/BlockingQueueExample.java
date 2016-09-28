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
        Consumer1 consumer1 = new Consumer1(queue);
        Consumer2 consumer2 = new Consumer2(queue);
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
