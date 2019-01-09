/*

An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node,
you may revisit nodes multiple times, and you may reuse edges.

Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]

 */
package bfsdfs.hard;

import java.util.*;

/**
 * Created by poorvank.b on 21/07/18.
 */
public class ShortestPathVisitingAllNodes {

    private static class Tuple {
        int mask; // Stores the visited nodes in this path
        int head; // Head of current path
        int len; // Length of the path

        Tuple(int mask, int head, int len) {
            this.mask = mask;
            this.head = head;
            this.len = len;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuple)) return false;

            Tuple tuple = (Tuple) o;

            return mask == tuple.mask && head == tuple.head && len == tuple.len;

        }

        @Override
        public int hashCode() {
            int result = mask;
            result = 31 * result + head;
            result = 31 * result + len;
            return result;
        }
    }

    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        Queue<Tuple> queue = new LinkedList<>();
        // Used to avoid visiting duplicate path
        Set<Tuple> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int mask = (1 << i);
            // We don't care about length of path when checking duplicate path
            set.add(new Tuple(mask, i, 0));
            queue.offer(new Tuple(mask, i, 0));
        }

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            if (tuple.mask == (1 << N) - 1) {
                // This path has visited all nodes, and it's guaranteed this is the shortest one by
                // BFS so return it
                return tuple.len;
            } else {
                for (int v : graph[tuple.head]) {
                    int mask = tuple.mask | (1 << v);
                    Tuple t = new Tuple(mask, v, 0);
                    if (set.add(t)) {
                        queue.offer(new Tuple(mask, v, tuple.len + 1));
                    }
                }
            }
        }
        // Should not reach here
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3},{0},{0},{0}};
        System.out.println(new ShortestPathVisitingAllNodes().shortestPathLength(arr));
    }

}

/*

Idea is to use BFS to traverse the graph. Since all edges are weighted 1, we can use a Queue
(instead of a PriorityQueue sorted by cost). Since all edges are weighted 1, then closer nodes will always be evaluated before farther ones.

In order to represent a path, I used a combination of 3 variables:

int bitMask: mask of all the nodes we visited so far: 0 -> not visited, 1 -> visited (Originally this was Set<Integer>of
all nodes we visited so far, but since the Solution TLE and N <= 12, it turns out we can use a bitMask and 32 bits total
in an Integer can cover all the possibilities. This is a small speed up optimization.)
int curr: current node we are on
int cost: the total cost in the path.
Each path taken will have a unique combination of these 3 variables.

We initialize our queue to contain N possible paths each starting from [0,N-1]. This is because we can start at any of N possible Nodes.

At each step, we remove element from the queue and see if we have covered all 12 nodes in our bitMask. If we cover all nodes,
 we return the cost of the path immediately. Since we are using BFS, this is guranteed to be path with the lowest cost.

Otherwise, we get all the neighbors of current node, and for each neighbor, set the Node to visited in bitMask, and then add
 it back into the queue.

In order to prevent duplicate paths from being visited, we use a Set<Tuple> to store the Set<Path> that we have visited before.
 Since we don't really need the cost here, I set cost to 0 for elements stored in Set. You could also set the actual cost value here,
  it wouldn't make a difference :)

  there are 2^n * n possible states. complexity = 2^n * n

There are 2^n * n possible states. time complexity is also n*(2^n), because each node may be visited 2^n times.

 */