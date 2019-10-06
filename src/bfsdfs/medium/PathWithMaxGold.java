/*

In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


Constraints:

1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;


public class PathWithMaxGold {

    int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};

    public int getMaximumGold(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        List<int[]> list = new ArrayList<>();

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j] != 0) {
                    list.add(new int[]{i,j});
                }
            }
        }

        int max = 0;
        for (int[] v : list) {
            boolean[][] visited = new boolean[m][n];
            max = Math.max(dfs(v[0],v[1],grid,m,n,visited), max);
        }

        return max;
    }

    private int dfs(int x, int y, int[][] grid, int m, int n,boolean[][] visited) {

        int val =0;

        visited[x][y] = true;
        val += grid[x][y];
        int loopMax = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            int max = 0;
            if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && !visited[nextX][nextY] && grid[nextX][nextY] != 0) {
                max = dfs(nextX,nextY,grid,m,n,visited);
            }
            loopMax = Math.max(max,loopMax);
        }
        visited[x][y] = false;
        return val+loopMax;

    }


    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0 ,0 ,0 ,0 ,0 ,0 ,32,0 ,0,20},
                {0 ,0 ,2 ,0 ,0 ,0 ,0 ,40,0,32},
                {13,20,36,0 ,0 ,0 ,20,0 ,0,0},
                {0 ,31,27,0 ,19,0 ,0 ,25,18,0},
                {0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0,0},
                {0 ,0 ,0 ,0 ,0 ,0 ,0 ,18,0,6},
                {0 ,0 ,0 ,25,0 ,0 ,0 ,0 ,0,0},
                {0 ,0 ,0 ,21,0 ,30,0 ,0 ,0,0},
                {19,10,0 ,0 ,34,0 ,2 ,0 ,0,27},
                {0 ,0 ,0 ,0 ,0 ,34,0 ,0 ,0,0}
        };
        System.out.println(new PathWithMaxGold().getMaximumGold(a));
    }

}
