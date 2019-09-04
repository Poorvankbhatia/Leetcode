/*

There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
(A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
The cost is the sum of the connection costs used. If the task is impossible, return -1.


Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation:
Choosing any 2 edges will connect all cities so we choose the minimum 2.


Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation:
There is no way to connect all cities even if all edges are used.


1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]


 */
package bfsdfs.medium;

import bfsdfs.UnionFind;

import java.util.Arrays;
import java.util.Comparator;

public class ConnectCitiesWithMinimumCost {

    public int minimumCost(int N, int[][] connections) {

        UnionFind unionFind = new UnionFind(N);
        //Prims MST
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        int sum = 0;
        for (int[] connection :  connections ) {
            if(!unionFind.isConnected(connection[0]-1,connection[1]-1)) {
                unionFind.union(connection[0]-1,connection[1]-1);
                sum += connection[2];
            }
        }

        return unionFind.getCount()==1 ? sum : -1;

    }

    public static void main(String[] args) {
        int[][] connections = new int[][] {
                {1,2,3},
                {3,4,4}
        };
        System.out.println(new ConnectCitiesWithMinimumCost().minimumCost(4, connections));
    }

}
