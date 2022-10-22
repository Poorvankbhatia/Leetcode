/*
You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from
node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers node1 and node2.

Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance
from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node
with the smallest index, and if no possible answer exists, return -1.

Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance
than 1, so we return node 2.


Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance
than 2, so we return node 2.


Constraints:

n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i
0 <= node1, node2 < n

Note that edges may contain cycles.
 */
package bfsdfs.medium;

import java.util.*;

public class ClosestNode {

    public int closestMeetingNode(int[] edges, int node1, int node2) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if(edges[i]!=-1) {
                map.put(i,edges[i]);
            }
        }
        boolean[] visited1 = new boolean[edges.length];
        int[] distance1 = new int[edges.length];
        bfs(map,node1,visited1,distance1,node2);
        boolean[] visited2 = new boolean[edges.length];
        int[] distance2 = new int[edges.length];
        bfs(map,node2,visited2,distance2,node1);
        int min = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < distance1.length; i++) {
            if(visited1[i] && visited2[i]) {
                if(min>Math.max(distance1[i],distance2[i])) {
                    min = Math.max(distance1[i],distance2[i]);
                    ans = i;
                }

            }
        }
        return ans;
    }

    private void  bfs(Map<Integer,Integer> map,int node,boolean[] visited,int[] distance, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        distance[node]=0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if(poll==end) {
                break;
            }
            if(map.containsKey(poll)) {
                int next  = map.get(poll);
                if(!visited[next]) {
                    visited[next] = true;
                    distance[next] += distance[poll]+1;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ClosestNode().closestMeetingNode(new int[]{2,0,-1,-1,-1,1,1,4,1},6,8));
    }

}

/*

Code:

public int closestMeetingNode(int[] edges, int node1, int node2) {

    HashMap<Integer, Integer> vis = new HashMap<>();
    for (int i=node1, d=0; i>=0 && !vis.containsKey(i); i=edges[i])
        vis.put(i, d++);

    HashSet<Integer> vis2 = new HashSet<>();
    int res=-1, dist = 0, min=Integer.MAX_VALUE;

    for(int i=node2; i>=0 && !vis2.contains(i); i=edges[i]){
        if(vis.containsKey(i)){
            int curr = Math.max(dist, vis.get(i));
            if(res==-1 || curr<=min){
                res = curr==min ? Math.min(res, i) : i;
                min = curr;
            }
        }
        dist++; vis2.add(i);
    }
    return res;
}
Logic:

use dfs for node 1, and make a map for <node, distance>
now use dfs for node 2, check if our map contains a value for that node
if yes then check if the distance/index is minimum

 */