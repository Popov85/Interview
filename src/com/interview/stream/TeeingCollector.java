package com.interview.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Teeing collector
 * his new collector allows you to perform two independent reductions on a stream and then combine the results in a final step.
 */
public class TeeingCollector {

    private static class Result {

        private int sum;

        private long count;

        public Result(int sum, long count) {
            this.sum = sum;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "sum=" + sum +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        new TeeingCollector().call2();
    }


    /**
     * Example: You can calculate both the sum and the average of a collection of numbers in one pass through the stream.
     */
    private void call() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Result result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),  // First collector
                        Collectors.counting(),                     // Second collector
                        (sum, count) -> new Result(sum, count)));  // Merger function

        System.out.println(result); // Prints Result{sum=15, count=5}
    }

    /**
     * Finding both the maximum and minimum elements of a stream.
     */
    private void call2() {
        List<Integer> numbers = List.of(5, 2, 8, 1, 3);

        // Finding both maximum and minimum in a single pass
        Result result = numbers.stream().collect(
                Collectors.teeing(
                        Collectors.maxBy(Integer::compareTo),  // First collector to find max
                        Collectors.minBy(Integer::compareTo),  // Second collector to find min
                        (max, min) -> new Result(max.orElse(null), min.orElse(null))
                        )
                );


        System.out.println(result); // Prints Result{sum=8, count=1}
    }
}
