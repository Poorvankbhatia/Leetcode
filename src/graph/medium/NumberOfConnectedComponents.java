/*

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 write a function to find the number of connected components in an undirected graph.

Example 1:
0 3
| |
1 --- 2 4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 */
package graph.medium;

/**
 * Created by poorvank.b on 26/02/18.
 */
public class NumberOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {

        int count = n;
        int[] parent = new int[n];

        for (int i=0;i<n;i++) {
            parent[i]=i;
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            int p1 = getParent(parent,start);
            int p2 = getParent(parent,end);

            if(p1!=p2) {
                count--;
                parent[p1]=p2;
            }

        }

        return count;

    }

    private int getParent(int[] parent, int i) {
        while (i!=parent[i]) {
            parent[i]=parent[parent[i]];
            i=parent[i];
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,1},
                {1,2},
                {3,4}
        };

        System.out.println(new NumberOfConnectedComponents().countComponents(5,arr));
    }

}

/*

There are k loops and each loop processing the root array costs log(n). Therefore, time complexity is O(k*log(n)).

 */