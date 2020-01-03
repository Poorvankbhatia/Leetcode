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
    int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m==0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 1;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                max = Math.max(max,dfs(matrix,dp,visited,i,j));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix,int[][] dp,boolean[][] visited,int i,int j) {
        visited[i][j]=true;
        if(dp[i][j]>0) {
            visited[i][j]=false;
            return dp[i][j];
        }
        int loopMax = 0;
        for (int[] ints : dir) {
            int nextX = i + ints[0];
            int nextY = j + ints[1];
            if (nextX >= 0 && nextY >= 0 && nextX < matrix.length && nextY < matrix[0].length && !visited[nextX][nextY] && matrix[nextX][nextY] > matrix[i][j]) {
                loopMax = Math.max(loopMax, dfs(matrix, dp, visited, nextX, nextY));
            }
        }
        dp[i][j]=loopMax+1;
        visited[i][j]=false;
        return dp[i][j];
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
