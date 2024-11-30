package com.interview.problems.graph.dsuimpl;

import com.interview.problems.graph.Connection;
import com.interview.problems.graph.ConnectionGraph;
import com.interview.problems.graph.ConnectionHelper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnionFindWithSets implements ConnectionGraph {

    private int[] parent;

    private int[] rank;

    private Map<Integer, Set<Integer>> setsMap;

    public Map<Integer, Set<Integer>> getSetsMap() {
        return setsMap;
    }

    private final ConnectionHelper helper = new ConnectionHelper();

    public UnionFindWithSets(int n) {
        parent = new int[n];
        rank = new int[n];
        setsMap = new HashMap<>();

        // Initialize each element as its own set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            setsMap.put(i, new HashSet<>(Arrays.asList(i))); // Each element in its own set
        }
    }

    public static void main(String[] args) {
        UnionFindWithSets uf = new UnionFindWithSets(7);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(5, 6);

        uf.getConn();
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union with rank and set merging
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                mergeSets(rootX, rootY);
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                mergeSets(rootY, rootX);
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
                mergeSets(rootX, rootY);
            }
        }
    }

    // Merge sets of two roots and update the setsMap
    private void mergeSets(int rootX, int rootY) {
        // Get the sets for each root
        Set<Integer> setX = setsMap.get(rootX);
        Set<Integer> setY = setsMap.get(rootY);

        // Merge the smaller set into the larger set to save space
        if (setX.size() < setY.size()) {
            setY.addAll(setX);
            setsMap.put(rootY, setY);
            setsMap.remove(rootX);
        } else {
            setX.addAll(setY);
            setsMap.put(rootX, setX);
            setsMap.remove(rootY);
        }
    }

    // Get the set for a given element
    public Set<Integer> getSet(int x) {
        int root = find(x);
        return setsMap.get(root);
    }

    @Override
    public void apply(String from, List<String> to) {
        // Produce newSet
        Set<Integer> newSet = Stream
                .concat(Stream.of(from), to.stream())
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        for (Integer next : newSet) {
            // Find where it is
            int i = find(next);

        }
        
        // TODO!

    }

    @Override
    public List<Connection> getConn() {
        // Print individual sets
        List<Connection> result = new ArrayList<>();
        for (Set<Integer> set : getSetsMap().values()) {
            result.addAll(helper.represent(set));
        }
        return helper.displaySorted(result);
    }


}
