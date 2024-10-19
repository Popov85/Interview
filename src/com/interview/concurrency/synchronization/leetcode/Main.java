package com.interview.concurrency.synchronization.leetcode;

public class Main {

    public static void main(String[] args) {

        Foo foo = new FooWithLock();

        Thread t1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the threads

        t1.start();
        t2.start();
        t3.start();
    }
}
