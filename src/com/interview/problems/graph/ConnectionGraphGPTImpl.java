package com.interview.problems.graph;

import java.util.*;

public class ConnectionGraphGPTImpl implements ConnectionGraph {
    private final Set<Connection> connections;

    public ConnectionGraphGPTImpl() {
        this.connections = new HashSet<>();
    }

    public static void main(String[] args) {
        ConnectionGraphGPTImpl task = new ConnectionGraphGPTImpl();
        task.apply("1", List.of("2", "3"));
        task.apply("4", List.of("1"));

        for (Connection conn : task.getConn()) {
            System.out.println(conn);
        }
    }

    @Override
    public List<Connection> getConn() {
        //return new ArrayList<>(connections); // Return a copy of the connections
        return connections.stream().sorted((Comparator
                .comparing(Connection::getFrom)
                .thenComparing(Connection::getTo))).toList();
    }

    @Override
    public void apply(String from, List<String> to) {
        // Add new connections only if they don't exist
        for (String target : to) {
            if (!from.equals(target)) {
                addConnectionIfAbsent(from, target);
                addConnectionIfAbsent(target, from); // Ensure bidirectional connection
            }
        }

        // Also ensure all "to" nodes connect with each other
        for (int i = 0; i < to.size(); i++) {
            for (int j = i + 1; j < to.size(); j++) {
                String node1 = to.get(i);
                String node2 = to.get(j);
                addConnectionIfAbsent(node1, node2);
                addConnectionIfAbsent(node2, node1);
            }
        }
    }

    private void addConnectionIfAbsent(String from, String to) {
        Connection connection = new Connection(from, to);
        if (!connections.contains(connection)) {
            connections.add(connection); // Add only if the connection is not already present
        }
    }
}
