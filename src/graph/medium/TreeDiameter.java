/*
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
Each node has labels in the set {0, 1, ..., edges.length}.

Input: edges = [[0,1],[0,2]]
Output: 2
Explanation:
A longest path of the tree is the path 1 - 0 - 2.


Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation:
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.


Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.

 */
package graph.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeDiameter {

    private class Node {
        int value;
        int distance;

        public Node(int node, int distance) {
            this.value = node;
            this.distance = distance;
        }
    }

    public int treeDiameter(int[][] edges) {
        int n = edges.length+1;

        LinkedList<Integer>[] adjacencyList = new LinkedList[n];
        for(int i = 0; i < n; ++i) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }

        Node start = bfs(0,n,adjacencyList);
        return bfs(start.value,n,adjacencyList).distance;

    }

    private Node bfs(int u, int n, LinkedList<Integer>[] adj) {
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        distance[u] = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for(int i = 0; i < adj[poll].size(); ++i) {
                int v = adj[poll].get(i);
                if(distance[v] == -1) {
                    queue.add(v);
                    distance[v] = distance[poll] + 1;
                }
            }
        }

        int maxDistance = 0;
        int val = 0;
        for(int i = 0; i < n; ++i) {
            if(distance[i] > maxDistance) {
                maxDistance = distance[i];
                val = i;
            }
        }
        return new Node(val,maxDistance);
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0,1},
                {1,2},
                {2,3},
                {1,4},
                {4,5}
        };
        System.out.println(new TreeDiameter().treeDiameter(edges));
    }

}

/*

We can find longest path using two BFSs.
If we start BFS from any node 'N' and find a node with the longest distance from 'N',
it must be an end point of the longest path.
It can be proved using contradiction.
So the algorithm reduces to simple two BFSs.
First BFS to find an end point of the longest path and second BFS from this end point to find the actual longest path.

Proof: https://stackoverflow.com/questions/20010472/proof-of-correctness-algorithm-for-diameter-of-a-tree-in-graph-theory

 */