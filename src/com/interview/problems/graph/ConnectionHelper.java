package com.interview.problems.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Display result in form of a sorted list on all possible interconnections
 */
public class ConnectionHelper {

    // The total complexity of the merge method is:
    //O(n+m×k), where n - size of newSet, m - size of targetSets, k - is the average size of each set within targetSets.
    public Set<String> merge(Set<String> newSet, Set<Set<String>> targetSets) {
        //System.out.println("Merging new set = "+newSet+" target = "+targetSets);
        return Stream
                .concat(newSet.stream(), targetSets.stream().flatMap(Set::stream))
                .collect(Collectors.toSet());
    }

    public List<Connection> represent(Set<String> set) {
        if (set==null || set.isEmpty()) return Collections.emptyList();
        List<Connection> result = new ArrayList<>();
        for (String i : set) {
            for (String j : set) {
                if (!i.equals(j)) {
                    result.add(new Connection(i, j));
                }
            }
        }
        return result;
    }

    public List<Connection> displaySorted(List<Connection> connections) {
        return connections.stream().sorted((Comparator
                .comparing(Connection::getFrom)
                        .thenComparing(Connection::getTo)))
                .peek(item-> System.out.println(item))
                .toList();
    }
}
