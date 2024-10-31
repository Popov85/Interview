package com.interview.problems.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Solution alg:
 * 1) Maintain an internal structure of set of disjoint sets to keep track of all existing disjoint sets in the system;
 * Set<Set<String>> baseSet;
 * 2) Treat input as essentially set of elements that should be interconnected at first invocation;
 * 3) At second invocation, given that system already has some disjoint sets, we need to find in which sets my new set element participate;
 * 4) I repeat this procedure for each new element in new set;
 * 5) If sets to be merged are not found - simply add a new set to the internal collection;
 * 6) If sets to be merged are found, say 1 or 2 sets in Set<Set<String>>, remember them to be merged with my new set;
 * 6.1) Old sets simply remove.
 * 7. List<Connection> will be built based on internal Set<Set<String>> each time when requested; (computed lazily)
 * Total complexity: O(n×m)+O(n+m×k) = O(n×m+m×k), where n - size of new set, m - quantity of existing disjoint sets; k - average size of each of disjoint sets;
 *
 */
public class ConnectionGraphImpl implements ConnectionGraph {

    private final ConnectionHelper helper = new ConnectionHelper();

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
            ConnectionHelper helper = new ConnectionHelper();
            Set<String> merge = helper.merge(newSet, setsToConnect);
            baseSets.add(merge);
        }
    }

    @Override
    public List<Connection> getConn() {
        List<Connection> result = new ArrayList<>();
        for (Set<String> set : baseSets) {
            result.addAll(helper.represent(set));
        }
        return helper.displaySorted(result);
    }

    // The total time complexity is therefore: O(n×m)
    private Set<Set<String>> find(Set<String> newSet) {
        // Find a list of sets where such elements are already present?
        Set<Set<String>> setsToConnect = new HashSet<>();
        for (String next : newSet) {//O(n) - n - number of new elements
            // Identify those sets where next element is participating;
            Iterator<Set<String>> iterator = baseSets.iterator();
            while (iterator.hasNext()) {// O(m), m - number of sets
                Set<String> nextSet = iterator.next();
                if (nextSet.contains(next)) {
                    setsToConnect.add(new HashSet<>(nextSet));
                    iterator.remove();//Immediately remove as it will be replaced with the merged one!
                    break; // Element can participate in at most 1 set! So, when found immediately return;
                }
            }
        }
        return setsToConnect;
    }
}
