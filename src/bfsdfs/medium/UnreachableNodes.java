/*
You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.

Input: n = 3, edges = [[0,1],[0,2],[1,2]]
Output: 0
Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.

Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.


Constraints:

1 <= n <= 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated edges.

 */
package bfsdfs.medium;
import bfsdfs.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class UnreachableNodes {

    public long countPairs(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0],edge[1]);
        }
        if(unionFind.getCount()==1) {
            return 0;
        }
        // find count of nodes in each connected component.
        Map<Integer,Integer> map = new HashMap<>();
        for (int a : unionFind.parent) {
            int parent = unionFind.find(a);
            map.put(parent,map.getOrDefault(parent,0)+1);
        }
        long connectedPairs = 0;
        /*
        N points in a graph can max have (n)*(n-1)/2 pairs.
        First point can have n-1 pairs, 2nd n-2, 3rd n-3.. and so on.
        (n-1)+(n-2)+(n-3)... 1 = (n)*(n-1)/2
        */
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            long val = entry.getValue();
            connectedPairs += (val*(val-1)/2);
        }
        long totalPairs = (long) (n) *(n-1)/2;
        return totalPairs-connectedPairs;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {5,0},
                {1,0},
                {10,7},
                {9,8},{7,2},{1,3},
                {0,2},{8,5},{4,6},{4,2}
        };
        System.out.println(new UnreachableNodes().countPairs(11,edges));
    }

}
