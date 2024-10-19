package com.interview.concurrency.synchronization.robot;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paw implements Runnable {

    private final int name;

    private static volatile int leg = 1; // Flag to indicate whose turn it is

    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    public Paw(int name) {
        this.name = name;
    }

    // 2 threads evoke concurrently
    @Override
    public void run() {
        while (true) {
            step();
        }
    }


    private void step() {
        lock.lock();
        try {
            // Wait until it's this thread's turn
            while (name!=leg) {
                condition.await();
            }
            // Perform the step
            System.out.println("Do step name = " + name);
            // Toggle the turn
            leg++;
            if (leg>4) {
                leg = 1;
            }
            // Signal the other thread to proceed
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
