/*

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

 */

package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 17/10/16.
 */
public class WaterFlow {

    private class Reachable {
        private boolean reachAtlantic;
        private boolean reachPacific;

        public Reachable(boolean reachAtlantic, boolean reachPacific) {
            this.reachAtlantic = reachAtlantic;
            this.reachPacific = reachPacific;
        }

        public boolean isReachable() {
            return reachAtlantic && reachPacific;
        }
    }

    private int[] xMove = new int[]{0, 1, -1, 0};
    private int[] yMove = new int[]{1, 0, 0, -1};

    public List<int[]> pacificAtlantic(int[][] matrix) {

        List<int[]> list = new ArrayList<>();

        if(matrix.length==0) {
            return list;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        Reachable[][] reachable = new Reachable[row][col];
        boolean[][] visited = new boolean[row][col];



        for (int i = 0; i < row; i++) {
            for (int j = col - 1; j >= 0; j--) {
                reachable[i][j] = new Reachable(false, false);
                reachable[i][j].reachAtlantic = reachAtlantic(matrix, visited, i, j, row, col, reachable);
                reachable[i][j].reachPacific = reachPacific(matrix, visited, i, j, row, col, reachable);
                if (reachable[i][j].isReachable()) {
                    int[] arr = new int[]{i, j};
                    list.add(arr);
                }
            }

        }

        /*for (int[] arr : children) {
            System.out.print(Arrays.toString(arr) +  " ");
        }*/

        return list;


    }


    private boolean reachAtlantic(int[][] matrix, boolean[][] visited, int x, int y, int row, int col, Reachable[][] reachable) {

        if (reachable[x][y]!=null && (reachable[x][y]).reachAtlantic) {
            return true;
        }

        if (x == row - 1 || y == col - 1) {
            return true;
        }

        /*
        So that the marked array is not visited again
         */
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {

            int newX = x + xMove[i];
            int newY = y + yMove[i];

            if (isSafe(row, col, newX, newY, matrix[x][y], matrix,visited)) {
                if (reachAtlantic(matrix, visited, newX, newY, row, col, reachable)) {
                    visited[x][y] = false;
                    return true;
                }
            }

        }

        visited[x][y] = false;
        return false;

    }

    private boolean reachPacific(int[][] matrix, boolean[][] visited, int x, int y, int row, int col, Reachable[][] reachable) {

        /*
            If an index is already reachable return true
         */
        if (reachable[x][y]!=null &&(reachable[x][y]).reachPacific) {
            return true;
        }

        if (x == 0 || y == 0) {
            return true;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {

            int newX = x + xMove[i];
            int newY = y + yMove[i];

            if (isSafe(row, col, newX, newY, matrix[x][y], matrix,visited)) {
                if (reachPacific(matrix, visited, newX, newY, row, col, reachable)) {
                    visited[x][y] = false;
                    return true;
                }
            }

        }

        visited[x][y] = false;
        return false;

    }


    private boolean isSafe(int row, int col, int x, int y, int currentVal, int[][] matrix,boolean[][] visited) {
        return (x >= 0 && y >= 0 && x < row && y < col && !visited[x][y] && currentVal >= matrix[x][y]);
    }

    public static void main(String[] args) {

        int[][] arr = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        new WaterFlow().pacificAtlantic(arr);

    }

}
