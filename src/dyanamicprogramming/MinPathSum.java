/*

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 11/09/16.
 */
public class MinPathSum {

    public int minPathSum(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        for (int i=1;i<row;i++) {
            grid[i][0] += grid[i-1][0];
        }

        for (int j=1;j<col;j++) {
            grid[0][j] += grid[0][j-1];
        }

        for (int i=1;i<row;i++) {
            for (int j=1;j<col;j++) {
                grid[i][j] += Math.min(grid[i][j-1],grid[i-1][j]);
            }
        }

        return grid[row-1][col-1];

    }

    public static void main(String[] args) {

        int[][] a = new int[][]{{1,2},{3,2,1}};

        System.out.println(new MinPathSum().minPathSum(a));

    }

}
