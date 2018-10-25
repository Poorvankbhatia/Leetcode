/*

In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other
nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one
additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge
connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers,
return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

 */
package bfsdfs.hard;


import java.util.Arrays;

/**
 * Created by poorvank.b on 28/09/17.
 */
public class RedundantConnection2 {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (edge[1] == 0) {
                continue;
            }
            int child = edge[1], father = edge[0];
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edge;
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }

    public int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {4,2},{1,5},{5,2},{5,3},{2,4}
        };
        System.out.println(Arrays.toString(new RedundantConnection2().findRedundantDirectedConnection(a)));
    }

}

/*


There are two cases for the tree structure to be invalid.
1) A node having two parents;
   including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
2) A circle exists
If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents. So my solution works in two steps.

1) Check whether there is a node having two parents.
    If so, store them as candidates A and B, and set the second edge invalid.
2) Perform normal union find.
    If the tree is now valid
           simply return candidate B
    else if candidates not existing
           we find a circle, return current edge;
    else
           remove candidate A instead of B.

4 -> 2 -> 1
          ^
          |
          3

 */
