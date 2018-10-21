/*

In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 1.


Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.


 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

import java.util.HashMap;

/**
 * Created by poorvank.b on 02/05/18.
 */
public class MakingLargeIsland {


    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m*n);
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir : dirs) {
                        res = Math.max(res, connect(i, j, i + dir[0], j + dir[1], grid, uf, m, n));
                    }
                }
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res = Math.max(res, check(i, j, uf, m, n));
                }
            }
        }
        return res;
    }

    private int connect(int a, int b, int i, int j, int[][] grid, UnionFind uf, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return 0;
        }
        return uf.union(a * n + b, i * n + j);
    }

    public int check(int i, int j, UnionFind uf, int m, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x < 0 || y < 0 || x >= m || y >= n) {
                continue;
            }
            int r = uf.find(x * n + y);
            map.put(r, uf.size[r]);
        }
        int res = 1;
        for (int k : map.keySet()){
            res += map.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1}
        };
        System.out.println(new MakingLargeIsland().largestIsland(grid));
    }

}

/*


scan the grid, union the island together, record the max area
scan again, check every 0, sum up the areas around and update max
n is size of grid, Space complexity O(n), Time(nlogn).
union find with rank needs logn time to union

 */