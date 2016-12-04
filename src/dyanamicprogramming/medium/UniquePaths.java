/*

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach
the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 11/09/16.
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {

        int[][] grid = new int[m][n];

        grid[0][0] = 1;

        for (int i=1;i<m;i++) {
            grid[i][0] = 1;
        }

        for (int j=1;j<n;j++) {
            grid[0][j] = 1;
        }

        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }

        return grid[m-1][n-1];

    }

}
