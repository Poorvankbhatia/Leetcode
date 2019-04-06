/*

For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a
function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path.
In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

 */
package binarysearch.medium;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> myGraph = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if (n==1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];
        for(int i=0; i<n; i++) {
            myGraph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            myGraph.get(edge[0]).add(edge[1]);
            myGraph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<n; i++)
            if (degree[i]==0)
                return res;
            else if (degree[i]==1) {
                queue.offer(i);
            }

        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int count = queue.size();

            for(int i=0; i<count; i++){
                int curr = queue.remove();
                res.add(curr);
                degree[curr]--;
                for(int k=0; k<myGraph.get(curr).size(); k++) {
                    int next = myGraph.get(curr).get(k);
                    if (degree[next]==0) continue;
                    if (degree[next]==2) {
                        queue.offer(next);
                    }
                    degree[next]--;
                }
            }
        }
        return res;
    }
}

/*
code starts from the leaf nodes.

For leaf nodes, their degree = 1, which means each of them is only connected to one node.

In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees to 0),
and meanwhile we add the new leaf nodes after deleting them(just add their connected nodes with degree as 2) to the queue.

So basically in the end, the nodes in the queue would be connected to no other nodes but each other. They should be the answer.

First let's review some statement for tree in graph theory:

(1) A tree is an undirected graph in which any two vertices are
connected by exactly one path.

(2) Any connected graph who has n nodes with n-1 edges is a tree.

(3) The degree of a vertex of a graph is the number of
edges incident to the vertex.

(4) A leaf is a vertex of degree 1. An internal vertex is a vertex of
degree at least 2.

(5) A path graph is a tree with two or more vertices that is not
branched at all.

(6) A tree is called a rooted tree if one vertex has been designated
the root.

(7) The height of a rooted tree is the number of edges on the longest
downward path between root and a leaf.

OK. Let's stop here and look at our problem.

Our problem want us to find the minimum height trees and return their root labels. First we can think about a simple case -- a path graph.

For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.

Despite its triviality, let design a algorithm to find them.

Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is very easy to get the idea of two pointers.
One from each end and move at the same speed. When they meet or they are one step away, (depends on the parity of n), we have the roots we want.

This gives us a lot of useful ideas to crack our real problem.

For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move
the same speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.

It is easy to see that the last two pointers are from the two ends of the longest path in the graph.

The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner vertexes. Then remove the new
leaves. Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!

The time complexity and space complexity are both O(n).

Note that for a tree we always have V = n, E = n-1.
 */