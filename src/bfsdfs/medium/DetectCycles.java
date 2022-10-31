/*
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell,
you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1)
is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:
 */
package bfsdfs.medium;

import java.util.LinkedList;
import java.util.Queue;

public class DetectCycles {

    int m, n;
    boolean[][] vs;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean containsCycle(char[][] g) {
        m = g.length; n = g[0].length; vs = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vs[i][j] && bfs(g, i, j, -1, -1)) return true;
            }
        }
        return false;
    }

    private boolean bfs(char[][] g, int i, int j, int x, int y) {  // point curr [i, j], point prev [x, y];
        char c = g[i][j];
        Queue<int[]> q = new LinkedList<>();
        vs[i][j] = true;
        q.offer(new int[]{i, j, x, y});
        while (!q.isEmpty()) {
            for (int k = 0, l = q.size(); k < l; k++) {
                int[] curr = q.poll();
                for (int[] d : dir) {
                    int row = curr[0] + d[0], col = curr[1] + d[1];
                    if (row < 0 || row >= m || col < 0 || col >= n || g[row][col] != c) continue;
                    if (row == curr[2] && col == curr[3]) continue;  // a == c
                    if (vs[row][col]) return true;
                    q.offer(new int[]{row, col, curr[0], curr[1]});
                    vs[row][col] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new DetectCycles().containsCycle(new char[][]{{'a','a'},{'b','a'}}));
    }

}
