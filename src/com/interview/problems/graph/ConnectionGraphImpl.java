package com.interview.problems.graph;

import com.interview.problems.graph.impl.Helper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionGraphImpl implements ConnectionGraph {

    // Base disjoint sets
    private Set<Set<String>> baseSets = new HashSet<>();

    public static void main(String[] args) {
        ConnectionGraphImpl connectionGraph = new ConnectionGraphImpl();
        connectionGraph.apply("1", List.of("2", "3"));
        //System.out.println("Result 1 = " + connectionGraph.getConn());
        connectionGraph.apply("1", List.of("4"));
        //System.out.println("Result 2 = " + connectionGraph.getConn());
        connectionGraph.apply("6", List.of("7"));
        connectionGraph.getConn();
    }

    /**
     * Using "apply" method, you can create inter-connection between existing connections.
     * the connection between the same "from" and "to" should not be possible. e.g:
     *
     * @param from from apex
     * @param to   list of to apexes
     */
    @Override
    public void apply(String from, List<String> to) {
        // Produce newSet
        Set<String> newSet = Stream
                .concat(Stream.of(from), to.stream())
                .collect(Collectors.toSet());
        // Find a list of sets where such elements are already present?
        Set<Set<String>> setsToConnect = find(newSet);
        if (setsToConnect.isEmpty()) {
            // Add a new set to the baseSets
            baseSets.add(newSet);
        } else {
            // Connect new set with target sets
            // Merge sets
            Helper helper = new Helper();
            Set<String> merge = helper.merge(newSet, setsToConnect);
            baseSets.add(merge);
        }
    }

    @Override
    public List<Connection> getConn() {
        Helper helper = new Helper();
        List<Connection> result = new ArrayList<>();
        for (Set<String> set : baseSets) {
            result.addAll(helper.represent(set));
        }
        return helper.displaySorted(result);
    }

    private Set<Set<String>> find(Set<String> newSet) {
        // Find a list of sets where such elements are already present?
        Set<Set<String>> setsToConnect = new HashSet<>();
        for (String next : newSet) {
            // Identify those sets where next element is participating;
            Iterator<Set<String>> iterator = baseSets.iterator();
            while (iterator.hasNext()) {
                Set<String> nextSet = iterator.next();
                if (nextSet.contains(next)) {
                    setsToConnect.add(new HashSet<>(nextSet));
                    iterator.remove();//Immediately remove as it will be replaced with the merged one!
                }
            }
        }
        return setsToConnect;
    }
}
