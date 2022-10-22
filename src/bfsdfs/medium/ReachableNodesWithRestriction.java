/*
There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.

Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.

Note that node 0 will not be a restricted node.


Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
Output: 4
Explanation: The diagram above shows the tree.
We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.

Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
Output: 3
Explanation: The diagram above shows the tree.
We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.


Constraints:

2 <= n <= 105
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
edges represents a valid tree.
1 <= restricted.length < n
1 <= restricted[i] < n
All the values of restricted are unique.
 */
package bfsdfs.medium;

import java.util.*;

public class ReachableNodesWithRestriction {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.putIfAbsent(edge[0],new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.putIfAbsent(edge[1],new ArrayList<>());
            map.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        for (int i : restricted) {
            visited[i] = true;
        }
        return dfs(0,visited,map);
    }

    private int dfs(int n, boolean[] visited, Map<Integer,List<Integer>> map) {
        visited[n] = true;
        int ans = 0;
        if(map.containsKey(n)) {
            for(int next : map.get(n)) {
                if(!visited[next]) {
                    ans += dfs(next,visited,map);
                }
            }
        }
        return ans+1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,1},{0,2},{0,5},
                {0,4},{3,2},{6,5}
        };
        System.out.println(new ReachableNodesWithRestriction().reachableNodes(7,a,new int[]{4,2,1}));
    }

}


/*

BFS:

class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] al = new ArrayList[n];
        for(int i=0;i<n;i++) al[i] = new ArrayList<>();
        for(int e[] : edges){
            al[e[0]].add(e[1]); al[e[1]].add(e[0]);
        }
        Set<Integer> set = new HashSet<>();
        for(int i : restricted) set.add(i);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);  set.add(0);
        int ans = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                ans++;
                int curr = q.remove();
                for(int next : al[curr])
                    if(set.add(next)) q.add(next);
            }
        }
        return ans;
    }
}

 */