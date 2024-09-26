package com.interview.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;


/**
 * GroupingBy collector
 * In Java streams, the groupingBy pattern is used to group elements of a stream by a certain criterion, producing a Map where the keys represent the criterion and the values are lists of items that fit that criterion.
 * The Collectors.groupingBy() method is typically used for this purpose. It takes a classifier function that determines the grouping criterion and returns a Collector that collects the elements of the stream into a Map.
 */
public class GroupingByCollector {

    private static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void main(String[] args) {
        new GroupingByCollector().call();
    }


    /**
     * Example: Grouping a list of people by their age
     */
    private void call() {
        List<Person> people = List.of(
                new Person("Alice", 23),
                new Person("Bob", 23),
                new Person("Charlie", 24),
                new Person("David", 24),
                new Person("Eve", 25)
        );

        // Group people by age
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(person -> person.age));

        // Print the grouped result
        peopleByAge.forEach((age, persons) ->
                System.out.println("Age " + age + ": " + persons));
    }

    /**
     * Grouping with Counting (2 arguments):
     */
    private void call2() {
        List<Person> people = List.of(
                new Person("Alice", 23),
                new Person("Bob", 23),
                new Person("Charlie", 24),
                new Person("David", 24),
                new Person("Eve", 25)
        );

        // Group people by age and count the number of people in each age group
        Map<Integer, Long> peopleCountByAge = people.stream()
                .collect(Collectors.groupingBy(person -> person.age, Collectors.counting()));

        // Print the grouped result
        peopleCountByAge.forEach((age, count) ->
                System.out.println("Age " + age + ": " + count + " people"));
    }
}
