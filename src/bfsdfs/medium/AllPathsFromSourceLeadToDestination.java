/*

Given the edges of a directed graph, and two nodes source and destination of this graph,
determine whether or not all paths starting from source eventually end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.


Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
Output: true

Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
Output: false
Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.


Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
Output: false
Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths,
such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.


Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
Output: false
Explanation: There is infinite self-loop at destination node.

 */
package bfsdfs.medium;

import java.util.*;

public class AllPathsFromSourceLeadToDestination {

    private Map<Integer, List<Integer>> connectionMap;
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {

        connectionMap = new HashMap<>();
        for (int[] edge : edges) {
            connectionMap.putIfAbsent(edge[0],new ArrayList<>());
            connectionMap.get(edge[0]).add(edge[1]);
        }

        if(connectionMap.containsKey(destination)) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        return dfs(source,destination,visited);
    }

    private boolean dfs(int source,int destination,Set<Integer> visited) {
        visited.add(source);
        if(connectionMap.containsKey(source)) {
            for (int next : connectionMap.get(source)) {
                if(visited.contains(next)) {
                   return false;
                }
                boolean result = dfs(next, destination, new HashSet<>(visited));
                if(!result) {
                    return false;
                }
            }
        } else {
            return source == destination;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {0,1},
                {0,2},
                {1,3},
                {1,2},
                {2,3}
        };
        System.out.println(new AllPathsFromSourceLeadToDestination().leadsToDestination(4,edges,0,3));
    }

}

/*
Another sol:

class Solution {
    enum State { PROCESSING, PROCESSED }

    public boolean leadsToDestination(int n, int[][] edges, int src, int dest) {
        List<Integer>[] graph = buildDigraph(n, edges);
        return leadsToDest(graph, src, dest, new State[n]);
    }

    private boolean leadsToDest(List<Integer>[] graph, int node, int dest, State[] states) {
        if (states[node] != null) return states[node] == State.PROCESSED;
        if (graph[node].isEmpty()) return node == dest;
        states[node] = State.PROCESSING;
        for (int next : graph[node]) {
            if (!leadsToDest(graph, next, dest, states)) return false;
        }
        states[node] = State.PROCESSED;
        return true;
    }

    private List<Integer>[] buildDigraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        return graph;
    }
}
 */