package com.interview.stream;

import java.util.List;

/**
 * Filter-Map-Reduce Pattern
 */
public class FilterMapReduce {

    public static void main(String[] args) {
        new FilterMapReduce().call();
    }

    /**
     *  * This is one of the most frequently used patterns in stream processing, combining three key operations:
     *  * Filter: Select elements based on a condition.
     *  * Map: Transform each element to a different form.
     *  * Reduce: Combine elements into a single result (e.g., summing, concatenating).
     *  * Example: Filtering even numbers, mapping to their squares, and summing the result.
     */
    private void call() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int sumOfSquares = numbers.stream()
                .filter(n -> n % 2 == 0)  // Filter even numbers
                .map(n -> n * n)          // Square them
                .reduce(0, Integer::sum);  // Sum the squares

        System.out.println(sumOfSquares);  // Output: 56
    }
}
