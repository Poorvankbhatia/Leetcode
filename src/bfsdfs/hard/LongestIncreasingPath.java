/*

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

 */
package bfsdfs.hard;

/**
 * Created by poorvank on 18/11/16.
 */
public class LongestIncreasingPath {
    private int[] XMOVE = {0, 1, -1, 0};
    private int[] YMOVE = {1, 0, 0, -1};

    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int res = 0;
        boolean[][] visited = new boolean[rows][cols];
        int[][] count = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res = Math.max(getMaxPath(i, j, rows, cols, 0, matrix, visited, count), res);
            }
        }
        return res;

    }

    private int getMaxPath(int i, int j, int rows, int cols, int maxValue, int[][] matrix, boolean[][] visited, int[][] count) {
        visited[i][j] = true;

        /*
        If count[i][j] !=0 it means that there is a path starting from it which has been
        already calculated so no need to do repeated computations.
         */
        if (count[i][j] != 0) {
            visited[i][j] = false;
            return maxValue + count[i][j];
        }
        maxValue++;
        int loopMax = 0;
        for (int k = 0; k < 4; k++) {
            int nextX = i + XMOVE[k];
            int nextY = j + YMOVE[k];
            if (isValid(nextX, nextY, rows, cols, visited, matrix, matrix[i][j])) {
                loopMax = Math.max(loopMax, getMaxPath(nextX, nextY, rows, cols, 0, matrix, visited, count));
            }
        }
        maxValue = maxValue + loopMax;
        count[i][j] = maxValue;
        visited[i][j] = false;
        return maxValue;
    }

    private boolean isValid(int x, int y, int rows, int cols, boolean[][] visited, int[][] matrix, int currentValue) {
        return (x >= 0 && y >= 0 && x < rows && y < cols && !visited[x][y] && (matrix[x][y] > currentValue));
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {13, 2, 5, 6},
                {12, 3, 0, 7},
                {11, 10, 9, 8}
        };
        System.out.println(new LongestIncreasingPath().longestIncreasingPath(arr));

    }

}
