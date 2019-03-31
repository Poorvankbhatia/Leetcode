/*

Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.



Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation:
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
Example 2:

Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation:
All 1s are either on the boundary or can reach the boundary.


Note:

1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
All rows have the same size.

 */
package bfsdfs.medium;

public class NumberOfEnclaves {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int numEnclaves(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (i == 0 || i == m-1 || j == 0 || j == n-1) {
                    if (A[i][j] == 1) {
                        dfs(A, m, n, i, j);
                    }
                }
            }
        }
        int count = 0;
        for (int[] ints : A) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) count++;
            }

        }
        return count;
    }
    public void dfs(int[][] A, int m, int n, int x, int y) {
        if (x >= m || x < 0 || y >= n || y < 0 || A[x][y] == 0) return;
        A[x][y] = 0;
        for (int[] d : dirs)
            dfs(A, m, n, x+d[0], y+d[1]);
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0,0,0,0},
                {0,1,0,0},
                {1,0,1,0},
                {0,0,0,1},
        };
        System.out.println(new NumberOfEnclaves().numEnclaves(A));
    }

}
