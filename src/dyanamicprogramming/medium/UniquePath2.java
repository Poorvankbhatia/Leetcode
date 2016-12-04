/*

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 11/09/16.
 */
public class UniquePath2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] grid = new int[row][col];


        for (int i=0;i<row;i++) {
            if(obstacleGrid[i][0]==1) {
                break;
            }
            grid[i][0] = 1;
        }

        for (int j=0;j<col;j++) {
            if(obstacleGrid[0][j]==1) {
                break;
            }
            grid[0][j] = 1;
        }

        for (int i=1;i<row;i++) {
            for (int j=1;j<col;j++) {
                if(obstacleGrid[i-1][j]!=1 && obstacleGrid[i][j]!=1) {
                    grid[i][j] += grid[i-1][j];
                }
                if(obstacleGrid[i][j-1]!=1 && obstacleGrid[i][j]!=1) {
                    grid[i][j] += grid[i][j-1];
                }
            }
        }

        return grid[row-1][col-1];
    }

    public static void main(String[] args) {

        int[][] a = new int[][]{{0}};

        System.out.println(new UniquePath2().uniquePathsWithObstacles(a));

    }

}
