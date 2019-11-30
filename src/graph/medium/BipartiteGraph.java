/*

Given a graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such
that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i,
 and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent ubsets.


Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.

 */
package graph.medium;

import java.util.*;

/**
 * Created by poorvank.b on 21/02/18.
 */
public class BipartiteGraph {

    public boolean isBipartite(int[][] graph) {

        if(graph==null || graph.length==0) {
            return true;
        }
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i=0;i<graph.length;i++) {
            map.putIfAbsent(i, new ArrayList<>());
            for (int a : graph[i]) {
                map.get(i).add(a);
            }
        }
        boolean[] visited = new boolean[graph.length];
        boolean[] color = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        // Traversing on every node because the graph can be a forest too(i.e a disconnected graph)
        for (int i=0;i<graph.length;i++) {
            if(!visited[i]) {
                queue.add(i);
                visited[i]=true;
                color[i]=true;
                while (!queue.isEmpty()) {
                    int poll = queue.poll();
                    for (int n : map.get(poll)) {
                        if(!visited[n]) {
                            color[n]=!color[poll];
                            visited[n]=true;
                            queue.add(n);
                        } else if(color[n]==color[poll]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,2,3},
                {0,2},
                {0,1,3},
                {0,2}
        };
        System.out.println(new BipartiteGraph().isBipartite(arr));
    }

}

/*

Better Approach:

public boolean isBipartite(int[][] graph) {
        //BFS
        // 0(not meet), 1(black), 2(white)
        int[] visited = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length != 0 && visited[i] == 0) {
                visited[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while(! q.isEmpty()) {
                    int current = q.poll();
                    for (int c: graph[current]) {

                        if (visited[c] == 0) {
                            visited[c] = (visited[current] == 1) ? 2 : 1;
                            q.offer(c);
                        } else {
                            if (visited[c] == visited[current]) return false;
                        }
                    }
                }

            }
        }

        return true;
    }

 */