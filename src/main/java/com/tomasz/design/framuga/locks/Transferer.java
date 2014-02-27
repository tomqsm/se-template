package com.tomasz.design.framuga.locks;

/**
 * Horstman, 740.
 */
public class Transferer implements Runnable {

    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private static int DELAY = 10000;

    public Transferer(Bank bank, int from, double max) {
        this.bank = bank;
        this.fromAccount = from;
        this.maxAmount = max;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep(2000);
//                Thread.sleep((int) (DELAY * Math.random()));
            }
        } catch (InterruptedException exception) {
            
        }
    }

}
