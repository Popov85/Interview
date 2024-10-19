package com.interview.concurrency;

import java.util.concurrent.StructuredTaskScope;

public class Fibers {
    public static void main(String[] args) {
        Fibers fibers = new Fibers();
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var task1 = scope.fork(fibers::fetchDataFromService1);
            var task2 = scope.fork(fibers::fetchDataFromService2);
            var task3 = scope.fork(fibers::fetchDataFromService3);

            // Wait for all tasks to complete or fail
            scope.join();          // Wait for all tasks to complete
            scope.throwIfFailed(); // If any task fails, propagate the exception

            // Retrieve results from the tasks
            String result1 = task1.get();
            String result2 = task2.get();
            String result3 = task3.get();
            System.out.println("Results: " + result1 + ", " + result2 + ", " + result3);
        } catch (Exception e) {
            System.err.println("Error in task execution: " + e.getMessage());
        }
    }


    private String fetchDataFromService1() throws InterruptedException {
        System.out.println("Fetch 1 in: " + Thread.currentThread());
        Thread.sleep(1000);  // Simulate network or processing delay
        return "Service1 Data";
    }

    private String fetchDataFromService2() throws InterruptedException {
        System.out.println("Fetch 2 in: " + Thread.currentThread());
        Thread.sleep(2000);  // Simulate network or processing delay
        return "Service2 Data";
    }

    private String fetchDataFromService3() throws InterruptedException {
        System.out.println("Fetch 3 in: " + Thread.currentThread());
        Thread.sleep(1500);  // Simulate network or processing delay
        // Simulate a failure
        throw new RuntimeException("Service3 failed");
    }
}
