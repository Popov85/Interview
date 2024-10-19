package com.interview.concurrency.synchronization.leetcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class FooWithLock implements Foo {

    private int step = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    @Override
    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            while (step != 1) {
                condition.await(); // Wait for the correct step
            }
            printFirst.run(); // Execute the first task
            step = 2; // Update the step to allow second() to proceed
            condition.signalAll(); // Notify all waiting threads
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (step != 2) {
                condition.await(); // Wait for the correct step
            }
            printSecond.run(); // Execute the second task
            step = 3; // Update the step to allow third() to proceed
            condition.signalAll(); // Notify all waiting threads
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (step != 3) {
                condition.await(); // Wait for the correct step
            }
            printThird.run(); // Execute the third task
            // No need to update the step or signal here as this is the last step
        } finally {
            lock.unlock();
        }
    }
}
