package com.interview.concurrency.synchronization.robot;

/**
 * Yandex interview
 * https://www.youtube.com/watch?v=y3y9tmQNPCc&list=PLXi_yqrmaspzzdPH3v3nAttFDoBY44Cqc&index=30
 * Task: synchronize threads to perform threads alternately, like 1-2-3-4, 1-2-3-4, etc..
 */
public class RobotDog {
    public static void main(String[] args) {
        new Thread(new Paw(1)).start();
        new Thread(new Paw(2)).start();
        new Thread(new Paw(3)).start();
        new Thread(new Paw(4)).start();
    }
}
