/*

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel
between two different cities (this network form a tree). Last year, The ministry of transport decided to
orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0


Constraints:

2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi



 */
package bfsdfs.medium;

import java.util.*;

public class ReorderRoutes {

    private int count = 0;

    public int minReorder(int n, int[][] connections) {
        int start =0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer>[] directedEdges = new Set[n];
        for (int[] connection : connections) {
            map.putIfAbsent(connection[0],new ArrayList<>());
            map.putIfAbsent(connection[1],new ArrayList<>());
            map.get(connection[0]).add(connection[1]);
            map.get(connection[1]).add(connection[0]);
            if(directedEdges[connection[0]]==null) directedEdges[connection[0]] = new HashSet<>();
            directedEdges[connection[0]].add(connection[1]);
        }

        dfs(start,map,new boolean[n],directedEdges);
        return count;
    }

    private void dfs(int start, Map<Integer, List<Integer>> map, boolean[] visited,Set<Integer>[] directedEdges) {
        visited[start] = true;
        for (int next : map.get(start)) {
            if(!visited[next]) {
                if(directedEdges[start]!=null && directedEdges[start].contains(next)) {
                    count++;
                }
                dfs(next,map,visited, directedEdges);
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,1},{1,3},{2,3},{4,0},{4,5}
        };
        System.out.println(new ReorderRoutes().minReorder(6,a));
    }

}

/**
 Since there is only one way to travel and graph will
 have n-1 connections and we will always be able to reorder
 so that every city can reach 0, we can conclude that no cycles will exist.

 n-1 edges with every city reachable will never cause a cycle.

 So Treat the graph as undirected.
 Start a dfs from the root, if you come across an edge in the forward direction, you need to reverse the edge

 */