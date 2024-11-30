package com.interview.stream;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * toMap collector
 * The Collector.toMap pattern is part of the Java Streams API, specifically designed to collect data from a stream into a Map.
 * It is provided by the Collectors utility class and allows you to transform elements from a stream into key-value pairs in a Map.
 * Collectors.toMap(Function<? super T, ? extends K> keyMapper,
 *                  Function<? super T, ? extends U> valueMapper,
 *                  BinaryOperator<U> mergeFunction)
 */
public class ToMapCollector {

    private static class User {
        private String id;
        private String name;
        private String email;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public User(String id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        new ToMapCollector().task();
    }

    /**
     *Let’s assume we have a list of Person objects where each person has a name and an age,
     * and we want to create a map where the key is the person's name and the value is their age.
     */
    private void call() {
        List<User> users = List.of(
                new User("1", "Alice", "alice@example.com"),
                new User("2", "Bob", "bob@example.com"),
                new User("3", "Charlie", "charlie@example.com")
        );

        // Create a map where the key is the user ID and the value is the User object
        Map<String, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        // Print the grouped result
        userIdToUserMap.forEach((id, user) ->
                System.out.println("ID " + id + ": " + " user "+ user));
    }

    private void call2() {
        List<User> users = List.of(
                new User("1", "Alice", "alice@example.com"),
                new User("2", "Bob", "bob@example.com"),
                new User("3", "Charlie", "charlie@example.com")
        );

        // Create a map where the key is the user ID and the value is the email
        Map<String, String> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, User::getEmail));
        // Print the grouped result
        userIdToUserMap.forEach((id, user) ->
                System.out.println("ID " + id + ": " + " user "+ user));
    }

    /**
     * There is a multimap
     * 1 -> [0, 1, 2]
     * 2 -> [3, 4]
     *
     * Transform into
     * Map<Long, Integer>, like:
     * [0->1], [1->1], [2->1], [3->2], [4->2]
     */
    private void task() {
        // Map with lists as values
        Map<Integer, List<Long>> multimap = Map.of(
                1, List.of(0L, 1L, 2L),
                2, List.of(3L, 4L)
        );

        // Create the resulting map
        Map<Long, Integer> transformedMap = new HashMap<>();

        // Iterate over the multimap
        multimap.forEach((key, values) -> {
            // For each value in the list, put it into the new map
            values.forEach(value -> transformedMap.put(value, key));
        });

        System.out.println("transformedMap: " + transformedMap);

        // Flattening the map values into a single list
        var newMap = multimap.entrySet().stream().flatMap((e)-> {
            Integer key = e.getKey();
            List<Long> value = e.getValue();
            List<Map.Entry<Long, Integer>> collect =
                    value.stream().map(v -> Map.entry(v, key)).collect(Collectors.toList());
            return collect.stream();
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("New Map: " + newMap);
    }

    private void task2() {
        // Map with lists as values
        Map<Integer, List<Long>> multimap = Map.of(
                1, List.of(0L, 1L, 2L),
                2, List.of(3L, 4L)
        );

        // Directly mapping the key-value pairs without using Map.Entry
        var newMap = multimap.entrySet().stream()
                .flatMap(e -> e.getValue().stream().map(v -> new Object[]{v, e.getKey()}))
                .collect(Collectors.toMap(array -> (Long) array[0], array -> (Integer) array[1]));

        System.out.println("New Map: " + newMap);
    }


}
