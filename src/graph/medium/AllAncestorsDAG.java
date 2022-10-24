/*
You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG).
The nodes are numbered from 0 to n - 1 (inclusive).

You are also given a 2D integer array edges, where edges[i] = [from(i), to(i)] denotes that there is a unidirectional
edge from(i) to(i) in the graph.

Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.

A node u is an ancestor of another node v if u can reach v via a set of edges.

Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
Explanation:
The above diagram represents the input graph.
- Nodes 0, 1, and 2 do not have any ancestors.
- Node 3 has two ancestors 0 and 1.
- Node 4 has two ancestors 0 and 2.
- Node 5 has three ancestors 0, 1, and 3.
- Node 6 has five ancestors 0, 1, 2, 3, and 4.
- Node 7 has four ancestors 0, 1, 2, and 3.

Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
Explanation:
The above diagram represents the input graph.
- Node 0 does not have any ancestor.
- Node 1 has one ancestor 0.
- Node 2 has two ancestors 0 and 1.
- Node 3 has three ancestors 0, 1, and 2.
- Node 4 has four ancestors 0, 1, 2, and 3.


Constraints:

1 <= n <= 1000
0 <= edges.length <= min(2000, n * (n - 1) / 2)
edges[i].length == 2
0 <= fromi, toi <= n - 1
fromi != toi
There are no duplicate edges.
The graph is directed and acyclic.



 */
package graph.medium;

import java.util.*;

public class AllAncestorsDAG {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer,TreeSet<Integer>> map = new HashMap<>();
        int[] indegree = new int[n];
        Map<Integer, List<Integer>> child = new HashMap<>();
        for (int[] edge : edges) {
            child.putIfAbsent(edge[0],new ArrayList<>());
            child.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i]==0) {
                queue.add(i);
                map.put(i,new TreeSet<>());
            }
        }
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if(child.containsKey(poll)) {
                for (int next : child.get(poll)) {
                    if(--indegree[next]==0) {
                        queue.add(next);
                    }
                    map.putIfAbsent(next,new TreeSet<>());
                    // Add direct parent.
                    map.get(next).add(poll);
                    // Add parents of parent
                    map.get(next).addAll(map.get(poll));
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0;i<n;i++) {
            result.add(new ArrayList<>(map.get(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0,3},
                {0,4},
                {1,3},
                {2,4},
                {2,7},
                {3,5},
                {3,6},
                {3,7},
                {4,6}
        };
        System.out.println(new AllAncestorsDAG().getAncestors(8,a));
    }
}

/*
Simple Topological sort

DFS:

public List<List<Integer>> getAncestors(int n, int[][] edges) {
    List<List<Integer>> ans = new ArrayList();
    List<List<Integer>> directChild = new ArrayList();
    for (int i = 0; i < n; i++) {
        ans.add(new ArrayList());
        directChild.add(new ArrayList());
    }
    for (int[] e: edges)
        directChild.get(e[0]).add(e[1]);
    for (int i = 0; i < n; i++)
        dfs(i, i, ans, directChild);
    return ans;
}
private void dfs(int x, int curr, List<List<Integer>> ans, List<List<Integer>> directChild) {
    for (int ch: directChild.get(curr))
        if(ans.get(ch).size() == 0 || ans.get(ch).get(ans.get(ch).size() - 1) != x) {
            ans.get(ch).add(x);
            dfs(x, ch, ans, directChild);
        }
}

 */