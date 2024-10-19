/**
 * Type: https://leetcode.com/discuss/general-discussion/1072418/Disjoint-Set-Union-(DSU)Union-Find-A-Complete-Guide
 * More: https://leetcode.com/problem-list/union-find/
 * Task:
 * Suppose you have multiple separate graphs of integers, like [Set.of(1, 2, 3), Set.of(4, 5), Set.of(6, 7), etc.]
 * There can be unlimited number of these graphs, each have unique numbers.
 * Your task is to implement a method:
 * void apply(String from, List<String> to);
 * so that it would connect all featured graphs as follows:
 * 1) connect(1, [4]) would result in [Set.of(1, 2, 3, 4, 5), Set.of(6, 7)]
 * 2) connect(6, [8, 9] would result in [Set.of(1, 2, 3, 4, 5), Set.of(6, 7, 8, 9)]
 * 3) connect(10, [11, 12] would result in [Set.of(1, 2, 3, 4, 5), Set.of(6, 7, 8, 9), Set.of(10, 11, 12)]
 * So, each connect function either creates a new sub-graph or merges existing.
 * connect(1, [4]) would yield the same result as connect(4, [1])
 * Also, I need to implement a method List<Pair<Integer, Integer>/>> getConn(); that would return all connected pairs (all connected combinations)
 * After invocation 1)
 * 1-2
 * 1-3
 * 1-4
 * 1-5
 * 2-1
 * 2-3
 * 2-4
 * 2-5
 * 3-1
 * 3-2
 * 3-4
 * 3-5
 * 4-1
 * 4-2
 * 4-3
 * 4-5
 * 5-1
 * 5-2
 * 5-3
 * 5-4
 * and
 * 6-7
 * 7-6
 *
 *  *After invocation 2)
 *  1-2
 *  1-3
 *  1-4
 *  1-5
 *  2-1
 *  2-3
 *  2-4
 *  2-5
 *  3-1
 *  3-2
 *  3-4
 *  3-5
 *  4-1
 *  4-2
 *  4-3
 *  4-5
 *  5-1
 *  5-2
 *  5-3
 *  5-4
 *  and
 *  6-7
 *  6-8
 *  6-9
 *  7-6
 *  7-8
 *  7-9
 *  8-6
 *  8-7
 *  8-9
 *  9-8
 *
 */
package com.interview.problems.graph;