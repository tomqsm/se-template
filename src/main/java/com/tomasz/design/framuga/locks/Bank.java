package com.tomasz.design.framuga.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Horstman, 748
 */
public class Bank {

    private double[] accounts;
    private ReentrantLock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
            bankLock = new ReentrantLock();
            sufficientFunds = bankLock.newCondition();
        }
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            while (accounts[from] < amount) {
                System.out.printf("Waiting ... %s%n", Thread.currentThread().getName());
                sufficientFunds.await();
                System.out.printf("Insufficient balance on %d, %10.2f < %10.2f%n", from, accounts[from], amount);
            }
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d ", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Balance: %10.2f from account %10.2f to account %10.2f ", amount, accounts[from], accounts[to]);
            System.out.printf("Total: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    int size() {
        return accounts.length;
    }

}
