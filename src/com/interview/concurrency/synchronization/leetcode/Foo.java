package com.interview.concurrency.synchronization.leetcode;

public interface Foo {
    void first(Runnable printFirst) throws InterruptedException;

    void second(Runnable printSecond) throws InterruptedException;

    void third(Runnable printThird) throws InterruptedException;
}
