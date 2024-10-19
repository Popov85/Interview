package com.interview.concurrency.synchronization.robot;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foot implements Runnable {

    private final String name;

    private static AtomicInteger step = new AtomicInteger(0);

    private static final Object obj = new Object();

    private static volatile boolean leftTurn = true; // Flag to indicate whose turn it is

    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static final Semaphore leftSemaphore = new Semaphore(1);
    private static final Semaphore rightSemaphore = new Semaphore(0);

    private static final AtomicBoolean leftStep = new AtomicBoolean(true);

    public Foot(String name) {
        this.name = name;
    }

    // 2 threads evoke concurrently
    @Override
    public void run() {
        while (true) {
            step1(step.incrementAndGet());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void step() {
        synchronized (obj) {
            try {
                if (("left".equals(name) && leftTurn)
                        || ("right".equals(name) && !leftTurn)) {
                    System.out.println("Step is made: Thread, name = " + name + " left step= " + leftTurn);
                    leftTurn = !leftTurn;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void step1(int step) {
        // Attempt to perform the step using CAS
        if (("left".equals(name) && leftStep.compareAndSet(true, false)) ||
                ("right".equals(name) && leftStep.compareAndSet(false, true))) {
            // If CAS is successful, print the step
            System.out.println("Step is made: " + name+ " step= " + step);
        }
    }

    private void step2() {
        try {
            if (("left".equals(name) && leftStep.get())
                    || ("right".equals(name) && !leftStep.get())) {
                System.out.println("Step is made:" + name);
                leftStep.set(!leftStep.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void step3() {
        lock.lock();
        try {
            // Wait until it's this thread's turn
            while (("left".equals(name) && !leftTurn) ||
                    ("right".equals(name) && leftTurn)) {
                condition.await();
            }
            // Perform the step
            System.out.println("Do step name = " + name);
            // Toggle the turn
            leftTurn = !leftTurn;
            // Signal the other thread to proceed
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void step4() {
        try {
            if ("left".equals(name)) {
                leftSemaphore.acquire(); // Wait until the left semaphore has a permit
                // Perform the step for the left thread
                System.out.println("Step by = " + name);
                rightSemaphore.release(); // Release a permit for the right thread
            } else {
                rightSemaphore.acquire(); // Wait until the right semaphore has a permit
                // Perform the step for the right thread
                System.out.println("Step by = " + name);
                leftSemaphore.release(); // Release a permit for the left thread
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
