package com.interview.concurrency.synchronization.robot;

/**
 * Yandex interview
 * https://www.youtube.com/watch?v=y3y9tmQNPCc&list=PLXi_yqrmaspzzdPH3v3nAttFDoBY44Cqc&index=30
 * Task: synchronize threads to perform threads alternately, like left-right, left-right, etc..
 */
public class Robot {
    public static void main(String[] args) {
        new Thread(new Foot("left")).start();
        new Thread(new Foot("right")).start();
    }
}
