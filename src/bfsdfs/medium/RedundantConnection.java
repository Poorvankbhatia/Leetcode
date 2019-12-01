/*
We are given a "tree" in the form of a 2D-array, with distinct values for each node.

In the given 2D-array, each element pair [u, v] represents that v is a child of u in the tree.

We can remove exactly one redundant pair in this "tree" to make the result a tree.

You need to find and output such a pair. If there are multiple answers for this question,
output the one appearing last in the 2D-array. There is always at least one answer.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: Original tree will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [1,3], [3,1]]
Output: [3,1]
Explanation: Original tree will be like this:
  1
 / \\
2   3
Note:
The size of the input 2D-array will be between 1 and 1000.
Every integer represented in the 2D-array will be between 1 and 2000.
 */
package bfsdfs.medium;

import bfsdfs.UnionFind;

import java.util.*;

/**
 * Created by poorvank.b on 24/09/17.
 */
public class RedundantConnection {

    int[] parent;
    int[] size;
    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[1001];
        size = new int[1001];
        for(int i=0;i<1001;i++) {
            parent[i]=i;
            size[i]=1;
        }
        for(int[] edge : edges) {
            if(getParent(edge[0])==getParent(edge[1])) {
                return edge;
            }
            union(edge[0],edge[1]);
        }
        return new int[0];
    }
    private int getParent(int x) {
        while(x!=parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    private void union(int a,int b) {
        int pA = getParent(a);
        int pB = getParent(b);
        if(pA!=pB) {
            if(size[pA]>size[pB]) {
                parent[pB]=parent[pA];
                size[pA]+=size[pB];
            } else {
                parent[pA]=parent[pB];
                size[pB]+=size[pA];
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{5,2}, {3,4}, {4,3}, {5,3}, {1,3}};
        System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(arr)));
    }

}
