/*

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which
turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of
islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
Example:
Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
Challenge:
Can you do it in time complexity O(k log mn), where k is the length of the positions?

 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 10/06/17.
 */
public class NumberOfIslands2 {

    private int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        int noOfIslands = 0;
        List<Integer> result = new ArrayList<>();

        if(positions==null || positions.length==0) {
            return result;
        }

        int[] size = new int[m*n];
        int[] parent = new int[m*n];

        UnionFind unionFind = new UnionFind(n*m,size,parent);

        for (int[] p : positions) {
            noOfIslands++;
            int x = p[0];
            int y = p[1];
            int index = x*n+y;
            parent[index] = index;
            size[index] = 1;
            for(int[] d: direction){
                int i = x + d[0];
                int j = y + d[1];
                int newIndex = i * n + j;
                if(i >= 0 && i < m && j >= 0 && j < n && unionFind.getSize(newIndex) != 0) {
                    if(!unionFind.isConnected(index, newIndex)) {
                        unionFind.union(index, newIndex);
                        noOfIslands--;
                    }
                }
            }
            result.add(noOfIslands);
        }

        unionFind.print();

        return result;

    }

    public static void main(String[] args) {
        int m = 3,n=3;
        int[][] positions = new int[][]{{0,0}, {0,1}, {1,0}, {2,1}};
        System.out.println(new NumberOfIslands2().numIslands2(m,n,positions));

    }

}

/*

It is important to note that, when we want to find the number of islands only once, at a specific time, using DFS will be faster.

However, the technique shown in this article is great when the 2D matrix is modified during the runtime, i.e. to find incremental connected
components.

For example, each time we add a new cross somewhere in the 2D matrix, we could call "union" on the corresponding index and its eight neighbors'
index. When we want to find the number of islands, we would then just have to note down the frequency of each set, as its done here.


Time complexity : Klog(mn)


Create parent[mn+1] and reserve parent[0] to indicate whether a cell is water.
Since the init value of new int[m * n + 1] is 0, you can avoid the array initialization of complexity O(mn).
you can achieve O(klog(mn)).

 */

/*
 G I
 */