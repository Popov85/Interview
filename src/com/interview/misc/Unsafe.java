package com.interview.misc;

public class Unsafe {
    public static void main(String[] args) {
        sun.misc.Unsafe unsafe = sun.misc.Unsafe.getUnsafe();
        //unsafe.compareAndSwapObject();
        new Object();
    }
}
