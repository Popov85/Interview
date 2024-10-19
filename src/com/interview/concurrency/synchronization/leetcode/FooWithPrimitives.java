package com.interview.concurrency.synchronization.leetcode;

public class FooWithPrimitives implements Foo {

    private int step = 1;

    @Override
    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            while (step!=1) {
                wait();
            }
            printFirst.run();
            step=2; // Signal that second() can now proceed
            notifyAll();
        }
    }

    @Override
    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while (step!=2) {
                wait();
            }
            printSecond.run();
            step=3; // Signal that third() can now proceed
            notifyAll();
        }

    }

    @Override
    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (step!=3) {
                wait();
            }
            printThird.run();
        }
    }
}
