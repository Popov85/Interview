package com.interview.problems.graph;

import java.util.List;

public interface ConnectionGraph {

    List<Connection> getConn();
    void apply(String from, List<String> to);

}
