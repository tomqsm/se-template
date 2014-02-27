package com.tomasz.design.framuga.locks;

/**
 *
 * @author toks
 */
public class Client {

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String... args) {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            Transferer t = new Transferer(b, i, INITIAL_BALANCE);
            Thread th = new Thread(t);
            th.start();
        }
    }
}
