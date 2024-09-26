package com.interview.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * flatMap()
 * It’s used to flatten a stream of collections (like lists) into a single stream, allowing operations on all elements as if they were in a single collection.
 *  Use Case:
 *   - Commonly used when dealing with nested collections (like List<List<T>>, Map<K, List<V>>, or other complex data structures).
 */
public class Flattening {

    public static void main(String[] args) {
        new Flattening().call();
    }


    private void call() {
        // List of lists (nested collections)
        List<List<Integer>> nestedList = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8, 9)
        );

        // Using flatMap to flatten the nested list into a single list
        List<Integer> flatList = nestedList.stream()
                .flatMap(list -> list.stream()) // explicitly shows that each list is being converted to a stream. The method reference (List::stream) can be more concise but might be less intuitive for newcomers.
                .collect(Collectors.toList());

        System.out.println("Flattened List: " + flatList);
    }

    private void call2() {
        // Map with lists as values
        Map<String, List<Integer>> mapOfLists = Map.of(
                "group1", List.of(1, 2, 3),
                "group2", List.of(4, 5),
                "group3", List.of(6, 7, 8, 9)
        );


        // Flattening the map values into a single list
        List<Integer> flatList = mapOfLists.values().stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println("Flattened List from Map: " + flatList);
    }

}
