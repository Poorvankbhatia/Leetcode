/*
Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] =
[fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable.
It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
Output: [0,3]
Explanation: It's not possible to reach all the nodes from a single vertex.
From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].

Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
Output: [0,2,3]
Explanation: Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them.
Also any of these vertices can reach nodes 1 and 4.


Constraints:

2 <= n <= 10^5
1 <= edges.length <= min(10^5, n * (n - 1) / 2)
edges[i].length == 2
0 <= fromi, toi < n
All pairs (fromi, toi) are distinct.

 */
package graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinVerticesToReachAllNodes {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n];
        for(List<Integer> list : edges) {
            indegree[list.get(1)]++;
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(indegree[i]==0) result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> vertices = new ArrayList<>();
        vertices.add(Arrays.asList(5,4));
        vertices.add(Arrays.asList(5,2));
        vertices.add(Arrays.asList(2,1));
        vertices.add(Arrays.asList(1,0));
        vertices.add(Arrays.asList(0,1));
        vertices.add(Arrays.asList(1,3));
        System.out.println(new MinVerticesToReachAllNodes().findSmallestSetOfVertices(6,vertices));
    }

}

/*

Union find sol:

public static int findParent(int[] parent, int i) {
        if(parent[i] == i) return i;
        return parent[i] = findParent(parent, parent[i]);
    }
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int i = 0; i < edges.size(); i++) {
            List<Integer> curr = edges.get(i);
            int sv = curr.get(0);
            int ev = curr.get(1);
            if(visited[ev]) continue;
            int p1 = findParent(parent, sv);
            int p2 = findParent(parent, ev);
            parent[p2] = p1;
            visited[ev] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) if(parent[i] == i) ans.add(i);
        return ans;
    }

 */
